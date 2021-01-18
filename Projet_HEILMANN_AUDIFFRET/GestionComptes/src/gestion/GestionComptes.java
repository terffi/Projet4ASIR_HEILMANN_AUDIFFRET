package gestion;

import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public class GestionComptes{
	
	public static ArrayList<Compte> comptes;
	
	//M�thode pour cr�er la liste d'�tudiants
	@WebMethod(operationName = "initialisation")
	public void creation() {
		comptes = new ArrayList<Compte>();
	}

	//M�thode pour ajouter des �tudiants
	@WebMethod(operationName = "ajoutCompte")
	public void ajouter(@WebParam(name="nom")String nom,@WebParam(name="prenom")String prenom,@WebParam(name="mail")String mail,@WebParam(name="mdp")String mdp) {
		Compte c = new Compte(nom, prenom, mail,mdp);
		comptes.add(c);
	}
	
	/*
	//m�thode qui premet de modifier des informations d'un �tudiants
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
	
	
	//m�thode qui permet de consulter les informations relatives � un �tudiant de cette liste (via id)
	@WebMethod(operationName = "SearchCompte")
	public Compte identifiant(@WebParam(name="mail")String mail) {
		for(Compte c : comptes) {
			if(c.getMail().equals(mail)) {
				return c;
			}
		}
		return null;
	}
	
	
	//M�thode pour avoir des informations sur tous les �tudiants de la liste
	@WebMethod(operationName = "tous")
	public ArrayList<Compte> afficherTousLesEtudiants(){
		return comptes;
	}
	
	
		/*
	//M�thode pour supprimer un �tudiant de la liste
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
		


