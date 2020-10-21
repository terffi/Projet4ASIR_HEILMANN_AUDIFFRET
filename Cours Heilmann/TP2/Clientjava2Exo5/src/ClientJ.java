import webservice2.ServiceEtudiant;
import webservice2.Etudiant;


public class ClientJ {

	public static void main(String[] args) {
		// définition d'un stub ....
		
		ServiceEtudiant stub = new ServiceEtudiant().getEtudiantPorts();
		System.out.println("réponse renvoyée par le serveur est "+stub.convertir(11));

	}

}
