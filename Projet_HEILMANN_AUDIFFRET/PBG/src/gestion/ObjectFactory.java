
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

    private final static QName _ModifCompte_QNAME = new QName("http://gestion/", "modifCompte");
    private final static QName _AfficherComptesResponse_QNAME = new QName("http://gestion/", "afficherComptesResponse");
    private final static QName _AjoutEvent_QNAME = new QName("http://gestion/", "ajoutEvent");
    private final static QName _ModifImageEventResponse_QNAME = new QName("http://gestion/", "modifImageEventResponse");
    private final static QName _ModifMdpCompte_QNAME = new QName("http://gestion/", "modifMdpCompte");
    private final static QName _CompteValideResponse_QNAME = new QName("http://gestion/", "compteValideResponse");
    private final static QName _AfficherEvents_QNAME = new QName("http://gestion/", "afficherEvents");
    private final static QName _SupprCompte_QNAME = new QName("http://gestion/", "supprCompte");
    private final static QName _InitialisationResponse_QNAME = new QName("http://gestion/", "initialisationResponse");
    private final static QName _SupprEvent_QNAME = new QName("http://gestion/", "supprEvent");
    private final static QName _AfficherParticipantsResponse_QNAME = new QName("http://gestion/", "afficherParticipantsResponse");
    private final static QName _RechercheCompteResponse_QNAME = new QName("http://gestion/", "rechercheCompteResponse");
    private final static QName _Participant_QNAME = new QName("http://gestion/", "participant");
    private final static QName _IsAdmin_QNAME = new QName("http://gestion/", "isAdmin");
    private final static QName _ModifMdpCompteResponse_QNAME = new QName("http://gestion/", "modifMdpCompteResponse");
    private final static QName _AfficherParticipationsResponse_QNAME = new QName("http://gestion/", "afficherParticipationsResponse");
    private final static QName _AjoutCompte_QNAME = new QName("http://gestion/", "ajoutCompte");
    private final static QName _AjoutEventResponse_QNAME = new QName("http://gestion/", "ajoutEventResponse");
    private final static QName _SupprCompteResponse_QNAME = new QName("http://gestion/", "supprCompteResponse");
    private final static QName _IsAdminResponse_QNAME = new QName("http://gestion/", "isAdminResponse");
    private final static QName _CompteValide_QNAME = new QName("http://gestion/", "compteValide");
    private final static QName _RechercheEvent_QNAME = new QName("http://gestion/", "rechercheEvent");
    private final static QName _RechercheEventResponse_QNAME = new QName("http://gestion/", "rechercheEventResponse");
    private final static QName _AjouterParticipationResponse_QNAME = new QName("http://gestion/", "ajouterParticipationResponse");
    private final static QName _AfficherComptes_QNAME = new QName("http://gestion/", "afficherComptes");
    private final static QName _ModifImageEvent_QNAME = new QName("http://gestion/", "modifImageEvent");
    private final static QName _AjoutCompteResponse_QNAME = new QName("http://gestion/", "ajoutCompteResponse");
    private final static QName _SetAdmin_QNAME = new QName("http://gestion/", "setAdmin");
    private final static QName _SetAdminResponse_QNAME = new QName("http://gestion/", "setAdminResponse");
    private final static QName _AfficherParticipations_QNAME = new QName("http://gestion/", "afficherParticipations");
    private final static QName _ModifCompteResponse_QNAME = new QName("http://gestion/", "modifCompteResponse");
    private final static QName _AfficherEventsResponse_QNAME = new QName("http://gestion/", "afficherEventsResponse");
    private final static QName _ModifEvent_QNAME = new QName("http://gestion/", "modifEvent");
    private final static QName _ModifEventResponse_QNAME = new QName("http://gestion/", "modifEventResponse");
    private final static QName _SupprEventResponse_QNAME = new QName("http://gestion/", "supprEventResponse");
    private final static QName _RechercheCompte_QNAME = new QName("http://gestion/", "rechercheCompte");
    private final static QName _Compte_QNAME = new QName("http://gestion/", "compte");
    private final static QName _Event_QNAME = new QName("http://gestion/", "event");
    private final static QName _Initialisation_QNAME = new QName("http://gestion/", "initialisation");
    private final static QName _AfficherParticipants_QNAME = new QName("http://gestion/", "afficherParticipants");
    private final static QName _Connexion_QNAME = new QName("http://gestion/", "connexion");
    private final static QName _ConnexionResponse_QNAME = new QName("http://gestion/", "connexionResponse");
    private final static QName _AjouterParticipation_QNAME = new QName("http://gestion/", "ajouterParticipation");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: gestion
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AjouterParticipation }
     * 
     */
    public AjouterParticipation createAjouterParticipation() {
        return new AjouterParticipation();
    }

    /**
     * Create an instance of {@link AfficherEventsResponse }
     * 
     */
    public AfficherEventsResponse createAfficherEventsResponse() {
        return new AfficherEventsResponse();
    }

    /**
     * Create an instance of {@link AfficherParticipants }
     * 
     */
    public AfficherParticipants createAfficherParticipants() {
        return new AfficherParticipants();
    }

    /**
     * Create an instance of {@link Connexion }
     * 
     */
    public Connexion createConnexion() {
        return new Connexion();
    }

    /**
     * Create an instance of {@link RechercheEventResponse }
     * 
     */
    public RechercheEventResponse createRechercheEventResponse() {
        return new RechercheEventResponse();
    }

    /**
     * Create an instance of {@link AfficherParticipations }
     * 
     */
    public AfficherParticipations createAfficherParticipations() {
        return new AfficherParticipations();
    }

    /**
     * Create an instance of {@link ModifEventResponse }
     * 
     */
    public ModifEventResponse createModifEventResponse() {
        return new ModifEventResponse();
    }

    /**
     * Create an instance of {@link CompteValideResponse }
     * 
     */
    public CompteValideResponse createCompteValideResponse() {
        return new CompteValideResponse();
    }

    /**
     * Create an instance of {@link CompteValide }
     * 
     */
    public CompteValide createCompteValide() {
        return new CompteValide();
    }

    /**
     * Create an instance of {@link ModifCompte }
     * 
     */
    public ModifCompte createModifCompte() {
        return new ModifCompte();
    }

    /**
     * Create an instance of {@link IsAdmin }
     * 
     */
    public IsAdmin createIsAdmin() {
        return new IsAdmin();
    }

    /**
     * Create an instance of {@link Initialisation }
     * 
     */
    public Initialisation createInitialisation() {
        return new Initialisation();
    }

    /**
     * Create an instance of {@link AjoutEvent }
     * 
     */
    public AjoutEvent createAjoutEvent() {
        return new AjoutEvent();
    }

    /**
     * Create an instance of {@link Event }
     * 
     */
    public Event createEvent() {
        return new Event();
    }

    /**
     * Create an instance of {@link AfficherParticipantsResponse }
     * 
     */
    public AfficherParticipantsResponse createAfficherParticipantsResponse() {
        return new AfficherParticipantsResponse();
    }

    /**
     * Create an instance of {@link ModifMdpCompteResponse }
     * 
     */
    public ModifMdpCompteResponse createModifMdpCompteResponse() {
        return new ModifMdpCompteResponse();
    }

    /**
     * Create an instance of {@link SupprEvent }
     * 
     */
    public SupprEvent createSupprEvent() {
        return new SupprEvent();
    }

    /**
     * Create an instance of {@link SetAdmin }
     * 
     */
    public SetAdmin createSetAdmin() {
        return new SetAdmin();
    }

    /**
     * Create an instance of {@link AjoutEventResponse }
     * 
     */
    public AjoutEventResponse createAjoutEventResponse() {
        return new AjoutEventResponse();
    }

    /**
     * Create an instance of {@link AjoutCompte }
     * 
     */
    public AjoutCompte createAjoutCompte() {
        return new AjoutCompte();
    }

    /**
     * Create an instance of {@link AjouterParticipationResponse }
     * 
     */
    public AjouterParticipationResponse createAjouterParticipationResponse() {
        return new AjouterParticipationResponse();
    }

    /**
     * Create an instance of {@link ConnexionResponse }
     * 
     */
    public ConnexionResponse createConnexionResponse() {
        return new ConnexionResponse();
    }

    /**
     * Create an instance of {@link AfficherComptesResponse }
     * 
     */
    public AfficherComptesResponse createAfficherComptesResponse() {
        return new AfficherComptesResponse();
    }

    /**
     * Create an instance of {@link Compte }
     * 
     */
    public Compte createCompte() {
        return new Compte();
    }

    /**
     * Create an instance of {@link ModifCompteResponse }
     * 
     */
    public ModifCompteResponse createModifCompteResponse() {
        return new ModifCompteResponse();
    }

    /**
     * Create an instance of {@link ModifImageEventResponse }
     * 
     */
    public ModifImageEventResponse createModifImageEventResponse() {
        return new ModifImageEventResponse();
    }

    /**
     * Create an instance of {@link Participant }
     * 
     */
    public Participant createParticipant() {
        return new Participant();
    }

    /**
     * Create an instance of {@link ModifMdpCompte }
     * 
     */
    public ModifMdpCompte createModifMdpCompte() {
        return new ModifMdpCompte();
    }

    /**
     * Create an instance of {@link InitialisationResponse }
     * 
     */
    public InitialisationResponse createInitialisationResponse() {
        return new InitialisationResponse();
    }

    /**
     * Create an instance of {@link SetAdminResponse }
     * 
     */
    public SetAdminResponse createSetAdminResponse() {
        return new SetAdminResponse();
    }

    /**
     * Create an instance of {@link ModifEvent }
     * 
     */
    public ModifEvent createModifEvent() {
        return new ModifEvent();
    }

    /**
     * Create an instance of {@link AfficherParticipationsResponse }
     * 
     */
    public AfficherParticipationsResponse createAfficherParticipationsResponse() {
        return new AfficherParticipationsResponse();
    }

    /**
     * Create an instance of {@link SupprCompteResponse }
     * 
     */
    public SupprCompteResponse createSupprCompteResponse() {
        return new SupprCompteResponse();
    }

    /**
     * Create an instance of {@link AjoutCompteResponse }
     * 
     */
    public AjoutCompteResponse createAjoutCompteResponse() {
        return new AjoutCompteResponse();
    }

    /**
     * Create an instance of {@link AfficherComptes }
     * 
     */
    public AfficherComptes createAfficherComptes() {
        return new AfficherComptes();
    }

    /**
     * Create an instance of {@link AfficherEvents }
     * 
     */
    public AfficherEvents createAfficherEvents() {
        return new AfficherEvents();
    }

    /**
     * Create an instance of {@link SupprCompte }
     * 
     */
    public SupprCompte createSupprCompte() {
        return new SupprCompte();
    }

    /**
     * Create an instance of {@link RechercheCompteResponse }
     * 
     */
    public RechercheCompteResponse createRechercheCompteResponse() {
        return new RechercheCompteResponse();
    }

    /**
     * Create an instance of {@link RechercheCompte }
     * 
     */
    public RechercheCompte createRechercheCompte() {
        return new RechercheCompte();
    }

    /**
     * Create an instance of {@link SupprEventResponse }
     * 
     */
    public SupprEventResponse createSupprEventResponse() {
        return new SupprEventResponse();
    }

    /**
     * Create an instance of {@link IsAdminResponse }
     * 
     */
    public IsAdminResponse createIsAdminResponse() {
        return new IsAdminResponse();
    }

    /**
     * Create an instance of {@link RechercheEvent }
     * 
     */
    public RechercheEvent createRechercheEvent() {
        return new RechercheEvent();
    }

    /**
     * Create an instance of {@link ModifImageEvent }
     * 
     */
    public ModifImageEvent createModifImageEvent() {
        return new ModifImageEvent();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModifCompte }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestion/", name = "modifCompte")
    public JAXBElement<ModifCompte> createModifCompte(ModifCompte value) {
        return new JAXBElement<ModifCompte>(_ModifCompte_QNAME, ModifCompte.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AfficherComptesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestion/", name = "afficherComptesResponse")
    public JAXBElement<AfficherComptesResponse> createAfficherComptesResponse(AfficherComptesResponse value) {
        return new JAXBElement<AfficherComptesResponse>(_AfficherComptesResponse_QNAME, AfficherComptesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AjoutEvent }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestion/", name = "ajoutEvent")
    public JAXBElement<AjoutEvent> createAjoutEvent(AjoutEvent value) {
        return new JAXBElement<AjoutEvent>(_AjoutEvent_QNAME, AjoutEvent.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModifImageEventResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestion/", name = "modifImageEventResponse")
    public JAXBElement<ModifImageEventResponse> createModifImageEventResponse(ModifImageEventResponse value) {
        return new JAXBElement<ModifImageEventResponse>(_ModifImageEventResponse_QNAME, ModifImageEventResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModifMdpCompte }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestion/", name = "modifMdpCompte")
    public JAXBElement<ModifMdpCompte> createModifMdpCompte(ModifMdpCompte value) {
        return new JAXBElement<ModifMdpCompte>(_ModifMdpCompte_QNAME, ModifMdpCompte.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CompteValideResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestion/", name = "compteValideResponse")
    public JAXBElement<CompteValideResponse> createCompteValideResponse(CompteValideResponse value) {
        return new JAXBElement<CompteValideResponse>(_CompteValideResponse_QNAME, CompteValideResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AfficherEvents }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestion/", name = "afficherEvents")
    public JAXBElement<AfficherEvents> createAfficherEvents(AfficherEvents value) {
        return new JAXBElement<AfficherEvents>(_AfficherEvents_QNAME, AfficherEvents.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SupprCompte }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestion/", name = "supprCompte")
    public JAXBElement<SupprCompte> createSupprCompte(SupprCompte value) {
        return new JAXBElement<SupprCompte>(_SupprCompte_QNAME, SupprCompte.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InitialisationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestion/", name = "initialisationResponse")
    public JAXBElement<InitialisationResponse> createInitialisationResponse(InitialisationResponse value) {
        return new JAXBElement<InitialisationResponse>(_InitialisationResponse_QNAME, InitialisationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SupprEvent }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestion/", name = "supprEvent")
    public JAXBElement<SupprEvent> createSupprEvent(SupprEvent value) {
        return new JAXBElement<SupprEvent>(_SupprEvent_QNAME, SupprEvent.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AfficherParticipantsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestion/", name = "afficherParticipantsResponse")
    public JAXBElement<AfficherParticipantsResponse> createAfficherParticipantsResponse(AfficherParticipantsResponse value) {
        return new JAXBElement<AfficherParticipantsResponse>(_AfficherParticipantsResponse_QNAME, AfficherParticipantsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RechercheCompteResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestion/", name = "rechercheCompteResponse")
    public JAXBElement<RechercheCompteResponse> createRechercheCompteResponse(RechercheCompteResponse value) {
        return new JAXBElement<RechercheCompteResponse>(_RechercheCompteResponse_QNAME, RechercheCompteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Participant }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestion/", name = "participant")
    public JAXBElement<Participant> createParticipant(Participant value) {
        return new JAXBElement<Participant>(_Participant_QNAME, Participant.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IsAdmin }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestion/", name = "isAdmin")
    public JAXBElement<IsAdmin> createIsAdmin(IsAdmin value) {
        return new JAXBElement<IsAdmin>(_IsAdmin_QNAME, IsAdmin.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModifMdpCompteResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestion/", name = "modifMdpCompteResponse")
    public JAXBElement<ModifMdpCompteResponse> createModifMdpCompteResponse(ModifMdpCompteResponse value) {
        return new JAXBElement<ModifMdpCompteResponse>(_ModifMdpCompteResponse_QNAME, ModifMdpCompteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AfficherParticipationsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestion/", name = "afficherParticipationsResponse")
    public JAXBElement<AfficherParticipationsResponse> createAfficherParticipationsResponse(AfficherParticipationsResponse value) {
        return new JAXBElement<AfficherParticipationsResponse>(_AfficherParticipationsResponse_QNAME, AfficherParticipationsResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link AjoutEventResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestion/", name = "ajoutEventResponse")
    public JAXBElement<AjoutEventResponse> createAjoutEventResponse(AjoutEventResponse value) {
        return new JAXBElement<AjoutEventResponse>(_AjoutEventResponse_QNAME, AjoutEventResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SupprCompteResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestion/", name = "supprCompteResponse")
    public JAXBElement<SupprCompteResponse> createSupprCompteResponse(SupprCompteResponse value) {
        return new JAXBElement<SupprCompteResponse>(_SupprCompteResponse_QNAME, SupprCompteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IsAdminResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestion/", name = "isAdminResponse")
    public JAXBElement<IsAdminResponse> createIsAdminResponse(IsAdminResponse value) {
        return new JAXBElement<IsAdminResponse>(_IsAdminResponse_QNAME, IsAdminResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CompteValide }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestion/", name = "compteValide")
    public JAXBElement<CompteValide> createCompteValide(CompteValide value) {
        return new JAXBElement<CompteValide>(_CompteValide_QNAME, CompteValide.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RechercheEvent }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestion/", name = "rechercheEvent")
    public JAXBElement<RechercheEvent> createRechercheEvent(RechercheEvent value) {
        return new JAXBElement<RechercheEvent>(_RechercheEvent_QNAME, RechercheEvent.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RechercheEventResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestion/", name = "rechercheEventResponse")
    public JAXBElement<RechercheEventResponse> createRechercheEventResponse(RechercheEventResponse value) {
        return new JAXBElement<RechercheEventResponse>(_RechercheEventResponse_QNAME, RechercheEventResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AjouterParticipationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestion/", name = "ajouterParticipationResponse")
    public JAXBElement<AjouterParticipationResponse> createAjouterParticipationResponse(AjouterParticipationResponse value) {
        return new JAXBElement<AjouterParticipationResponse>(_AjouterParticipationResponse_QNAME, AjouterParticipationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AfficherComptes }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestion/", name = "afficherComptes")
    public JAXBElement<AfficherComptes> createAfficherComptes(AfficherComptes value) {
        return new JAXBElement<AfficherComptes>(_AfficherComptes_QNAME, AfficherComptes.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModifImageEvent }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestion/", name = "modifImageEvent")
    public JAXBElement<ModifImageEvent> createModifImageEvent(ModifImageEvent value) {
        return new JAXBElement<ModifImageEvent>(_ModifImageEvent_QNAME, ModifImageEvent.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link SetAdmin }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestion/", name = "setAdmin")
    public JAXBElement<SetAdmin> createSetAdmin(SetAdmin value) {
        return new JAXBElement<SetAdmin>(_SetAdmin_QNAME, SetAdmin.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetAdminResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestion/", name = "setAdminResponse")
    public JAXBElement<SetAdminResponse> createSetAdminResponse(SetAdminResponse value) {
        return new JAXBElement<SetAdminResponse>(_SetAdminResponse_QNAME, SetAdminResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AfficherParticipations }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestion/", name = "afficherParticipations")
    public JAXBElement<AfficherParticipations> createAfficherParticipations(AfficherParticipations value) {
        return new JAXBElement<AfficherParticipations>(_AfficherParticipations_QNAME, AfficherParticipations.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModifCompteResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestion/", name = "modifCompteResponse")
    public JAXBElement<ModifCompteResponse> createModifCompteResponse(ModifCompteResponse value) {
        return new JAXBElement<ModifCompteResponse>(_ModifCompteResponse_QNAME, ModifCompteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AfficherEventsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestion/", name = "afficherEventsResponse")
    public JAXBElement<AfficherEventsResponse> createAfficherEventsResponse(AfficherEventsResponse value) {
        return new JAXBElement<AfficherEventsResponse>(_AfficherEventsResponse_QNAME, AfficherEventsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModifEvent }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestion/", name = "modifEvent")
    public JAXBElement<ModifEvent> createModifEvent(ModifEvent value) {
        return new JAXBElement<ModifEvent>(_ModifEvent_QNAME, ModifEvent.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModifEventResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestion/", name = "modifEventResponse")
    public JAXBElement<ModifEventResponse> createModifEventResponse(ModifEventResponse value) {
        return new JAXBElement<ModifEventResponse>(_ModifEventResponse_QNAME, ModifEventResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SupprEventResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestion/", name = "supprEventResponse")
    public JAXBElement<SupprEventResponse> createSupprEventResponse(SupprEventResponse value) {
        return new JAXBElement<SupprEventResponse>(_SupprEventResponse_QNAME, SupprEventResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RechercheCompte }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestion/", name = "rechercheCompte")
    public JAXBElement<RechercheCompte> createRechercheCompte(RechercheCompte value) {
        return new JAXBElement<RechercheCompte>(_RechercheCompte_QNAME, RechercheCompte.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link Event }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestion/", name = "event")
    public JAXBElement<Event> createEvent(Event value) {
        return new JAXBElement<Event>(_Event_QNAME, Event.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link AfficherParticipants }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestion/", name = "afficherParticipants")
    public JAXBElement<AfficherParticipants> createAfficherParticipants(AfficherParticipants value) {
        return new JAXBElement<AfficherParticipants>(_AfficherParticipants_QNAME, AfficherParticipants.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Connexion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestion/", name = "connexion")
    public JAXBElement<Connexion> createConnexion(Connexion value) {
        return new JAXBElement<Connexion>(_Connexion_QNAME, Connexion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConnexionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestion/", name = "connexionResponse")
    public JAXBElement<ConnexionResponse> createConnexionResponse(ConnexionResponse value) {
        return new JAXBElement<ConnexionResponse>(_ConnexionResponse_QNAME, ConnexionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AjouterParticipation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestion/", name = "ajouterParticipation")
    public JAXBElement<AjouterParticipation> createAjouterParticipation(AjouterParticipation value) {
        return new JAXBElement<AjouterParticipation>(_AjouterParticipation_QNAME, AjouterParticipation.class, null, value);
    }

}
