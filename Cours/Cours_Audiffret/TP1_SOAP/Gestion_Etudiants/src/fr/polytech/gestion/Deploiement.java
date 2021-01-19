package fr.polytech.gestion;

import javax.xml.ws.Endpoint;

public class Deploiement {
	
	public static void main(String[] args) {
		String url = "http://localhost:9090/";
		Endpoint.publish(url, new GestionEtudiants());
		
		System.out.println(url+"?wsdl");
	}

}
