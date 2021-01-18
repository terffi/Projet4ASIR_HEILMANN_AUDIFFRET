package gestion;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;



@WebService
public class GestionPBG {
	
	public static ArrayList<Compte> comptes = new ArrayList<Compte>(Arrays.asList(new Compte("admin","admin","admin","admin",true)));
	public static ArrayList<Event> events;
	public static ArrayList<Participant> participations;

	//M�thode pour cr�er les listes
	@WebMethod(operationName = "initialisation")
	public void creation() {
		events = new ArrayList<Event>();
		
		participations= new ArrayList<Participant>();
	}
	
	
	
	//gestion des comptes
	

	
	//M�thode pour obtenire tout les comptes
	@WebMethod(operationName = "afficherComptes")
	public ArrayList<Compte> afficherComptes(){
		//M�thode qui renvoie la liste des comptes
		
		return comptes;
	}
	
	
	
	//M�thode pour obtenir les informations d'un compte via son mail
	@WebMethod(operationName = "rechercheCompte")
	public Compte rechercheUnCompte(@WebParam(name="mail") String mail) {
		
		for(Compte c : comptes) {
			if(c.getMail().equals(mail)) {
				return c;
			}
		}
		return null;
	}
	
	
	
	//M�thode pour ajouter des comptes
	@WebMethod(operationName = "ajoutCompte")
	public Compte ajouter(@WebParam(name="nom")String nom,@WebParam(name="prenom")String prenom,@WebParam(name="mail")String mail,@WebParam(name="mdp")String mdp) {
		//v�rifie si un compte avec l'adresse mail rentr�e existe d�j� puis ajoute le compte � la liste si le mail n'est pas
		//d�j� pris, renvoie le compte ajout� si le mail est libre, renvoie null sinon, renvoie �galement null si un probleme
		//survient lors de l'encryption du mot de passe
		
		Compte c=null;
		
		//verification de l'adresse mail
		if(rechercheUnCompte(mail)==null) {
			
			//ajout du compte
			c = new Compte(nom, prenom, mail,mdp); //mot de passe encrypt�
			
			//v�rification de l'encryption du mot de passe
			if(c.getMdp()!=null) {
				comptes.add(c);
			}
			else {
				//erreur lors de l'encryption du mot de passe
				c=null;
			}			
		}
		
		return c;

	}
	
	
	
	//M�thode pour modifier un compte
	@WebMethod(operationName = "modifCompte")
	public void modifierUnCompte(@WebParam(name="nom")String nom,@WebParam(name="prenom")String prenom,@WebParam(name="mail")String mail) {
		//methode permettant de modifier nom et pr�nom d'un compte
		
		Compte c = rechercheUnCompte(mail);
		
		if(c!=null) {
			c.setNom(nom);
			c.setPrenom(prenom);
		}
	}
	
	
	
	//M�thode pour modifier le mot de passe d'un compte
	@WebMethod(operationName = "modifMdpCompte")
	public String modifierMotDePasse(@WebParam(name="mail") String mail,@WebParam(name="mdp") String mdp) {
		//m�thode permettant de modifier le mot de passe d'un compte, renvoie le mot de passe encrypt�,
		//renvoie null en cas d'erreur d'encryption du mot de passe
		
		//modification du mot de passe
		Compte c = rechercheUnCompte(mail);
		
		if(c!=null) {
			String oldMdp = c.getMdp(); //stockage de l'ancien mot de passe en cas d'erreur d'encryption
			
			c.setMdp(mdp);
			
			if(c.getMdp()==null) {
				//erreur d'encryption
				c.setMdpEncryp(oldMdp); //on remet l'ancien mot de passe
				return null;
			}
		}
		
		return c.getMdp();
	}
	
	
	//M�thode pour supprimer un compte
	@WebMethod(operationName = "supprCompte")
	public void supprimerUnCompte(@WebParam(name="mail") String mail) {
		
		Compte c = rechercheUnCompte(mail);
		comptes.remove(c);
	}
	
	
	
	//M�thode pour connecter un utilisateur
	@WebMethod(operationName = "connexion")
	public Compte connexion(@WebParam(name="mail") String mail,@WebParam(name="mdp") String mdp) {
		//permet � un utilisateur de se connecter, renvoi le compte correspondant si la combinaison mail/mdp correspond � un compte
		//renvoie null sinon
		
		//recherche du compte associ� au mail donn�
		Compte c = rechercheUnCompte(mail);
		
		if(c!=null) {
			//v�rification du mot de passe
			try {
				if(Encryption.validatePassword(mdp,c.getMdp())) {
					return c;
					
				}
			} catch (NoSuchAlgorithmException e) {
				System.out.println("Erreur lors de la v�rification du mot de passe");
				e.printStackTrace();
				return null;
			} catch (InvalidKeySpecException e) {
				System.out.println("Erreur lors de la v�rification du mot de passe");
				e.printStackTrace();
				return null;
			}
				
		}
		
		//aucun compte n'existe avec ce mail ou le mot de passe est incorrect
		return null;
		
	}
	
	
	
	//M�thode pour valider un compte
	@WebMethod(operationName = "compteValide")
	public boolean compteValide(@WebParam(name="mail") String mail, @WebParam(name="mdp") String mdp) {
		//permet de v�rifier la validit� d'un compte
		//renvoie true si le couple mail/mot de passe sont dans la liste
		//renvoie false sinon
		
		Compte c = rechercheUnCompte(mail);
		
		if(c!=null) {
			if(c.getMdp().equals(mdp)) {
				//compte valide
				return true;
			}
		}

		//compte invalide
		return false;
	}
	
	
	
	//M�thode pour d�finir un compte administrateur
	@WebMethod(operationName = "setAdmin")
	public void compteAdmin(@WebParam(name="mail") String mail) {
		//m�thode permettant de d�finir un compte en tant qu'administrateur
		
		Compte c = rechercheUnCompte(mail);
		c.setAdmin(true);
	}
	
	
	
	//M�thode pour savoir si un compte est administrateur
	@WebMethod(operationName = "isAdmin")
	public boolean isAdmin(@WebParam(name="mail") String mail, @WebParam(name="mdp") String mdp) {
		//permet de v�rifier la validit� d'un compte et s'il est administrateur
		//renvoie true si le couple mail/mot de passe sont dans la table comptes et que admin vaut true
		//renvoie false sinon
		
		Compte c = rechercheUnCompte(mail);
		
		if(c!=null) {
			if(c.getMdp().equals(mdp)) {
				//compte valide
				return c.isAdmin();
			}
		}
		
		//compte invalide
		return false;
	}
	
	
	
	//gestion des �v�nements
	
	
	
	//M�thode pour obtenir tout les events
	@WebMethod(operationName = "afficherEvents")
	public ArrayList<Event> afficherTousLesEvent(){
		//M�thode qui renvoie la liste des events
		
		return events;
	}
	
	
	
	//M�thode pour obtenir les informations d'un event via son id
	@WebMethod(operationName = "rechercheEvent")
	public Event rechercherUnEvent(@WebParam(name="id") int id) {
		
		for(Event e : events) {
			if(e.getId()==id) {
				return e;
			}
		}
		return null;
	}
	
	
	
	//M�thode pour ajouter des events
	@WebMethod(operationName = "ajoutEvent")
	public boolean ajouterEvent(@WebParam(name="id") int id,@WebParam(name="nom") String nom,@WebParam(name="description") String description,@WebParam(name="date") Date date,@WebParam(name="image") String image) {
		//v�rifie si un event avec l'id rentr�e existe d�j� puis ajoute l'event � la liste si l'id n'est pas
		//d�j� pris, renvoi true si l'event a correctement �t� ajout�, false sinon
		
		//verification de l'id
		if(rechercherUnEvent(id)==null) {
			//ajout de l'event
			Event e = new Event(id, nom, description, date, image);
			
			events.add(e);
			return true;
		}
		
		return false;
	}

	
	
	//M�thode pour supprimer un event
	@WebMethod(operationName = "supprEvent")
	public void supprimerEvent(@WebParam(name="id") int id) {
		
		Event e = rechercherUnEvent(id);
		events.remove(e);		
	}
	
	
	
	//M�thode pour modifier l'image d'un event
	@WebMethod(operationName = "modifImageEvent")
	public void modifierImage(@WebParam(name="id") int id,@WebParam(name="image") String image) {
		
		Event e = rechercherUnEvent(id);
		if(e!=null) {
			e.setBase64Image(image);		
		}
	}
	
	
	
	//M�thode pour modifier les infos d'un event
	@WebMethod(operationName = "modifEvent")
	public void modifierNomDescriptionDate(@WebParam(name="id") int id,@WebParam(name="nom") String nom,@WebParam(name="description") String description,@WebParam(name="date") Date date) {
		
		Event e = rechercherUnEvent(id);
		
		if(e!=null) {
			e.setNom(nom);
			e.setDescription(description);
			e.setDate(date);
		}
	}
	
	
	
	//gestion des participants aux events
	
	
	
	//M�thode pour obtenir toute les participations
	@WebMethod(operationName = "afficherParticipations")
	public ArrayList<Participant> afficherParticipations(){
		//M�thode qui renvoie la liste des participations
		
		return participations;
	}
	
	
	
	//M�thode pour obtenir tous les participants d'un event
	@WebMethod(operationName = "afficherParticipants")
	public ArrayList<Compte> afficherParticipants(int idEvent){
		//M�thode qui renvoie la liste des participants d'un event
		
		ArrayList<Compte> participants = new ArrayList<Compte>();
		
		for(Participant p : participations) {
			if(p.getIdEvent()==idEvent) {
				participants.add(rechercheUnCompte(p.getMail()));
			}
		}
		
		return participants;
	}
	
	
	
	
	//M�thode pour ajouter des participants � un event
	@WebMethod(operationName = "ajouterParticipation")
	public void ajouterParticipation(int idEvent, String mail) {
		//v�rifie si un participant le mail rentr� existe d�j� pour l'event donn� puis ajoute la participation � la liste si 
		//le participant ne participe pas d�j� � l'event
		
		//v�rification de la participation
		List<Compte> participants = afficherParticipants(idEvent);
		
		boolean valide = true;
		for(Compte c : participants) {
			if(c.getMail().equals(mail)) {
				valide=false;
			}
		}
		
		//ajout de la participation
		if(valide) {
			participations.add(new Participant(idEvent, mail));
		}
	}

}
