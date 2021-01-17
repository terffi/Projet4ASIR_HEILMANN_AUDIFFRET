package polytech;

import java.io.Serializable;


import java.sql.Blob;
import java.sql.Date;

public class Event {

	private int id;
	private String nom;
	private Blob image;
	private String description;
	private Date date;
	private String base64Image;
	
	
	public Event() {
		super();
	}





	public Event(int id, String nom, String description, Blob image, Date date, String base64Image) {
		super();
		this.id = id;
		this.nom = nom;
		this.description = description;
		this.image = image;
		this.date = date;
		this.base64Image = base64Image;
	}

	public String getDescription() {
		return description;
	}



	public Date getDate() {
		return date;
	}





	public void setDate(Date date) {
		this.date = date;
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
