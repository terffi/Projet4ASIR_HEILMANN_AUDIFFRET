package webservice2;

import javax.xml.ws.Endpoint;

public class DeploiementEtudiant {

	public static void main(String[] args) {
	String url = "http://localhost:8585/wsServiceEtudiant";
	ServiceEtudiant s = new ServiceEtudiant();
	Endpoint.publish(url, s);
	System.out.println("Votre service est publi�");
	System.out.println("Adresse du WSD http://localhost:8585/wsServiceEtudiant/?wsdl");
	
	
	}

}