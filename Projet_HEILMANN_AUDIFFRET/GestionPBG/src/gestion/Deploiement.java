package gestion;

import javax.xml.ws.Endpoint;

public class Deploiement {
	
	public static void main(String[] args) {
		String url = "http://localhost:9090/GestionPBG";
		Endpoint.publish(url, new GestionPBG());
		
		System.out.println(url+"?wsdl");
	}

}
