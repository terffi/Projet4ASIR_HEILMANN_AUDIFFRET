package gestion;

import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public class GestionComptes{
	
	public static ArrayList<Compte> comptes;
	
	//Méthode pour créer la liste d'étudiants
	@WebMethod(operationName = "initialisation")
	public void creation() {
		comptes = new ArrayList<Compte>();
	}

	//Méthode pour ajouter des étudiants
	@WebMethod(operationName = "ajoutCompte")
	public void ajouter(@WebParam(name="nom")String nom,@WebParam(name="prenom")String prenom,@WebParam(name="mail")String mail,@WebParam(name="mdp")String mdp) {
		Compte c = new Compte(nom, prenom, mail,mdp);
		comptes.add(c);
	}
	
	/*
	//méthode qui premet de modifier des informations d'un étudiants
	public String modifier(@WebParam(name="id")int id, @WebParam(name="nom")String nom, @WebParam(name="prenom")String prenom, @WebParam(name="specialite")String spe) {
		
		for(Etudiant e : etudiants) {
			if(e.getId()==id) {
				if(nom!=null) {
				e.setNom(nom);
				}
				if(prenom!=null) {
				e.setPrenom(prenom);
				}
				if(spe!=null) {
				e.setSpecialite(spe);
				}
				return identifiant(id);
			}
		}
		return null;
	}
	*/
	
	
	//méthode qui permet de consulter les informations relatives à un étudiant de cette liste (via id)
	@WebMethod(operationName = "SearchCompte")
	public Compte identifiant(@WebParam(name="mail")String mail) {
		for(Compte c : comptes) {
			if(c.getMail().equals(mail)) {
				return c;
			}
		}
		return null;
	}
	
	
	//Méthode pour avoir des informations sur tous les étudiants de la liste
	@WebMethod(operationName = "tous")
	public ArrayList<Compte> afficherTousLesEtudiants(){
		return comptes;
	}
	
	
		/*
	//Méthode pour supprimer un étudiant de la liste
	@WebMethod(operationName = "suppression")
	public void supprimer(@WebParam(name="id")int id) {
		
		for(Etudiant e : etudiants) {
			if(e.getId()==id) {
				etudiants.remove(e);
			}
		}
	}
	*/
	
		
}
		


