import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

public class Desirialisation {

	public static void main(String[] Args) {
		try {
			JAXBContext jc = JAXBContext.newInstance(Personne.class);
			Unmarshaller um = jc.createUnmarshaller();
			Personne p = (Personne)um.unmarshal(new File("client.xml"));
			System.out.println(p.getNom());
			
		} catch (Exception ex) {
			Logger.getLogger(Desirialisation.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
