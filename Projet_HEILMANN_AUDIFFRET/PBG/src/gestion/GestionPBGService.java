
package gestion;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebServiceClient(name = "GestionPBGService", targetNamespace = "http://gestion/", wsdlLocation = "file:/D:/sam54/Documents/GitHub/Projet4ASIR_HEILMANN_AUDIFFRET/Projet_HEILMANN_AUDIFFRET/PBG/src/GestionPBGService.wsdl")
public class GestionPBGService
    extends Service
{

    private final static URL GESTIONPBGSERVICE_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(gestion.GestionPBGService.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = gestion.GestionPBGService.class.getResource(".");
            url = new URL(baseUrl, "file:/D:/sam54/Documents/GitHub/Projet4ASIR_HEILMANN_AUDIFFRET/Projet_HEILMANN_AUDIFFRET/PBG/src/GestionPBGService.wsdl");
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: 'file:/D:/sam54/Documents/GitHub/Projet4ASIR_HEILMANN_AUDIFFRET/Projet_HEILMANN_AUDIFFRET/PBG/src/GestionPBGService.wsdl', retrying as a local file");
            logger.warning(e.getMessage());
        }
        GESTIONPBGSERVICE_WSDL_LOCATION = url;
    }

    public GestionPBGService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public GestionPBGService() {
        super(GESTIONPBGSERVICE_WSDL_LOCATION, new QName("http://gestion/", "GestionPBGService"));
    }

    /**
     * 
     * @return
     *     returns GestionPBG
     */
    @WebEndpoint(name = "GestionPBGPort")
    public GestionPBG getGestionPBGPort() {
        return super.getPort(new QName("http://gestion/", "GestionPBGPort"), GestionPBG.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns GestionPBG
     */
    @WebEndpoint(name = "GestionPBGPort")
    public GestionPBG getGestionPBGPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://gestion/", "GestionPBGPort"), GestionPBG.class, features);
    }

}
