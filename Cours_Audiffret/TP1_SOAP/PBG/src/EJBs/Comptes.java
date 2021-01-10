package EJBs;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Comptes {
	
private Connection connection;
private Statement statement = null;
private ResultSet resultSet = null;
	
	public Connection getConnection() {
		return connection;
	}
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	public Statement getStatement() {
		return statement;
	}
	public void setStatement(Statement statement) {
		this.statement = statement;
	}
	public ResultSet getResultSet() {
		return resultSet;
	}
	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}
	
	//m�thode qui s'occupe uniquement de r�cup�rer la connexion � la base de donn�e ....
	public void seConnecter() {
		//Chargement du driver Mysql ...
				try {
					Class.forName("com.mysql.cj.jdbc.Driver"); //exception surveill�e ...
				} catch (ClassNotFoundException e) {
					
					System.out.println("Le driver n'est pas charg�");
				} 	
				try {
					connection = DriverManager.getConnection("jdbc:mysql://localhost/pbg?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "root");			
				} catch (SQLException e) {
					System.out.println("Probl�me de connection � la base de donn�e");
					e.printStackTrace();
				}
	}
	
	
	//m�thode qui s'occupe uniquement de se d�connecter de la base de donn�e ....
	public void seDeconnecter() {
		try {
			if (connection != null)
			connection.close();
			if(statement!=null)statement.close();
			if(resultSet != null)resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	public List<Compte> afficherTousLesComptes(){
		//M�thode qui renvoie la liste des comptes de la table comptes
		
		List<Compte> resultat = new ArrayList<Compte>();
		
		this.seConnecter();
		
		try {			
			statement = connection.createStatement();
			//executer une requete et recuperer le contenu dans l'objet resultSet ....
			resultSet = statement.executeQuery("SELECT * FROM `comptes`");
			
			//recuperation des donn�es ....
			while(resultSet.next()) {
				String nom = resultSet.getString("nom");
				String prenom = resultSet.getString("prenom");
				String mail = resultSet.getString("mail");
				String mdp = resultSet.getString("mot de passe");
				resultat.add(new Compte(nom,prenom,mail,mdp));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
				this.seDeconnecter();			
		}
		
		return resultat;
	}
	
	
	
	
	
	public Compte ajouterUnCompte(Compte compte,String mdp) {
		//v�rifie si un compte avec l'adresse mail rentr�e existe d�j� puis ajoute le compte � la table comptes si le mail n'est pas
		//d�j� pris, renvoie le compte ajout� si le mail est libre, renvoie null sinon
		
		this.seConnecter(); //je r�cup�re une connexion ....
		
		//failles d'injection SQL ....
		try {
			//v�rification de l'adresse mail
			PreparedStatement preparedStatement = this.connection.prepareStatement("SELECT * FROM `comptes` WHERE `mail`=?");
			preparedStatement.setString(1, compte.getMail());
			
			resultSet = preparedStatement.executeQuery();
			
			//recuperation des donn�es ....
			if(resultSet.next()) {
				//un compte existe d�j�
				compte=null;				
			}
			else {
				compte.setMdp(Encryption.generateStorngPasswordHash(mdp)); //encryption du mot de passe
				
				preparedStatement = this.connection.prepareStatement("INSERT INTO `comptes`(`nom`, `prenom`,`mail`,`mot de passe`) VALUES (?,?,?,?)");
				preparedStatement.setString(1, compte.getNom());
				preparedStatement.setString(2, compte.getPrenom());
				preparedStatement.setString(3, compte.getMail());
				preparedStatement.setString(4, compte.getMdp());
				
				//executer la requete ....
				preparedStatement.executeUpdate();
			}
			
		} catch (SQLException e) {
			compte=null;
			System.out.println("Je n'arrive pas � ajouter un compte");
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// erreur lors de l'encryption du mot de passe
			compte=null;
			System.out.println("erreur lors de l'encryption du mot de passe");
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// erreur lors de l'encryption du mot de passe
			compte=null;
			System.out.println("erreur lors de l'encryption du mot de passe");
			e.printStackTrace();
		}
		finally {
			this.seDeconnecter();
		}
		return compte;
	}
	
	
	
	
	public void modifierUnCompte(Compte compte) {
		//methode permettant de modifier nom et pr�nom d'un compte
		
		this.seConnecter(); //je r�cup�re une connexion ....
		
		//failles d'injection SQL ....
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement("UPDATE `comptes` SET `nom`= ?,`prenom`= ? WHERE `mail`= ?");
			preparedStatement.setString(1, compte.getNom());
			preparedStatement.setString(2, compte.getPrenom());
			preparedStatement.setString(3, compte.getMail());
			
			//executer la requete ....
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Je n'arrive pas � modifier un compte");
			e.printStackTrace();
		}
		finally {
			seDeconnecter();
		}
	}
	
	
	public String modifierMotDePasse(String mail, String mdp) {
		//m�thode permettant de modifier le mot de passe d'un compte, renvoie le mot de passe encrypt�
		
		//encryption du mot de passe
		String mdpEncrypt;
		try {
			mdpEncrypt = Encryption.generateStorngPasswordHash(mdp);
		} catch (NoSuchAlgorithmException e1) {
			//une erreur est survenu lors de l'encryption du mot de passe
			e1.printStackTrace();
			return null;	
		} catch (InvalidKeySpecException e1) {
			//une erreur est survenu lors de l'encryption du mot de passe
			e1.printStackTrace();
			return null;
		}
		
		
		this.seConnecter(); //je r�cup�re une connexion ....
			
		//failles d'injection SQL ....
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement("UPDATE `comptes` SET `mot de passe`= ? WHERE `mail`= ?");
			preparedStatement.setString(1, mdpEncrypt);
			preparedStatement.setString(2, mail);
	
			//executer la requete ....
			preparedStatement.executeUpdate();
				
		} catch (SQLException e) {
			mdpEncrypt=null;
			System.out.println("Je n'arrive pas � modifier le mot de passe");
			e.printStackTrace();
		}
		finally {
			seDeconnecter();
		}
		return mdpEncrypt;
	}
	
	public void supprimerUnCompte(String mail) {
		
		this.seConnecter(); //je r�cup�re une connexion ....
		
		//failles d'injection SQL ....
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement("DELETE FROM `comptes` WHERE `mail`= ?");
			preparedStatement.setString(1, mail);
			
			//executer la requete ....
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Je n'arrive pas � supprimer un compte");
			e.printStackTrace();
		}
		finally {
			this.seDeconnecter();
		}
	}
	
	
	public List<Compte> recherche(String motClef) {
		//moteur de recherche renvoyant la liste des comptes trouv� en fonction de la recherche motClef
		
		this.seConnecter(); //je r�cup�re une connexion ....
		
		motClef="%"+motClef+"%";
				
		List<Compte> resultat = new ArrayList<Compte>();
		
		// se connecter � la base de donn�e ...
		ResultSet resultSet = null;
		
		try {			
			//executer une requete et recuperer le contenu dans l'objet resultSet ....
			PreparedStatement preparedStatement = this.connection.prepareStatement("SELECT * FROM `comptes` WHERE `nom` LIKE ? OR `prenom` LIKE ? OR `mail` LIKE ?");
			preparedStatement.setString(1, motClef);
			preparedStatement.setString(2, motClef);
			preparedStatement.setString(3, motClef);

			resultSet = preparedStatement.executeQuery();
			//recuperation des donn�es ....
			while(resultSet.next()) {
				String nom = resultSet.getString("nom");
				String prenom = resultSet.getString("prenom");
				String mail = resultSet.getString("mail");
				String mdp = resultSet.getString("mot de passe");
				resultat.add(new Compte(nom,prenom,mail,mdp));
			}
			
		} catch (SQLException e) {
			System.out.println("Probl�me de connection � la base de donn�e");
			e.printStackTrace();
		}
		finally {
			this.seDeconnecter();
		}
		
		return resultat;
	}
	
	
	public Compte connexion(String mail, String mdp) {
		//permet � un utilisateur de se connecter, renvoi le compte correspondant si la combinaison mail/mdp correspond � un compte
		//renvoie null sinon
		
		this.seConnecter();
		
		Compte compte = new Compte();
		
		try {
			//executer une requete et recuperer le contenu dans l'objet resultSet ....
			PreparedStatement preparedStatement = this.connection.prepareStatement("SELECT * FROM `comptes` WHERE `mail`=?");
			preparedStatement.setString(1, mail);

			resultSet = preparedStatement.executeQuery();
			//recuperation des donn�es ....
			if(resultSet.next()) {
				String mdpEncrypt = resultSet.getString("mot de passe");
				try {
					if(Encryption.validatePassword(mdp, mdpEncrypt)) {
						compte.setNom(resultSet.getString("nom"));
						compte.setPrenom(resultSet.getString("prenom"));
						compte.setMail(mail);
						compte.setMdp(mdpEncrypt);
					}
					else {
						compte=null;
					}
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidKeySpecException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				//le mail entr� n'est pas dans la table
				compte=null;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			this.seDeconnecter();
		}	
		
		return compte;
	}
	
	
	public boolean compteValide(Compte compte) {
		//permet de v�rifier la validit� d'un compte
		//renvoie true si le couple mail/mot de passe sont dans la table comptes
		//renvoie false sinon
		
		boolean valide = false;
		
		this.seConnecter();
		
		try {
			//executer une requete et recuperer le contenu dans l'objet resultSet ....
			PreparedStatement preparedStatement = this.connection.prepareStatement("SELECT * FROM `comptes` WHERE `mail`=? AND `mot de passe`=?");
			preparedStatement.setString(1, compte.getMail());
			preparedStatement.setString(2, compte.getMdp());

			resultSet = preparedStatement.executeQuery();
			//recuperation des donn�es ....
			if(resultSet.next()) {
				return true;
			}			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			this.seDeconnecter();
		}	
		
		return valide;
	}

}
