package fr.polytech;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


//ici, je définis la couche métier de notre application ....
public class Etudiants {
	
	private Connection connection;
	
	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	//méthode qui s'occupe uniquement de récupérer la connexion à la base de donnée ....
	public void seConnecter() {
		//Chargement du driver Mysql ...
				try {
					Class.forName("com.mysql.cj.jdbc.Driver"); //exception surveillée ...
				} catch (ClassNotFoundException e) {
					
					System.out.println("Le driver n'est pas chargé");
				} 	
				try {
					connection = DriverManager.getConnection("jdbc:mysql://localhost/dao?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "root");			
				} catch (SQLException e) {
					System.out.println("Problème de connection à la base de donnée");
					System.out.println(e.getMessage());
				}
	}
	
	public List<Etudiant> afficherTousLesEtudiants(){
		
		List<Etudiant> resultat = new ArrayList<Etudiant>();
		
		this.seConnecter();
		
		// se connecter à la base de donnée ...
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {			
			statement = connection.createStatement();
			//executer une requete et recuperer le contenu dans l'objet resultSet ....
			resultSet = statement.executeQuery("SELECT * FROM `etudiants`");
			
			//recuperation des données ....
			while(resultSet.next()) {
				int identifiant = resultSet.getInt("identifiant");
				String nom = resultSet.getString("nom");
				String prenom = resultSet.getString("prenom");
				resultat.add(new Etudiant(identifiant,nom,prenom));
			}
			
		} catch (SQLException e) {
			System.out.println("Problème de connection à la base de donnée");
			System.out.println(e.getMessage());
		}
		finally {
				try {
					if (connection != null)
					connection.close();
					if(statement!=null)statement.close();
					if(resultSet != null)resultSet.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
		
		return resultat;
	}
	
	public void ajouterUnEtudiant(Etudiant etudiant) {
		//je me connecte à la base de donnée et on ajoute l'étudiant passé en paramètre ....
		this.seConnecter(); //je récupère une connexion ....
		
		//failles d'injection SQL ....
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement("INSERT INTO `etudiants`(`identifiant`, `nom`, `prenom`) VALUES (?,?,?)");
			preparedStatement.setInt(1, etudiant.getNumero());
			preparedStatement.setString(2, etudiant.getNom());
			preparedStatement.setString(3, etudiant.getPrenom());
			
			//executer la requete ....
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Je n'arrive pas à ajouter un étudiant");
		}
	}
	
	public void modifierUnEtudiant(Etudiant etudiant) {
		//je me connecte à la base de donnée et on ajoute l'étudiant passé en paramètre ....
		this.seConnecter(); //je récupère une connexion ....
		
		//failles d'injection SQL ....
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement("UPDATE `etudiants` SET `nom`= ?,`prenom`= ? WHERE `identifiant`= ?");
			preparedStatement.setInt(3, etudiant.getNumero());
			preparedStatement.setString(1, etudiant.getNom());
			preparedStatement.setString(2, etudiant.getPrenom());
			
			//executer la requete ....
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Je n'arrive pas à modifier un étudiant");
		}
	}
	
	public void supprimerUnEtudiant(int id) {
		//je me connecte à la base de donnée et on ajoute l'étudiant passé en paramètre ....
		this.seConnecter(); //je récupère une connexion ....
		
		//failles d'injection SQL ....
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement("DELETE FROM `etudiants` WHERE `identifiant`= ?");
			preparedStatement.setInt(1, id);
			
			//executer la requete ....
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Je n'arrive pas à supprimer un étudiant");
		}
	}
	
	
	public List<Etudiant> recherche(String motClef) {
		//je me connecte à la base de donnée et on ajoute l'étudiant passé en paramètre ....
		this.seConnecter(); //je récupère une connexion ....
				
		List<Etudiant> resultat = new ArrayList<Etudiant>();
		
		// se connecter à la base de donnée ...
		//Statement statement = null;
		ResultSet resultSet = null;
		
		try {			
			//statement = connection.createStatement();
			//executer une requete et recuperer le contenu dans l'objet resultSet ....
			PreparedStatement preparedStatement = this.connection.prepareStatement("SELECT * FROM `etudiants` WHERE `identifiant` LIKE ? OR `nom` LIKE ? OR `prenom` LIKE ?");
			preparedStatement.setString(1, motClef);
			preparedStatement.setString(2, motClef);
			preparedStatement.setString(3, motClef);
			//resultSet = statement.executeQuery("SELECT * FROM `etudiants` WHERE `identifiant` LIKE '%"+motClef+"%' OR `nom` LIKE '%"+motClef+"%' OR `prenom` LIKE '%"+motClef+"%'");

			resultSet = preparedStatement.executeQuery();
			//recuperation des données ....
			while(resultSet.next()) {
				int identifiant = resultSet.getInt("identifiant");
				String nom = resultSet.getString("nom");
				String prenom = resultSet.getString("prenom");
				resultat.add(new Etudiant(identifiant,nom,prenom));
			}
			
		} catch (SQLException e) {
			System.out.println("Problème de connection à la base de donnée");
			System.out.println(e.getMessage());
		}
		
		return resultat;
	}
}
