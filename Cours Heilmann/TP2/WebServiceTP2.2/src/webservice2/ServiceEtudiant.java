package webservice2;

import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;


@WebService(targetNamespace = "http://www.polytech.fr")
public class ServiceEtudiant {

/*	public static ArrayList<Etudiant> etudiants = new ArrayList<Etudiant>();
	static {
		etudiants.add(new Etudiant("George", "peter", "1A", 1));
		etudiants.add(new Etudiant("Géraldine", "jeanne", "2A", 2));
		etudiants.add(new Etudiant("Dupuis", "Anais", "3A", 3));
		etudiants.add(new Etudiant("Steve", "Stephane", "4A", 4));
		etudiants.add(new Etudiant("Thomas", "eric", "5A", 5));
		etudiants.add(new Etudiant("Antoine", "erwan", "1A", 6));
	} */
	
	@WebMethod(operationName="creer_liste")
	public ArrayList<Etudiant> creerUneListeEtudiant(){
		ArrayList<Etudiant> etudiants1 = new ArrayList<Etudiant>();
		return etudiants1;
	}
	
	
	@WebMethod(operationName="tous")
	public ArrayList<Etudiant> afficherTousLesEtudiants(ArrayList<Etudiant> etudiants1){
		return etudiants1;
	}
	
	@WebMethod(operationName="ajout")
	public Etudiant ajouter(@WebParam(name = "mt") String prenom,String classe, int num_etudiant, ArrayList<Etudiant> etudiants1) {
		Etudiant e = new Etudiant(nom, prenom, classe, num_etudiant);
		etudiants1.add(e);
		return e;
	}
	
	@WebMethod(operationName="recherche")
	public Etudiant getEtudiant(int num_etudiant, ArrayList<Etudiant> etudiants1) {
		for(Etudiant etudiant : etudiants1) {
			if(etudiant.getNum_etudiant()==num_etudiant) {
				return etudiant;
			}			
		}
		return null;
	}
	
	@WebMethod(operationName="supprimer")
	public boolean supEtudiant(int num_etudiant, ArrayList<Etudiant> etudiants1) {
		return etudiants1.remove(getEtudiant(num_etudiant, etudiants1));
	}
	
/*	@WebMethod(operationName="modifier")
	public Etudiant ModifEtudiant(String nom, String prenom,String classe,int num_etudiant, ArrayList<Etudiant> etudiants1) {
		Etudiant etudiant = getEtudiant(num_etudiant,  etudiants1);
		etudiant.setNom(nom);
		etudiant.setPrenom(prenom);
		etudiant.setClasse(classe);
		etudiant.setNum_etudiant(num_etudiant);
	} */
	
}


