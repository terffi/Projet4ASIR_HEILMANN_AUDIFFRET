package fr.polytech.gestion;

import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(targetNamespace="http://www.polytech.fr")
public class GestionEtudiants {
	
	public static ArrayList<Etudiant> etudiants;
	
	//Méthode pour créer la liste d'étudiants
	@WebMethod(operationName = "initialisation")
	public void creation() {
		etudiants = new ArrayList<Etudiant>();
	}

	//Méthode pour ajouter des étudiants
	@WebMethod(operationName = "ajout")
	public String ajouter(@WebParam(name="nom")String nom,@WebParam(name="prenom")String prenom,@WebParam(name="specialite")String spe) {
		Etudiant e = new Etudiant(nom, prenom, spe);
		etudiants.add(e);
		return identifiant(e.getId());
	}
	
	
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
	
	
	//méthode qui permet de consulter les informations relatives à un étudiant de cette liste (via id)
	@WebMethod(operationName = "SearchId")
	public String identifiant(@WebParam(name="id")int id) {
		for(Etudiant e : etudiants) {
			if(e.getId()==id) {
				return "["+String.valueOf(id)+", "+e.getNom()+", "+e.getPrenom()+", "+e.getSpecialite()+"]";
			}
		}
		return null;
	}
	
	
	//Méthode pour avoir des informations sur tous les étudiants de la liste
	@WebMethod(operationName = "tous")
	public String afficherTousLesEtudiants(){
		String liste="";
		for (Etudiant e : etudiants) {
			liste+=identifiant(e.getId())+" \n";
		}
		return liste;
	}
	
	
		
	//Méthode pour supprimer un étudiant de la liste
	@WebMethod(operationName = "suppression")
	public void supprimer(@WebParam(name="id")int id) {
		
		for(Etudiant e : etudiants) {
			if(e.getId()==id) {
				etudiants.remove(e);
			}
		}
	}
	
		
}
		


