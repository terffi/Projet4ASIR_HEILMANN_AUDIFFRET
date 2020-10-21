import java.io.File;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

public class Serialisation {

	public static void main(String[] args) {
		
		try {
			JAXBContext jc = JAXBContext.newInstance(Personne.class);
			@SuppressWarnings("deprecation")
			Personne p_1 = new Personne("Youcef", "Augustin", new Date(17,3,2017));
			Marshaller m = jc.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(p_1, new File("resultat.xml"));
			
		} catch (Exception ex) {
			Logger.getLogger(Serialiser.class.getName()).log(Level.SEVERE,null,ex);
			
		}

	}

}
