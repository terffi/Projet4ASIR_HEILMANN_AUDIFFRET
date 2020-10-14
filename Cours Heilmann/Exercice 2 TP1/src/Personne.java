import java.io.Serializable;
import java.sql.Date;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Personne implements Serializable{

	private String nom;
	private String prenom;
	private Date d;
	
	public Personne() {
		super();
	}
	
	
	public Personne(String nom, String prenom, Date d) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.d = d;
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
	
	public String setPrenom(String prenom) {
		return prenom;
	}

	public Date getD() {
		return d;
	}
	
	
}


