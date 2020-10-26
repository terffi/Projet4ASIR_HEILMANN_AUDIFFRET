
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

    private final static QName _CreerListeResponse_QNAME = new QName("http://www.polytech.fr", "creer_listeResponse");
    private final static QName _RechercheResponse_QNAME = new QName("http://www.polytech.fr", "rechercheResponse");
    private final static QName _Ajout_QNAME = new QName("http://www.polytech.fr", "ajout");
    private final static QName _TousResponse_QNAME = new QName("http://www.polytech.fr", "tousResponse");
    private final static QName _SupprimerResponse_QNAME = new QName("http://www.polytech.fr", "supprimerResponse");
    private final static QName _Tous_QNAME = new QName("http://www.polytech.fr", "tous");
    private final static QName _AjoutResponse_QNAME = new QName("http://www.polytech.fr", "ajoutResponse");
    private final static QName _Recherche_QNAME = new QName("http://www.polytech.fr", "recherche");
    private final static QName _Supprimer_QNAME = new QName("http://www.polytech.fr", "supprimer");
    private final static QName _CreerListe_QNAME = new QName("http://www.polytech.fr", "creer_liste");
    private final static QName _Etudiant_QNAME = new QName("http://www.polytech.fr", "etudiant");

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
     * Create an instance of {@link SupprimerResponse }
     * 
     */
    public SupprimerResponse createSupprimerResponse() {
        return new SupprimerResponse();
    }

    /**
     * Create an instance of {@link Tous }
     * 
     */
    public Tous createTous() {
        return new Tous();
    }

    /**
     * Create an instance of {@link CreerListe }
     * 
     */
    public CreerListe createCreerListe() {
        return new CreerListe();
    }

    /**
     * Create an instance of {@link AjoutResponse }
     * 
     */
    public AjoutResponse createAjoutResponse() {
        return new AjoutResponse();
    }

    /**
     * Create an instance of {@link Etudiant }
     * 
     */
    public Etudiant createEtudiant() {
        return new Etudiant();
    }

    /**
     * Create an instance of {@link TousResponse }
     * 
     */
    public TousResponse createTousResponse() {
        return new TousResponse();
    }

    /**
     * Create an instance of {@link Supprimer }
     * 
     */
    public Supprimer createSupprimer() {
        return new Supprimer();
    }

    /**
     * Create an instance of {@link Recherche }
     * 
     */
    public Recherche createRecherche() {
        return new Recherche();
    }

    /**
     * Create an instance of {@link CreerListeResponse }
     * 
     */
    public CreerListeResponse createCreerListeResponse() {
        return new CreerListeResponse();
    }

    /**
     * Create an instance of {@link RechercheResponse }
     * 
     */
    public RechercheResponse createRechercheResponse() {
        return new RechercheResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreerListeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.fr", name = "creer_listeResponse")
    public JAXBElement<CreerListeResponse> createCreerListeResponse(CreerListeResponse value) {
        return new JAXBElement<CreerListeResponse>(_CreerListeResponse_QNAME, CreerListeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RechercheResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.fr", name = "rechercheResponse")
    public JAXBElement<RechercheResponse> createRechercheResponse(RechercheResponse value) {
        return new JAXBElement<RechercheResponse>(_RechercheResponse_QNAME, RechercheResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link SupprimerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.fr", name = "supprimerResponse")
    public JAXBElement<SupprimerResponse> createSupprimerResponse(SupprimerResponse value) {
        return new JAXBElement<SupprimerResponse>(_SupprimerResponse_QNAME, SupprimerResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link Recherche }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.fr", name = "recherche")
    public JAXBElement<Recherche> createRecherche(Recherche value) {
        return new JAXBElement<Recherche>(_Recherche_QNAME, Recherche.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Supprimer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.fr", name = "supprimer")
    public JAXBElement<Supprimer> createSupprimer(Supprimer value) {
        return new JAXBElement<Supprimer>(_Supprimer_QNAME, Supprimer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreerListe }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.fr", name = "creer_liste")
    public JAXBElement<CreerListe> createCreerListe(CreerListe value) {
        return new JAXBElement<CreerListe>(_CreerListe_QNAME, CreerListe.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Etudiant }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.fr", name = "etudiant")
    public JAXBElement<Etudiant> createEtudiant(Etudiant value) {
        return new JAXBElement<Etudiant>(_Etudiant_QNAME, Etudiant.class, null, value);
    }

}
