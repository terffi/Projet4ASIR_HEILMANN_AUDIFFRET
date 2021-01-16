package polytech;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class Participants {

	private Connection connection;
	
	
	public void seConnecter() {
		//Chargement du Driver
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // exception surveillée ....

		}catch (ClassNotFoundException e) {
			
			System.out.println("le driver n'est pas chargé");
		}
		
		try {
			this.connection = DriverManager.getConnection("jdbc:mysql://localhost/testimage?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");

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
	
	//ajout d'un participant à un event
	public void ajouter_un_participant(int idEvents, String mail) {
		
		this.seConnecter();
		
		
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement("INSERT INTO `participants` (`idEvents`, `mail`) values (?, ?)");
			preparedStatement.setInt(1, idEvents);
			preparedStatement.setString(2, mail);

			
			
			preparedStatement.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<String> afficher_participant_event(int idEvents){
		List<String> resultat = new ArrayList<String>();
		
		//chargement du driver Mysql ....
		this.seConnecter();
	
		// se connecter à la base de données ....

		Statement statement = null;
		ResultSet resultSet = null;

		
		try {
			statement = connection.createStatement();
			// exécuter une requête et récupérer le contenu dans l'objet resultSet ....
			resultSet = statement.executeQuery("SELECT `mail` FROM bankimage WHERE idEvents LIKE '%"+idEvents+"%'");
		
		// récupération des données ....
		
		while(resultSet.next()) {
			String mail = resultSet.getString("mail");
			resultat.add(mail);
			
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
	
	
	public List<Participant> afficher_tout_particiapants(){
		List<Participant> resultat = new ArrayList<Participant>();
		
		//chargement du driver Mysql ....
		this.seConnecter();
	
		// se connecter à la base de données ....

		Statement statement = null;
		ResultSet resultSet = null;

		
		try {
			statement = connection.createStatement();
			// exécuter une requête et récupérer le contenu dans l'objet resultSet ....
			resultSet = statement.executeQuery("SELECT * FROM bankimage");
		
		// récupération des données ....
		
		while(resultSet.next()) {
			int idEvents = resultSet.getInt("idEvents");
			String mail = resultSet.getString("mail");
			resultat.add(new Participant(idEvents, mail));
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
