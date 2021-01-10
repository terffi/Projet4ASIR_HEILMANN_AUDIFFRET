package polytech;

import java.io.Serializable;
import java.sql.Blob;

public class Image implements Serializable{

	private int id;
	private String nom;
	private Blob image;
	private String description;
	private String base64Image;
	
	
	public Image() {
		super();
	}





	public Image(int id, String nom, String description, Blob image, String base64Image) {
		super();
		this.id = id;
		this.nom = nom;
		this.description = description;
		this.image = image;
		this.base64Image = base64Image;
	}

	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getBase64Image() {
		return base64Image;
	}


	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public Blob getImage() {
		return image;
	}


	public void setImage(Blob image) {
		this.image = image;
	}
	
	
	
	
	
	
}
