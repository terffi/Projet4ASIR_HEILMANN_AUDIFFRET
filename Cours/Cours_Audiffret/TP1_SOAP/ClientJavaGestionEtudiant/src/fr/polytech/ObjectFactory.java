
package fr.polytech;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fr.polytech package. 
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

    private final static QName _SearchId_QNAME = new QName("http://www.polytech.fr", "SearchId");
    private final static QName _Ajout_QNAME = new QName("http://www.polytech.fr", "ajout");
    private final static QName _TousResponse_QNAME = new QName("http://www.polytech.fr", "tousResponse");
    private final static QName _ModifierResponse_QNAME = new QName("http://www.polytech.fr", "modifierResponse");
    private final static QName _Tous_QNAME = new QName("http://www.polytech.fr", "tous");
    private final static QName _AjoutResponse_QNAME = new QName("http://www.polytech.fr", "ajoutResponse");
    private final static QName _InitialisationResponse_QNAME = new QName("http://www.polytech.fr", "initialisationResponse");
    private final static QName _SuppressionResponse_QNAME = new QName("http://www.polytech.fr", "suppressionResponse");
    private final static QName _Modifier_QNAME = new QName("http://www.polytech.fr", "modifier");
    private final static QName _Suppression_QNAME = new QName("http://www.polytech.fr", "suppression");
    private final static QName _SearchIdResponse_QNAME = new QName("http://www.polytech.fr", "SearchIdResponse");
    private final static QName _Initialisation_QNAME = new QName("http://www.polytech.fr", "initialisation");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fr.polytech
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Ajout }
     * 
     */
    public Ajout createAjout() {
        return new Ajout();
    }

    /**
     * Create an instance of {@link SearchIdResponse }
     * 
     */
    public SearchIdResponse createSearchIdResponse() {
        return new SearchIdResponse();
    }

    /**
     * Create an instance of {@link SuppressionResponse }
     * 
     */
    public SuppressionResponse createSuppressionResponse() {
        return new SuppressionResponse();
    }

    /**
     * Create an instance of {@link SearchId }
     * 
     */
    public SearchId createSearchId() {
        return new SearchId();
    }

    /**
     * Create an instance of {@link AjoutResponse }
     * 
     */
    public AjoutResponse createAjoutResponse() {
        return new AjoutResponse();
    }

    /**
     * Create an instance of {@link TousResponse }
     * 
     */
    public TousResponse createTousResponse() {
        return new TousResponse();
    }

    /**
     * Create an instance of {@link InitialisationResponse }
     * 
     */
    public InitialisationResponse createInitialisationResponse() {
        return new InitialisationResponse();
    }

    /**
     * Create an instance of {@link Tous }
     * 
     */
    public Tous createTous() {
        return new Tous();
    }

    /**
     * Create an instance of {@link Suppression }
     * 
     */
    public Suppression createSuppression() {
        return new Suppression();
    }

    /**
     * Create an instance of {@link ModifierResponse }
     * 
     */
    public ModifierResponse createModifierResponse() {
        return new ModifierResponse();
    }

    /**
     * Create an instance of {@link Initialisation }
     * 
     */
    public Initialisation createInitialisation() {
        return new Initialisation();
    }

    /**
     * Create an instance of {@link Modifier }
     * 
     */
    public Modifier createModifier() {
        return new Modifier();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.fr", name = "SearchId")
    public JAXBElement<SearchId> createSearchId(SearchId value) {
        return new JAXBElement<SearchId>(_SearchId_QNAME, SearchId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Ajout }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.fr", name = "ajout")
    public JAXBElement<Ajout> createAjout(Ajout value) {
        return new JAXBElement<Ajout>(_Ajout_QNAME, Ajout.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TousResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.fr", name = "tousResponse")
    public JAXBElement<TousResponse> createTousResponse(TousResponse value) {
        return new JAXBElement<TousResponse>(_TousResponse_QNAME, TousResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModifierResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.fr", name = "modifierResponse")
    public JAXBElement<ModifierResponse> createModifierResponse(ModifierResponse value) {
        return new JAXBElement<ModifierResponse>(_ModifierResponse_QNAME, ModifierResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Tous }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.fr", name = "tous")
    public JAXBElement<Tous> createTous(Tous value) {
        return new JAXBElement<Tous>(_Tous_QNAME, Tous.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AjoutResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.fr", name = "ajoutResponse")
    public JAXBElement<AjoutResponse> createAjoutResponse(AjoutResponse value) {
        return new JAXBElement<AjoutResponse>(_AjoutResponse_QNAME, AjoutResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InitialisationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.fr", name = "initialisationResponse")
    public JAXBElement<InitialisationResponse> createInitialisationResponse(InitialisationResponse value) {
        return new JAXBElement<InitialisationResponse>(_InitialisationResponse_QNAME, InitialisationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SuppressionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.fr", name = "suppressionResponse")
    public JAXBElement<SuppressionResponse> createSuppressionResponse(SuppressionResponse value) {
        return new JAXBElement<SuppressionResponse>(_SuppressionResponse_QNAME, SuppressionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Modifier }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.fr", name = "modifier")
    public JAXBElement<Modifier> createModifier(Modifier value) {
        return new JAXBElement<Modifier>(_Modifier_QNAME, Modifier.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Suppression }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.fr", name = "suppression")
    public JAXBElement<Suppression> createSuppression(Suppression value) {
        return new JAXBElement<Suppression>(_Suppression_QNAME, Suppression.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.fr", name = "SearchIdResponse")
    public JAXBElement<SearchIdResponse> createSearchIdResponse(SearchIdResponse value) {
        return new JAXBElement<SearchIdResponse>(_SearchIdResponse_QNAME, SearchIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Initialisation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.fr", name = "initialisation")
    public JAXBElement<Initialisation> createInitialisation(Initialisation value) {
        return new JAXBElement<Initialisation>(_Initialisation_QNAME, Initialisation.class, null, value);
    }

}
