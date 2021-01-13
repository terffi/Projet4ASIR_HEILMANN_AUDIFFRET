package polytech;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
