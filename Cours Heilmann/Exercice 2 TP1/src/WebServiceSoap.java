import javax.jws.WebService;

@WebService(targetNamespace = "http://www.polytechnancy.fr")
public class WebServiceSoap {

	public String afficher() {
		return "Mon premier service web !";
	}
}
