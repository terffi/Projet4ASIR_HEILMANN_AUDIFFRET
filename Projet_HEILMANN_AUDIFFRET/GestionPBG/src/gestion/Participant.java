package gestion;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Participant implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idEvent;
	private String mail;
	
	
	
	
	public Participant() {
		super();
	}


	public Participant(int idEvent, String mail) {
		super();
		this.idEvent = idEvent;
		this.mail = mail;
	}


	public int getIdEvent() {
		return idEvent;
	}


	public void setIdEvent(int idEvents) {
		this.idEvent = idEvents;
	}


	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}
	
	
	
}
