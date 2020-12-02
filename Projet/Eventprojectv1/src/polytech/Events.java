package polytech;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import polytech.Event;

public class Events {

	private Connection connection;
	
	public void seConnecter() {
		//chargement du driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		
		}catch(ClassNotFoundException e) {
			System.out.println("le driver ne s'est pas chargé");
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
	
	public List<Event> afficherTousLesEvents() {
		List<Event> resultat = new ArrayList<Event>();
		
		//chargement du driver Mysql ....
		this.seConnecter();
	
		// se connecter à la base de données ....

		Statement statement = null;
		ResultSet resultSet = null;

		
		try {
			statement = connection.createStatement();
		// exécuter une requête et récupérer le contenu dans l'objet resultSet ....
		resultSet = statement.executeQuery("SELECT * FROM `events`");

		// récupération des données ....
		
		while(resultSet.next()) {
			String affiche = resultSet.getAffiche("affiche");
			String bodyText = resultSet.getBodyText("bodyText");
			int identifiant = resultSet.getIdentifiant("bodyText");
			String date = resultSet.getDate("date");
			resultat.add(new Event(affiche, bodyText, identifiant, date));
					
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
		
	
	
	

	
}
