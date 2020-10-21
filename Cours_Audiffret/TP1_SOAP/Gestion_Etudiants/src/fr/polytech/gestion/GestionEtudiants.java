package fr.polytech.gestion;

import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(targetNamespace="http://www.polytech.fr")
public class GestionEtudiants {
	
	public static ArrayList<Etudiant> etudiants;
	
	//M�thode pour cr�er la liste d'�tudiants
	@WebMethod(operationName = "initialisation")
	public void creation() {
		etudiants = new ArrayList<Etudiant>();
	}

	//M�thode pour ajouter des �tudiants
	@WebMethod(operationName = "ajout")
	public String ajouter(@WebParam(name="nom")String nom,@WebParam(name="prenom")String prenom,@WebParam(name="specialite")String spe) {
		Etudiant e = new Etudiant(nom, prenom, spe);
		etudiants.add(e);
		return identifiant(e.getId());
	}
	
	
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
	
	
	//m�thode qui permet de consulter les informations relatives � un �tudiant de cette liste (via id)
	@WebMethod(operationName = "SearchId")
	public String identifiant(@WebParam(name="id")int id) {
		for(Etudiant e : etudiants) {
			if(e.getId()==id) {
				return "["+String.valueOf(id)+", "+e.getNom()+", "+e.getPrenom()+", "+e.getSpecialite()+"]";
			}
		}
		return null;
	}
	
	
	//M�thode pour avoir des informations sur tous les �tudiants de la liste
	@WebMethod(operationName = "tous")
	public String afficherTousLesEtudiants(){
		String liste="";
		for (Etudiant e : etudiants) {
			liste+=identifiant(e.getId())+" \n";
		}
		return liste;
	}
	
	
		
	//M�thode pour supprimer un �tudiant de la liste
	@WebMethod(operationName = "suppression")
	public void supprimer(@WebParam(name="id")int id) {
		
		for(Etudiant e : etudiants) {
			if(e.getId()==id) {
				etudiants.remove(e);
			}
		}
	}
	
		
}
		


