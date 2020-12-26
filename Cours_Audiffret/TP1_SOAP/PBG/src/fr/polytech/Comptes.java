package fr.polytech;

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
	
	public void ajouterUnCompte(Compte compte) {
		//je me connecte � la base de donn�e et on ajoute l'�tudiant pass� en param�tre ....
		this.seConnecter(); //je r�cup�re une connexion ....
		
		//failles d'injection SQL ....
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement("INSERT INTO `comptes`(`nom`, `prenom`,`mail`,`mot de passe`) VALUES (?,?,?,?)");
			preparedStatement.setString(1, compte.getNom());
			preparedStatement.setString(2, compte.getPrenom());
			preparedStatement.setString(3, compte.getMail());
			preparedStatement.setString(4, compte.getMdp());
			
			//executer la requete ....
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Je n'arrive pas � ajouter un �tudiant");
			e.printStackTrace();
		}
		finally {
			this.seDeconnecter();
		}
	}
	
	public void modifierUnCompte(Compte compte) {
		//je me connecte � la base de donn�e et on ajoute l'�tudiant pass� en param�tre ....
		this.seConnecter(); //je r�cup�re une connexion ....
		
		//failles d'injection SQL ....
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement("UPDATE `comptes` SET `nom`= ?,`prenom`= ?,`mot de passe`= ? WHERE `mail`= ?");
			preparedStatement.setString(1, compte.getNom());
			preparedStatement.setString(2, compte.getPrenom());
			preparedStatement.setString(3, compte.getMdp());
			preparedStatement.setString(4, compte.getMail());
			
			//executer la requete ....
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Je n'arrive pas � modifier un �tudiant");
			e.printStackTrace();
		}
		finally {
			seDeconnecter();
		}
	}
	
	public void supprimerUnCompte(String mail) {
		//je me connecte � la base de donn�e et on ajoute l'�tudiant pass� en param�tre ....
		this.seConnecter(); //je r�cup�re une connexion ....
		
		//failles d'injection SQL ....
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement("DELETE FROM `comptes` WHERE `mail`= ?");
			preparedStatement.setString(1, mail);
			
			//executer la requete ....
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Je n'arrive pas � supprimer un �tudiant");
			e.printStackTrace();
		}
		finally {
			this.seDeconnecter();
		}
	}
	
	
	public List<Compte> recherche(String motClef) {
		//je me connecte � la base de donn�e et on ajoute l'�tudiant pass� en param�tre ....
		this.seConnecter(); //je r�cup�re une connexion ....
		
		motClef="%"+motClef+"%";
				
		List<Compte> resultat = new ArrayList<Compte>();
		
		// se connecter � la base de donn�e ...
		//Statement statement = null;
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

}
