import javax.xml.ws.Endpoint;

public class Deploiement {

	public static void main(String[] Args) {
		String url = "http://localhost:8989/";
		 Endpoint.publish(url, new WebServiceSoap());
	     System.out.println("Votre service est d�ploy� et le WSDL, Web Service Description Language est accessible � :");
	     System.out.println(url+"?wsdl");
	}
}
