package gestion;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;



@WebService
public class GestionPBG {
	
	public static ArrayList<Compte> comptes = new ArrayList<Compte>();
	public static ArrayList<Event> events;
	public static ArrayList<Participant> participations;

	//Méthode pour créer les listes
	@WebMethod(operationName = "initialisation")
	public void creation() {
		events = new ArrayList<Event>();
		
		participations= new ArrayList<Participant>();
	}
	
	
	
	//gestion des comptes
	

	
	//Méthode pour obtenire tout les comptes
	@WebMethod(operationName = "afficherComptes")
	public ArrayList<Compte> afficherComptes(){
		//Méthode qui renvoie la liste des comptes
		
		for(Compte c : comptes) {
			System.out.println(c.getMail());
		}
		
		return comptes;
	}
	
	
	
	//Méthode pour obtenir les informations d'un compte via son mail
	@WebMethod(operationName = "rechercheCompte")
	public Compte rechercheUnCompte(@WebParam(name="mail") String mail) {
		
		for(Compte c : comptes) {
			if(c.getMail().equals(mail)) {
				return c;
			}
		}
		return null;
	}
	
	
	
	//Méthode pour ajouter des comptes
	@WebMethod(operationName = "ajoutCompte")
	public Compte ajouter(@WebParam(name="nom")String nom,@WebParam(name="prenom")String prenom,@WebParam(name="mail")String mail,@WebParam(name="mdp")String mdp) {
		//vérifie si un compte avec l'adresse mail rentrée existe déjà puis ajoute le compte à la liste si le mail n'est pas
		//déjà pris, renvoie le compte ajouté si le mail est libre, renvoie null sinon
		
		Compte c=null;
		
		//verification de l'adresse mail
		if(rechercheUnCompte(mail)==null) {
			try {
				//ajout du compte
				c = new Compte(nom, prenom, mail,Encryption.generateStorngPasswordHash(mdp)); //mot de passe encrypté
				
			} catch (NoSuchAlgorithmException e) {
				// erreur lors de l'encryption du mot de passe
				c=null;
				System.out.println("erreur lors de l'encryption du mot de passe");
				e.printStackTrace();
			} catch (InvalidKeySpecException e) {
				// erreur lors de l'encryption du mot de passe
				c=null;
				System.out.println("erreur lors de l'encryption du mot de passe");
				e.printStackTrace();
			}
			
			comptes.add(c);
		}
		
		return c;

	}
	
	
	
	//Méthode pour modifier un compte
	@WebMethod(operationName = "modifCompte")
	public void modifierUnCompte(@WebParam(name="nom")String nom,@WebParam(name="prenom")String prenom,@WebParam(name="mail")String mail) {
		//methode permettant de modifier nom et prénom d'un compte
		
		Compte c = rechercheUnCompte(mail);
		
		if(c!=null) {
			c.setNom(nom);
			c.setPrenom(prenom);
		}
	}
	
	
	
	//Méthode pour modifier le mot de passe d'un compte
	@WebMethod(operationName = "modifMdpCompte")
	public String modifierMotDePasse(@WebParam(name="mail") String mail,@WebParam(name="mdp") String mdp) {
		//méthode permettant de modifier le mot de passe d'un compte, renvoie le mot de passe encrypté
		
		//encryption du mot de passe
		String mdpEncrypt;
		try {
			mdpEncrypt = Encryption.generateStorngPasswordHash(mdp);
		} catch (NoSuchAlgorithmException e1) {
			//une erreur est survenu lors de l'encryption du mot de passe
			e1.printStackTrace();
			return null;	
		} catch (InvalidKeySpecException e1) {
			//une erreur est survenu lors de l'encryption du mot de passe
			e1.printStackTrace();
			return null;
		}
		
		//modification du mot de passe
		Compte c = rechercheUnCompte(mail);
		
		if(c!=null) {
			c.setMdp(mdpEncrypt);
		}
		
		return mdpEncrypt;
	}
	
	
	//Méthode pour supprimer un compte
	@WebMethod(operationName = "supprCompte")
	public void supprimerUnCompte(@WebParam(name="mail") String mail) {
		
		Compte c = rechercheUnCompte(mail);
		comptes.remove(c);
	}
	
	
	
	//Méthode pour connecter un utilisateur
	@WebMethod(operationName = "connexion")
	public Compte connexion(@WebParam(name="mail") String mail,@WebParam(name="mdp") String mdp) {
		//permet à un utilisateur de se connecter, renvoi le compte correspondant si la combinaison mail/mdp correspond à un compte
		//renvoie null sinon
		
		//recherche du compte associé au mail donné
		Compte c = rechercheUnCompte(mail);
		
		if(c!=null) {
			//vérification du mot de passe
			try {
				if(Encryption.validatePassword(c.getMdp(),mdp)) {
					
					return c;
					
				}
			} catch (NoSuchAlgorithmException e) {
				System.out.println("Erreur lors de la vérification du mot de passe");
				e.printStackTrace();
				return null;
			} catch (InvalidKeySpecException e) {
				System.out.println("Erreur lors de la vérification du mot de passe");
				e.printStackTrace();
				return null;
			}
				
		}
		
		//aucun compte n'existe avec ce mail ou le mot de passe est incorrect
		return null;
		
	}
	
	
	
	//Méthode pour valider un compte
	@WebMethod(operationName = "compteValide")
	public boolean compteValide(@WebParam(name="compte") Compte compte) {
		//permet de vérifier la validité d'un compte
		//renvoie true si le couple mail/mot de passe sont dans la liste
		//renvoie false sinon
		
		Compte c = rechercheUnCompte(compte.getMail());
		
		if(c!=null) {
			if(c.getMdp().equals(compte.getMdp())) {
				//compte valide
				return true;
			}
		}

		//compte invalide
		return false;
	}
	
	
	
	//Méthode pour définir un compte administrateur
	@WebMethod(operationName = "setAdmin")
	public void compteAdmin(@WebParam(name="mail") String mail) {
		//méthode permettant de définir un compte en tant qu'administrateur
		
		Compte c = rechercheUnCompte(mail);
		c.setAdmin(true);
	}
	
	
	
	//Méthode pour savoir si un compte est administrateur
	@WebMethod(operationName = "isAdmin")
	public boolean isAdmin(@WebParam(name="compte") Compte compte) {
		//permet de vérifier la validité d'un compte et s'il est administrateur
		//renvoie true si le couple mail/mot de passe sont dans la table comptes et que admin vaut true
		//renvoie false sinon
		
		Compte c = rechercheUnCompte(compte.getMail());
		
		if(c!=null) {
			if(c.getMdp().equals(compte.getMdp())) {
				//compte valide
				return compte.isAdmin();
			}
		}
		
		//compte invalide
		return false;
	}
	
	
	
	//gestion des évènements
	
	
	
	//Méthode pour obtenir tout les events
	@WebMethod(operationName = "afficherEvents")
	public ArrayList<Event> afficherTousLesEvent(){
		//Méthode qui renvoie la liste des events
		
		return events;
	}
	
	
	
	//Méthode pour obtenir les informations d'un event via son id
	@WebMethod(operationName = "rechercheEvent")
	public Event rechercherUnEvent(@WebParam(name="id") int id) {
		
		for(Event e : events) {
			if(e.getId()==id) {
				return e;
			}
		}
		return null;
	}
	
	
	
	//Méthode pour ajouter des events
	@WebMethod(operationName = "ajoutEvent")
	public boolean ajouterEvent(@WebParam(name="id") int id,@WebParam(name="nom") String nom,@WebParam(name="description") String description,@WebParam(name="date") Date date,@WebParam(name="image") String image) {
		//vérifie si un event avec l'id rentrée existe déjà puis ajoute l'event à la liste si l'id n'est pas
		//déjà pris, renvoi true si l'event a correctement été ajouté, false sinon
		
		//verification de l'id
		if(rechercherUnEvent(id)==null) {
			//ajout de l'event
			Event e = new Event(id, nom, description, date, image);
			
			events.add(e);
			return true;
		}
		
		return false;
	}

	
	
	//Méthode pour supprimer un event
	@WebMethod(operationName = "supprEvent")
	public void supprimerEvent(@WebParam(name="id") int id) {
		
		Event e = rechercherUnEvent(id);
		events.remove(e);		
	}
	
	
	
	//Méthode pour modifier l'image d'un event
	@WebMethod(operationName = "modifImageEvent")
	public void modifierImage(@WebParam(name="id") int id,@WebParam(name="image") String image) {
		
		Event e = rechercherUnEvent(id);
		if(e!=null) {
			e.setBase64Image(image);		
		}
	}
	
	
	
	//Méthode pour modifier les infos d'un event
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
	
	
	
	//Méthode pour obtenir toute les participations
	@WebMethod(operationName = "afficherParticipations")
	public ArrayList<Participant> afficherParticipations(){
		//Méthode qui renvoie la liste des participations
		
		return participations;
	}
	
	
	
	//Méthode pour obtenir tous les participants d'un event
	@WebMethod(operationName = "afficherParticipants")
	public ArrayList<Compte> afficherParticipants(int idEvent){
		//Méthode qui renvoie la liste des participants d'un event
		
		ArrayList<Compte> participants = new ArrayList<Compte>();
		
		for(Participant p : participations) {
			if(p.getIdEvent()==idEvent) {
				participants.add(rechercheUnCompte(p.getMail()));
			}
		}
		
		return participants;
	}
	
	
	
	
	//Méthode pour ajouter des participants à un event
	@WebMethod(operationName = "ajouterParticipation")
	public void ajouterParticipation(int idEvent, String mail) {
		//vérifie si un participant le mail rentré existe déjà pour l'event donné puis ajoute la participation à la liste si 
		//le participant ne participe pas déjà à l'event
		
		//vérification de la participation
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
