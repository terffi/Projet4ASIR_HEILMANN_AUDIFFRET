import fr.polytechnancy.Conversion;
import fr.polytechnancy.ConversionService;

public class ClientJ {

	public static void main(String[] args) {
	// d�finition d'un stub ....
		Conversion stub = new ConversionService().getConversionPort();
		System.out.println("r�ponse renvoy�e par le serveur est "+stub.convertir(11));
	}

}
