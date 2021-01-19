import fr.polytechnancy.Conversion;
import fr.polytechnancy.ConversionService;

public class ClientJ {

	public static void main(String[] args) {
	// définition d'un stub ....
		Conversion stub = new ConversionService().getConversionPort();
		System.out.println("réponse renvoyée par le serveur est "+stub.convertir(11));
	}

}
