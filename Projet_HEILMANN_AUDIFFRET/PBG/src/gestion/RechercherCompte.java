
package gestion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for rechercherCompte complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="rechercherCompte">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="motCle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rechercherCompte", propOrder = {
    "motCle"
})
public class RechercherCompte {

    protected String motCle;

    /**
     * Gets the value of the motCle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMotCle() {
        return motCle;
    }

    /**
     * Sets the value of the motCle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMotCle(String value) {
        this.motCle = value;
    }

}
