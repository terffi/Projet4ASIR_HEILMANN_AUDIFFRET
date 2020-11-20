package fr.polytech;

import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//ici, je définis la couche métier de notre application ....
public class Etudiants {

	private Connection connection;
	
	//methode qui s'occupe uniquement de récuéprer la connexion à la base de donnée ...
	public void seConnecter() {
		//Chargement du Driver
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // exception surveillée ....

		}catch (ClassNotFoundException e) {
			
			System.out.println("le driver n'est pas chargé");
		}
		
		try {
			this.connection = DriverManager.getConnection("jdbc:mysql://localhost/13novembreSD?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "root");

		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public Connection getConnection() {
		return connection;
	}



	public void setConnection(Connection connection) {
		this.connection = connection;
	}



	public List<Etudiant> afficherTousLesEtudiants() {
		List<Etudiant> resultat = new ArrayList<Etudiant>();
		
		//chargement du driver Mysql ....
		this.seConnecter();
	
		// se connecter à la base de données ....

		Statement statement = null;
		ResultSet resultSet = null;

		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/13novembreSD?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "root");
			statement = connection.createStatement();
		// exécuter une requête et récupérer le contenu dans l'objet resultSet ....
		resultSet = statement.executeQuery("SELECT * FROM `etudiants`");

		// récupération des données ....
		
		while(resultSet.next()) {
			int identifiant = resultSet.getInt("identifiant");
			String nom = resultSet.getString("nom");
			String prenom = resultSet.getString("prenom");
			resultat.add(new Etudiant(identifiant, nom, prenom));
			
		}
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		finally {
				try {
					if (connection != null) connection.close();
					if (statement != null) statement.close();
					if (resultSet != null) resultSet.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
	}
		
		
		
		
		return resultat;
}
	
	public void ajouterUnEtudiant(Etudiant etudiant) {
		
		this.seConnecter();
		
		
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement("INSERT INTO `etudiants`(`identifiant`, `nom`, `prenom`) VALUES (?,?,?)");
			preparedStatement.setInt(1, etudiant.getIdentifiant());
			preparedStatement.setString(2, etudiant.getNom());
			preparedStatement.setString(3, etudiant.getPrenom());
			
			preparedStatement.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void modifierInformationEtudiant(Etudiant etudiants) {
		this.seConnecter();
		
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement("UPDATE `etudiants` SET `nom`=?,`prenom`=? WHERE `identifiant`=?");
			preparedStatement.setInt(1, etudiants.getIdentifiant());
			preparedStatement.setString(2, etudiants.getNom());
			preparedStatement.setString(3, etudiants.getPrenom());
			
			preparedStatement.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void supprimerUnEtudiant(Etudiant etudiants) {
		this.seConnecter();
		
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement("DELETE FROM `etudiants` WHERE `identifiant`=?");
			//preparedStatement.setInt(1, etudiants.getIdentifiant());
			//preparedStatement.setString(2, etudiants.getNom());
			//preparedStatement.setString(3, etudiants.getPrenom());
			
			preparedStatement.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void rechercher(Etudiant etudiants) {
		this.seConnecter();
		
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement("SELECT `identifiant`, `nom`, `prenom` FROM `etudiants` WHERE `identifiant`=?");
			preparedStatement.setInt(1, etudiants.getIdentifiant());
			preparedStatement.setString(2, etudiants.getNom());
			preparedStatement.setString(3, etudiants.getPrenom());
			
			preparedStatement.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
