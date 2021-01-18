package gestion;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Compte implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nom;
	private String prenom;
	private String mail;
	private String mdp;
	private boolean admin;
	public Compte() {
		super();
	}
	public Compte(String nom, String prenom, String mail, String mdp) {
		super();
		
		//encryption du mot de passe (mot de passe null si erreur)
		try {
			
			this.mdp=Encryption.generateStorngPasswordHash(mdp);
			
		} catch (NoSuchAlgorithmException e) {
			// erreur lors de l'encryption du mot de passe
			this.mdp=null;
			System.out.println("erreur lors de l'encryption du mot de passe");
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// erreur lors de l'encryption du mot de passe
			this.mdp=null;
			System.out.println("erreur lors de l'encryption du mot de passe");
			e.printStackTrace();
		}
		
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.admin=false;
	}
	public Compte(String nom, String prenom, String mail, String mdp, boolean admin) {
		super();
		
		//encryption du mot de passe (mot de passe null si erreur)
		try {
			
			this.mdp=Encryption.generateStorngPasswordHash(mdp);
			
		} catch (NoSuchAlgorithmException e) {
			// erreur lors de l'encryption du mot de passe
			this.mdp=null;
			System.out.println("erreur lors de l'encryption du mot de passe");
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// erreur lors de l'encryption du mot de passe
			this.mdp=null;
			System.out.println("erreur lors de l'encryption du mot de passe");
			e.printStackTrace();
		}
		
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.admin=admin;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		//encryption du mot de passe (mot de passe null si erreur)
		try {
			
			this.mdp=Encryption.generateStorngPasswordHash(mdp);
			
		} catch (NoSuchAlgorithmException e) {
			// erreur lors de l'encryption du mot de passe
			this.mdp=null;
			System.out.println("erreur lors de l'encryption du mot de passe");
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// erreur lors de l'encryption du mot de passe
			this.mdp=null;
			System.out.println("erreur lors de l'encryption du mot de passe");
			e.printStackTrace();
		}
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
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	public void setMdpEncryp(String mdpEncrypt) {
		this.mdp=mdpEncrypt;
	}

}
