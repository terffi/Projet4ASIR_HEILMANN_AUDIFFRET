
package fr.polytechnancy;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebService(name = "Conversion", targetNamespace = "http://www.polytechNancy.fr")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface Conversion {


    /**
     * 
     * @param mt
     * @return
     *     returns double
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "convertir", targetNamespace = "http://www.polytechNancy.fr", className = "fr.polytechnancy.Convertir")
    @ResponseWrapper(localName = "convertirResponse", targetNamespace = "http://www.polytechNancy.fr", className = "fr.polytechnancy.ConvertirResponse")
    public double convertir(
        @WebParam(name = "mt", targetNamespace = "")
        double mt);

}
