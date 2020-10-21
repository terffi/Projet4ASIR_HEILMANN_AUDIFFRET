package webservice;

import javax.xml.ws.Endpoint;

public class Publication {

	public static void main(String[] args) {
	String url = "http://localhost:8989/wsConversion";
	Conversion c = new Conversion();
	Endpoint.publish(url, c);
	System.out.println("Votre service est publié");
	System.out.println("Adresse du WSD http://localhost:8989/wsConversion/?wsdl");
	
	
	}

}