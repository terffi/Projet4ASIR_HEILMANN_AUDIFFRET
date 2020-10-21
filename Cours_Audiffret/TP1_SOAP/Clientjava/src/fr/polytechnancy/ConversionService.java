
package fr.polytechnancy;

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
@WebServiceClient(name = "ConversionService", targetNamespace = "http://www.polytechNancy.fr", wsdlLocation = "file:/C:/Users/Emmanuelle%20CAUQUELIN/OneDrive/4A/Clientjava/src/ConversionService.wsdl")
public class ConversionService
    extends Service
{

    private final static URL CONVERSIONSERVICE_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(fr.polytechnancy.ConversionService.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = fr.polytechnancy.ConversionService.class.getResource(".");
            url = new URL(baseUrl, "file:/C:/Users/Emmanuelle%20CAUQUELIN/OneDrive/4A/Clientjava/src/ConversionService.wsdl");
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: 'file:/C:/Users/Emmanuelle%20CAUQUELIN/OneDrive/4A/Clientjava/src/ConversionService.wsdl', retrying as a local file");
            logger.warning(e.getMessage());
        }
        CONVERSIONSERVICE_WSDL_LOCATION = url;
    }

    public ConversionService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ConversionService() {
        super(CONVERSIONSERVICE_WSDL_LOCATION, new QName("http://www.polytechNancy.fr", "ConversionService"));
    }

    /**
     * 
     * @return
     *     returns Conversion
     */
    @WebEndpoint(name = "ConversionPort")
    public Conversion getConversionPort() {
        return super.getPort(new QName("http://www.polytechNancy.fr", "ConversionPort"), Conversion.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Conversion
     */
    @WebEndpoint(name = "ConversionPort")
    public Conversion getConversionPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://www.polytechNancy.fr", "ConversionPort"), Conversion.class, features);
    }

}
