
package gestion;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the gestion package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AjoutCompteResponse_QNAME = new QName("http://gestion/", "ajoutCompteResponse");
    private final static QName _Tous_QNAME = new QName("http://gestion/", "tous");
    private final static QName _TousResponse_QNAME = new QName("http://gestion/", "tousResponse");
    private final static QName _SearchCompteResponse_QNAME = new QName("http://gestion/", "SearchCompteResponse");
    private final static QName _Compte_QNAME = new QName("http://gestion/", "compte");
    private final static QName _Initialisation_QNAME = new QName("http://gestion/", "initialisation");
    private final static QName _SearchCompte_QNAME = new QName("http://gestion/", "SearchCompte");
    private final static QName _AjoutCompte_QNAME = new QName("http://gestion/", "ajoutCompte");
    private final static QName _InitialisationResponse_QNAME = new QName("http://gestion/", "initialisationResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: gestion
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SearchCompteResponse }
     * 
     */
    public SearchCompteResponse createSearchCompteResponse() {
        return new SearchCompteResponse();
    }

    /**
     * Create an instance of {@link TousResponse }
     * 
     */
    public TousResponse createTousResponse() {
        return new TousResponse();
    }

    /**
     * Create an instance of {@link Tous }
     * 
     */
    public Tous createTous() {
        return new Tous();
    }

    /**
     * Create an instance of {@link AjoutCompteResponse }
     * 
     */
    public AjoutCompteResponse createAjoutCompteResponse() {
        return new AjoutCompteResponse();
    }

    /**
     * Create an instance of {@link SearchCompte }
     * 
     */
    public SearchCompte createSearchCompte() {
        return new SearchCompte();
    }

    /**
     * Create an instance of {@link AjoutCompte }
     * 
     */
    public AjoutCompte createAjoutCompte() {
        return new AjoutCompte();
    }

    /**
     * Create an instance of {@link Compte }
     * 
     */
    public Compte createCompte() {
        return new Compte();
    }

    /**
     * Create an instance of {@link InitialisationResponse }
     * 
     */
    public InitialisationResponse createInitialisationResponse() {
        return new InitialisationResponse();
    }

    /**
     * Create an instance of {@link Initialisation }
     * 
     */
    public Initialisation createInitialisation() {
        return new Initialisation();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AjoutCompteResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestion/", name = "ajoutCompteResponse")
    public JAXBElement<AjoutCompteResponse> createAjoutCompteResponse(AjoutCompteResponse value) {
        return new JAXBElement<AjoutCompteResponse>(_AjoutCompteResponse_QNAME, AjoutCompteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Tous }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestion/", name = "tous")
    public JAXBElement<Tous> createTous(Tous value) {
        return new JAXBElement<Tous>(_Tous_QNAME, Tous.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TousResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestion/", name = "tousResponse")
    public JAXBElement<TousResponse> createTousResponse(TousResponse value) {
        return new JAXBElement<TousResponse>(_TousResponse_QNAME, TousResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchCompteResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestion/", name = "SearchCompteResponse")
    public JAXBElement<SearchCompteResponse> createSearchCompteResponse(SearchCompteResponse value) {
        return new JAXBElement<SearchCompteResponse>(_SearchCompteResponse_QNAME, SearchCompteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Compte }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestion/", name = "compte")
    public JAXBElement<Compte> createCompte(Compte value) {
        return new JAXBElement<Compte>(_Compte_QNAME, Compte.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Initialisation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestion/", name = "initialisation")
    public JAXBElement<Initialisation> createInitialisation(Initialisation value) {
        return new JAXBElement<Initialisation>(_Initialisation_QNAME, Initialisation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchCompte }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestion/", name = "SearchCompte")
    public JAXBElement<SearchCompte> createSearchCompte(SearchCompte value) {
        return new JAXBElement<SearchCompte>(_SearchCompte_QNAME, SearchCompte.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AjoutCompte }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestion/", name = "ajoutCompte")
    public JAXBElement<AjoutCompte> createAjoutCompte(AjoutCompte value) {
        return new JAXBElement<AjoutCompte>(_AjoutCompte_QNAME, AjoutCompte.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InitialisationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestion/", name = "initialisationResponse")
    public JAXBElement<InitialisationResponse> createInitialisationResponse(InitialisationResponse value) {
        return new JAXBElement<InitialisationResponse>(_InitialisationResponse_QNAME, InitialisationResponse.class, null, value);
    }

}
