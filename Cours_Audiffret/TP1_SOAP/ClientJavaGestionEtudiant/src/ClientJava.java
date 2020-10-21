import fr.polytech.GestionEtudiants;
import fr.polytech.GestionEtudiantsService;

public class ClientJava {

	public static void main(String[] args) {
		
	GestionEtudiants stub = new GestionEtudiantsService().getGestionEtudiantsPort();
	
	System.out.println("initialisation d'une liste d'étudiant");
	stub.initialisation();
	
	System.out.println("");
	
	System.out.println("Ajout de " + stub.ajout("Audiffret", "Sam", "SIR"));
	System.out.println("Ajout de " + stub.ajout("Heilmann", "Brice", null));
	
	System.out.println("");
	
	System.out.println("Affichage de toute la liste");
	System.out.println(stub.tous());
	
	System.out.println("");
	
	System.out.println("modification du 2e etudiant :" + stub.modifier(2, null, null, "SIR"));
	
	System.out.println("Informations du 2e etudiant :" + stub.searchId(2));
	
	System.out.println("");
	
	System.out.println("Suppression du 1e etudiant");
	stub.suppression(1);
	
	System.out.println("Affichage de la liste :");
	System.out.println(stub.tous());
		
	}
}
