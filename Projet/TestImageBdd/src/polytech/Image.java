package polytech;

import java.io.Serializable;
import java.sql.Blob;

public class Image implements Serializable{

	private String nom;
	private Blob image;
	private String base64Image;
	
	
	public Image() {
		super();
	}


	public Image(String nom, Blob image, String base64Image) {
		super();
		this.nom = nom;
		this.image = image;
		this.base64Image = base64Image;
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
