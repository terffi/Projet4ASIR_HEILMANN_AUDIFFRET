package fr.polytech;

public class Compte {
	private String nom;
	private String prenom;
	private String mail;
	private String mdp;
	public Compte() {
		super();
	}
	public Compte(String nom, String prenom, String mail, String mdp) {
		super();
		this.mdp=mdp;
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
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
