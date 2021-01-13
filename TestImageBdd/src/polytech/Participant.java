package polytech;

public class Participant {

	private int idEvents;
	private String mail;
	
	
	public Participant(int idEvents, String mail) {
		super();
		this.idEvents = idEvents;
		this.mail = mail;
	}


	public int getIdEvents() {
		return idEvents;
	}


	public void setIdEvents(int idEvents) {
		this.idEvents = idEvents;
	}


	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}
	
	
	
}
