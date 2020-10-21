package webservice2;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Etudiant implements Serializable {

	private String nom;
	private String prenom;
	private String classe;
	private int num_etudiant;
	
	public Etudiant() {
		super();
	}
	
	public Etudiant(String nom, String prenom, String classe, int num_etudiant) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.classe = classe;
		this.num_etudiant = num_etudiant;
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

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public int getNum_etudiant() {
		return num_etudiant;
	}

	public void setNum_etudiant(int num_etudiant) {
		this.num_etudiant = num_etudiant;
	}

	@Override
	public String toString() {
		return "Etudiant [nom=" + nom + ", prenom=" + prenom + ", classe=" + classe + ", num_etudiant=" + num_etudiant
				+ "]";
	}
	
	

	
}
