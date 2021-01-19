package webservice;
import java.util.ArrayList;
import java.util.Scanner;


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
		for(Etudiant etudiant : etudiants1) {
			String s = "Etudiant [nom=" + etudiant.getNom() + ", prenom=" + etudiant.getPrenom() + ", classe=" + etudiant.getClasse() + ", num_etudiant=" + etudiant.getNum_etudiant()
					+ "]";	
			System.out.println(s);
		}
		return etudiants1;
	}
	
	@WebMethod(operationName="ajout")
	public Etudiant ajouter(String nom, String prenom, String classe, int num_etudiant, ArrayList<Etudiant> etudiants1) {
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
	
	@WebMethod(operationName="modifier")
	public Etudiant ModifEtudiant(int element_a_modifier, int num_etudiant,  ArrayList<Etudiant> etudiants1) {
		Etudiant etudiant = getEtudiant(num_etudiant,  etudiants1);
		if(element_a_modifier==1) {
			Scanner scnom = new Scanner(System.in);
			System.out.println("nom :");
			String strnom = scnom.nextLine();
			etudiant.setNom(strnom);
		}
		if(element_a_modifier==2) {
			Scanner scprenom = new Scanner(System.in);
			System.out.println("prenom :");
			String strprenom = scprenom.nextLine();
			etudiant.setPrenom(strprenom);
		}
		if(element_a_modifier==3) {
			Scanner scclasse = new Scanner(System.in);
			System.out.println("classe :");
			String strclasse = scclasse.nextLine();
			etudiant.setClasse(strclasse);
		}
		if(element_a_modifier==4) {
			Scanner scnum = new Scanner(System.in);
			System.out.println("num_etudiant :");
			int strnum = scnum.nextInt();
			etudiant.setNum_etudiant(strnum);
		}
		
		return etudiant;

	} 
	
}


