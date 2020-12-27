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
	
	public void uploadImage(String nom, InputStream input) {
		
		this.seConnecter();
		
		
		try {
			PreparedStatement statement = this.connection.prepareStatement("SET GLOBAL max_allowed_packet = 1024*1024*8");
			statement.execute();
			
			PreparedStatement preparedStatement = this.connection.prepareStatement("INSERT INTO `bankimage` (`nom`, `image`) values (?, ?)");
			preparedStatement.setString(1, nom);
			preparedStatement.setBlob(2, input);
			
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
			String nom = resultSet.getString("nom");
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
			
			resultat.add(new Image(nom, image, base64Image));
			
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
	
	
	
	
	

}
