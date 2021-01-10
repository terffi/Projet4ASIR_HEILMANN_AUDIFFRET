package polytech;

import java.awt.image.BufferedImage;



import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
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








public class Images {
	private Connection connection;
	
	private Map<String,String> erreurs = new HashMap<String,String>();
	
	public Map<String,String> getErreurs(){
		return erreurs;
	}
	
	public void setErreur(String champ, String message) {
		erreurs.put(champ, message);
	}

	
	//methode qui s'occupe uniquement de récupérer la connexion à la base de donnée ...
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
	
	public void uploadImage(int id, String nom, String description, InputStream input) {
		
		this.seConnecter();
		
		
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement("INSERT INTO `bankimage` (`id`, `nom`, `description`, `image`) values (?, ?, ?, ?)");
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, nom);
			preparedStatement.setString(3, description);
			preparedStatement.setBlob(4, input);
			
			preparedStatement.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		 
		}
	
	public List<Image> rechercher(String Var) {
		List<Image> resultat = new ArrayList<Image>();
		
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
			
			resultat.add(new Image(id, nom, description, image, base64Image));
			
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
	
	public List<Image> afficherTout(){
		List<Image> resultat = new ArrayList<Image>();
		
		//chargement du driver Mysql ....
		this.seConnecter();
	
		// se connecter à la base de données ....

		Statement statement = null;
		ResultSet resultSet = null;

		
		try {
			statement = connection.createStatement();
			// exécuter une requête et récupérer le contenu dans l'objet resultSet ....
			resultSet = statement.executeQuery("SELECT * FROM bankimage");
		//	resultSet = statement.executeQuery("SELECT * FROM etudiants WHERE nom="+"\""+Var+"\""+" OR prenom="+"\""+Var+"\"");

		// récupération des données ....
		
		while(resultSet.next()) {
			int id = resultSet.getInt("id");
			String nom = resultSet.getString("nom");
			String description = resultSet.getString("description");
			Blob image = resultSet.getBlob("image");
			
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
			
			resultat.add(new Image(id, nom, description, image, base64Image));
			
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
	
	public void modifierNomDescription(int id, String nom, String description) {
		this.seConnecter();
		
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement("UPDATE bankimage SET `nom`=?, `description`=? WHERE `id`=?");
			preparedStatement.setInt(2, id);
			preparedStatement.setString(1, nom);
			preparedStatement.setString(2, description);
			
			preparedStatement.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	//vérifie si l'identifiant est déjà utilisé
	public boolean verification_id(int id, boolean envoi_modif) {
		
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
		if(envoi_modif) {	
			if(a==0) {return true;}
			else {return false;}
		}
		else {
			if(a==0) {return false;}
			else {return true;}
		}
		
	}
	
	
	
	

}
