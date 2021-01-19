import java.io.File;
import java.sql.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

public class Application {

	
	public static void main(String[] args) throws Exception{
		
		JAXBContext jc = JAXBContext.newInstance(Personne.class);
		Personne p_1 = new Personne("Youcef", "Augustin", new Date(17,3,2017));
		Marshaller m = jc.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		m.marshal(p_1, new File("client.xml"));
	}
}
