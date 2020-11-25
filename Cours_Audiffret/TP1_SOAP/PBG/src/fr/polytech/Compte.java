package fr.polytech;

public class Compte {
	private int id;
	private String nom;
	private String prenom;
	private String mail;
	private static int num;
	public Compte() {
		super();
	}
	public Compte(String nom, String prenom, String mail) {
		super();
		this.id = num++;
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
	}
	public Compte(int id, String nom, String prenom, String mail) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}

}
