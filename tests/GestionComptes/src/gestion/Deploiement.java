package gestion;

import javax.xml.ws.Endpoint;

public class Deploiement {
	
	public static void main(String[] args) {
		String url = "http://localhost:9090/";
		Endpoint.publish(url, new GestionComptes());
		
		System.out.println(url+"?wsdl");
	}

}
