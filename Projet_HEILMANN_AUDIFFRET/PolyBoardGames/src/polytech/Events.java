package polytech;

import java.awt.image.BufferedImage;



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
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.Part;








public class Events {
	
	
	
	private Connection connection;
	

	
	//methode qui s'occupe uniquement de récupérer la connexion à la base de donnée ...
	public void seConnecter() {
		//Chargement du Driver
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // exception surveillée ....

		}catch (ClassNotFoundException e) {
			
			System.out.println("le driver n'est pas chargé");
		}
		
		try {
			this.connection = DriverManager.getConnection("jdbc:mysql://localhost/pbg?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "root");
			
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
	
	public void uploadImage(int id, String nom, String description, Date date, InputStream input) {
		
		this.seConnecter();
		
		
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement("INSERT INTO `bankimage` (`id`, `nom`, `description`, `image`, `date`) values (?, ?, ?, ?, ?)");
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, nom);
			preparedStatement.setString(3, description);
			preparedStatement.setBlob(4, input);
			preparedStatement.setDate(5, date);
			
			
			preparedStatement.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		 
		}
	
	public List<Event> rechercher(String Var) {
		List<Event> resultat = new ArrayList<Event>();
		
		//chargement du driver Mysql ....
		this.seConnecter();
	
		// se connecter à la base de données ....

		Statement statement = null;
		ResultSet resultSet = null;

		
		try {
			statement = connection.createStatement();
			// exécuter une requête et récupérer le contenu dans l'objet resultSet ....
			resultSet = statement.executeQuery("SELECT * FROM bankimage WHERE nom LIKE '%"+Var+"%'");
		//	resultSet = statement.executeQuery("SELECT * FROM etudiants WHERE nom="+"\""+Var+"\""+" OR prenom="+"\""+Var+"\"");

		// récupération des données ....
		
		while(resultSet.next()) {
			int id = resultSet.getInt("id");
			String nom = resultSet.getString("nom");
			String description = resultSet.getString("description");
			Blob image = resultSet.getBlob("image");
			Date date = resultSet.getDate("date");
			
			InputStream in = image.getBinaryStream();  
			

			
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte[] buffer = new byte[4096];
			int bytesRead = -1;
			
			while ((bytesRead = in.read(buffer)) != -1) {
			    out.write(buffer, 0, bytesRead);
			}
			 
			byte[] imageBytes = out.toByteArray();
			 
			String base64Image = Base64.getEncoder().encodeToString(imageBytes);
			 
			in.close();
			out.close();
			
			resultat.add(new Event(id, nom, description, image, date, base64Image));
			
		}
		
		}catch(SQLException e) {
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
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
	
	public List<Event> afficherTout(){
		List<Event> resultat = new ArrayList<Event>();
		
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
			int id = resultSet.getInt("id");
			String nom = resultSet.getString("nom");
			String description = resultSet.getString("description");
			Blob image = resultSet.getBlob("image");
			Date date = resultSet.getDate("date");
			
			InputStream in = image.getBinaryStream();  
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte[] buffer = new byte[4096];
			int bytesRead = -1;
			
			while ((bytesRead = in.read(buffer)) != -1) {
			    out.write(buffer, 0, bytesRead);
			}
			 
			byte[] imageBytes = out.toByteArray();
			 
			String base64Image = Base64.getEncoder().encodeToString(imageBytes);
			 
			in.close();
			out.close();
			
			resultat.add(new Event(id, nom, description, image, date, base64Image));
			
		}
		
		}catch(SQLException e) {
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
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
	
	
	public void supprimerImage(int id) {
		this.seConnecter();
		
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement("DELETE FROM bankimage WHERE `id`=?");
			preparedStatement.setInt(1, id);
			
			preparedStatement.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("impossible d'exécuter la commande");
		}
		
	}
	
	
	public void modifierImage(int id, InputStream input) {
		this.seConnecter();
		
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement("UPDATE bankimage SET `image`=? WHERE `id`=?");
			preparedStatement.setInt(2, id);
			preparedStatement.setBlob(1, input);
			
			preparedStatement.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void modifierNomDescriptionDate(int id, String nom, String description, Date date) {
		this.seConnecter();
		
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement("UPDATE bankimage SET `nom`=?, `description`=?, `date`=? WHERE `id`=?");
			preparedStatement.setInt(4, id);
			preparedStatement.setString(1, nom);
			preparedStatement.setString(2, description);
			preparedStatement.setDate(3, date);
			
			preparedStatement.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	//vérifie si l'identifiant est déjà utilisé, envoi_modif = true pour vérifier si il est libre, et false pour vérifier qu'il est bien utilisé
	public boolean verification_id2(int id, boolean est_pas_present) {
		
		List<Integer> resultat = new ArrayList<Integer>();
		int a = 0;
		//chargement du driver Mysql ....
		this.seConnecter();
		// se connecter à la base de données ....
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.createStatement();
			
			resultSet = statement.executeQuery("SELECT `id` FROM bankimage");
		// récupération des données ....
		while(resultSet.next()) {
			int identifiant = resultSet.getInt("id");
			resultat.add(identifiant);
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
		for(int i=0; i<resultat.size(); i++) {
			if(resultat.get(i)==id) {a++;}	
		}
		if(est_pas_present) {	
			if(a==0) {return true;}
			else {return false;}
		}
		else {
			if(a==0) {return false;}
			else {return true;}
		}
		
	}
	
	
	
	

}
