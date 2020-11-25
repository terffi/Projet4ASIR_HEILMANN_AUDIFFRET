package polytech;

public class Event {

	String affiche;
	String bodyText;
	int identifiant;
	String date;
	
	public Event(String affiche, String bodyText, int identifiant, String date) {
		super();
		this.affiche = affiche;
		this.bodyText = bodyText;
		this.identifiant = identifiant;
		this.date = date;
	}

	public String getAffiche() {
		return affiche;
	}

	public void setAffiche(String affiche) {
		this.affiche = affiche;
	}

	public String getBodyText() {
		return bodyText;
	}

	public void setBodyText(String bodyText) {
		this.bodyText = bodyText;
	}

	public int getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(int identifiant) {
		this.identifiant = identifiant;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	
	
	
}
