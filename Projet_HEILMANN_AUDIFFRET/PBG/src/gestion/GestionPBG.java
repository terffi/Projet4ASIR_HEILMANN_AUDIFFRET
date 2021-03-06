
package gestion;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebService(name = "GestionPBG", targetNamespace = "http://gestion/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface GestionPBG {


    /**
     * 
     */
    @WebMethod
    @RequestWrapper(localName = "resetComptes", targetNamespace = "http://gestion/", className = "gestion.ResetComptes")
    @ResponseWrapper(localName = "resetComptesResponse", targetNamespace = "http://gestion/", className = "gestion.ResetComptesResponse")
    public void resetComptes();

    /**
     * 
     */
    @WebMethod
    @RequestWrapper(localName = "resetEvents", targetNamespace = "http://gestion/", className = "gestion.ResetEvents")
    @ResponseWrapper(localName = "resetEventsResponse", targetNamespace = "http://gestion/", className = "gestion.ResetEventsResponse")
    public void resetEvents();

    /**
     * 
     */
    @WebMethod
    @RequestWrapper(localName = "resetParticipations", targetNamespace = "http://gestion/", className = "gestion.ResetParticipations")
    @ResponseWrapper(localName = "resetParticipationsResponse", targetNamespace = "http://gestion/", className = "gestion.ResetParticipationsResponse")
    public void resetParticipations();

    /**
     * 
     * @return
     *     returns java.util.List<gestion.Compte>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "afficherComptes", targetNamespace = "http://gestion/", className = "gestion.AfficherComptes")
    @ResponseWrapper(localName = "afficherComptesResponse", targetNamespace = "http://gestion/", className = "gestion.AfficherComptesResponse")
    public List<Compte> afficherComptes();

    /**
     * 
     * @param motCle
     * @return
     *     returns java.util.List<gestion.Compte>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "rechercherCompte", targetNamespace = "http://gestion/", className = "gestion.RechercherCompte")
    @ResponseWrapper(localName = "rechercherCompteResponse", targetNamespace = "http://gestion/", className = "gestion.RechercherCompteResponse")
    public List<Compte> rechercherCompte(
        @WebParam(name = "motCle", targetNamespace = "")
        String motCle);

    /**
     * 
     * @param mail
     * @return
     *     returns gestion.Compte
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "rechercheUnCompte", targetNamespace = "http://gestion/", className = "gestion.RechercheUnCompte")
    @ResponseWrapper(localName = "rechercheUnCompteResponse", targetNamespace = "http://gestion/", className = "gestion.RechercheUnCompteResponse")
    public Compte rechercheUnCompte(
        @WebParam(name = "mail", targetNamespace = "")
        String mail);

    /**
     * 
     * @param prenom
     * @param mail
     * @param mdp
     * @param nom
     * @return
     *     returns gestion.Compte
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "ajoutCompte", targetNamespace = "http://gestion/", className = "gestion.AjoutCompte")
    @ResponseWrapper(localName = "ajoutCompteResponse", targetNamespace = "http://gestion/", className = "gestion.AjoutCompteResponse")
    public Compte ajoutCompte(
        @WebParam(name = "nom", targetNamespace = "")
        String nom,
        @WebParam(name = "prenom", targetNamespace = "")
        String prenom,
        @WebParam(name = "mail", targetNamespace = "")
        String mail,
        @WebParam(name = "mdp", targetNamespace = "")
        String mdp);

    /**
     * 
     * @param prenom
     * @param mail
     * @param nom
     */
    @WebMethod
    @RequestWrapper(localName = "modifCompte", targetNamespace = "http://gestion/", className = "gestion.ModifCompte")
    @ResponseWrapper(localName = "modifCompteResponse", targetNamespace = "http://gestion/", className = "gestion.ModifCompteResponse")
    public void modifCompte(
        @WebParam(name = "nom", targetNamespace = "")
        String nom,
        @WebParam(name = "prenom", targetNamespace = "")
        String prenom,
        @WebParam(name = "mail", targetNamespace = "")
        String mail);

    /**
     * 
     * @param mail
     * @param mdp
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "modifMdpCompte", targetNamespace = "http://gestion/", className = "gestion.ModifMdpCompte")
    @ResponseWrapper(localName = "modifMdpCompteResponse", targetNamespace = "http://gestion/", className = "gestion.ModifMdpCompteResponse")
    public String modifMdpCompte(
        @WebParam(name = "mail", targetNamespace = "")
        String mail,
        @WebParam(name = "mdp", targetNamespace = "")
        String mdp);

    /**
     * 
     * @param mail
     */
    @WebMethod
    @RequestWrapper(localName = "supprCompte", targetNamespace = "http://gestion/", className = "gestion.SupprCompte")
    @ResponseWrapper(localName = "supprCompteResponse", targetNamespace = "http://gestion/", className = "gestion.SupprCompteResponse")
    public void supprCompte(
        @WebParam(name = "mail", targetNamespace = "")
        String mail);

    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "supprParticipationMail", targetNamespace = "http://gestion/", className = "gestion.SupprParticipationMail")
    @ResponseWrapper(localName = "supprParticipationMailResponse", targetNamespace = "http://gestion/", className = "gestion.SupprParticipationMailResponse")
    public void supprParticipationMail(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @param mail
     * @param mdp
     * @return
     *     returns gestion.Compte
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "connexion", targetNamespace = "http://gestion/", className = "gestion.Connexion")
    @ResponseWrapper(localName = "connexionResponse", targetNamespace = "http://gestion/", className = "gestion.ConnexionResponse")
    public Compte connexion(
        @WebParam(name = "mail", targetNamespace = "")
        String mail,
        @WebParam(name = "mdp", targetNamespace = "")
        String mdp);

    /**
     * 
     * @param mail
     * @param mdp
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "compteValide", targetNamespace = "http://gestion/", className = "gestion.CompteValide")
    @ResponseWrapper(localName = "compteValideResponse", targetNamespace = "http://gestion/", className = "gestion.CompteValideResponse")
    public boolean compteValide(
        @WebParam(name = "mail", targetNamespace = "")
        String mail,
        @WebParam(name = "mdp", targetNamespace = "")
        String mdp);

    /**
     * 
     * @param mail
     */
    @WebMethod
    @RequestWrapper(localName = "setAdmin", targetNamespace = "http://gestion/", className = "gestion.SetAdmin")
    @ResponseWrapper(localName = "setAdminResponse", targetNamespace = "http://gestion/", className = "gestion.SetAdminResponse")
    public void setAdmin(
        @WebParam(name = "mail", targetNamespace = "")
        String mail);

    /**
     * 
     * @param mail
     */
    @WebMethod
    @RequestWrapper(localName = "unsetAdmin", targetNamespace = "http://gestion/", className = "gestion.UnsetAdmin")
    @ResponseWrapper(localName = "unsetAdminResponse", targetNamespace = "http://gestion/", className = "gestion.UnsetAdminResponse")
    public void unsetAdmin(
        @WebParam(name = "mail", targetNamespace = "")
        String mail);

    /**
     * 
     * @return
     *     returns java.util.List<gestion.Compte>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "admins", targetNamespace = "http://gestion/", className = "gestion.Admins")
    @ResponseWrapper(localName = "adminsResponse", targetNamespace = "http://gestion/", className = "gestion.AdminsResponse")
    public List<Compte> admins();

    /**
     * 
     * @param mail
     * @param mdp
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "isAdmin", targetNamespace = "http://gestion/", className = "gestion.IsAdmin")
    @ResponseWrapper(localName = "isAdminResponse", targetNamespace = "http://gestion/", className = "gestion.IsAdminResponse")
    public boolean isAdmin(
        @WebParam(name = "mail", targetNamespace = "")
        String mail,
        @WebParam(name = "mdp", targetNamespace = "")
        String mdp);

    /**
     * 
     * @return
     *     returns java.util.List<gestion.Event>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "afficherEvents", targetNamespace = "http://gestion/", className = "gestion.AfficherEvents")
    @ResponseWrapper(localName = "afficherEventsResponse", targetNamespace = "http://gestion/", className = "gestion.AfficherEventsResponse")
    public List<Event> afficherEvents();

    /**
     * 
     * @param motCle
     * @return
     *     returns java.util.List<gestion.Event>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "rechercheEvent", targetNamespace = "http://gestion/", className = "gestion.RechercheEvent")
    @ResponseWrapper(localName = "rechercheEventResponse", targetNamespace = "http://gestion/", className = "gestion.RechercheEventResponse")
    public List<Event> rechercheEvent(
        @WebParam(name = "motCle", targetNamespace = "")
        String motCle);

    /**
     * 
     * @param id
     * @return
     *     returns gestion.Event
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "rechercherUnEvent", targetNamespace = "http://gestion/", className = "gestion.RechercherUnEvent")
    @ResponseWrapper(localName = "rechercherUnEventResponse", targetNamespace = "http://gestion/", className = "gestion.RechercherUnEventResponse")
    public Event rechercherUnEvent(
        @WebParam(name = "id", targetNamespace = "")
        int id);

    /**
     * 
     * @param id
     * @param description
     * @param image
     * @param date
     * @param nom
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "ajoutEvent", targetNamespace = "http://gestion/", className = "gestion.AjoutEvent")
    @ResponseWrapper(localName = "ajoutEventResponse", targetNamespace = "http://gestion/", className = "gestion.AjoutEventResponse")
    public boolean ajoutEvent(
        @WebParam(name = "id", targetNamespace = "")
        int id,
        @WebParam(name = "nom", targetNamespace = "")
        String nom,
        @WebParam(name = "description", targetNamespace = "")
        String description,
        @WebParam(name = "date", targetNamespace = "")
        XMLGregorianCalendar date,
        @WebParam(name = "image", targetNamespace = "")
        String image);

    /**
     * 
     * @param id
     */
    @WebMethod
    @RequestWrapper(localName = "supprEvent", targetNamespace = "http://gestion/", className = "gestion.SupprEvent")
    @ResponseWrapper(localName = "supprEventResponse", targetNamespace = "http://gestion/", className = "gestion.SupprEventResponse")
    public void supprEvent(
        @WebParam(name = "id", targetNamespace = "")
        int id);

    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "supprParticipationEvent", targetNamespace = "http://gestion/", className = "gestion.SupprParticipationEvent")
    @ResponseWrapper(localName = "supprParticipationEventResponse", targetNamespace = "http://gestion/", className = "gestion.SupprParticipationEventResponse")
    public void supprParticipationEvent(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0);

    /**
     * 
     * @param id
     * @param image
     */
    @WebMethod
    @RequestWrapper(localName = "modifImageEvent", targetNamespace = "http://gestion/", className = "gestion.ModifImageEvent")
    @ResponseWrapper(localName = "modifImageEventResponse", targetNamespace = "http://gestion/", className = "gestion.ModifImageEventResponse")
    public void modifImageEvent(
        @WebParam(name = "id", targetNamespace = "")
        int id,
        @WebParam(name = "image", targetNamespace = "")
        String image);

    /**
     * 
     * @param id
     * @param description
     * @param date
     * @param nom
     */
    @WebMethod
    @RequestWrapper(localName = "modifEvent", targetNamespace = "http://gestion/", className = "gestion.ModifEvent")
    @ResponseWrapper(localName = "modifEventResponse", targetNamespace = "http://gestion/", className = "gestion.ModifEventResponse")
    public void modifEvent(
        @WebParam(name = "id", targetNamespace = "")
        int id,
        @WebParam(name = "nom", targetNamespace = "")
        String nom,
        @WebParam(name = "description", targetNamespace = "")
        String description,
        @WebParam(name = "date", targetNamespace = "")
        XMLGregorianCalendar date);

    /**
     * 
     * @return
     *     returns java.util.List<gestion.Participant>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "afficherParticipations", targetNamespace = "http://gestion/", className = "gestion.AfficherParticipations")
    @ResponseWrapper(localName = "afficherParticipationsResponse", targetNamespace = "http://gestion/", className = "gestion.AfficherParticipationsResponse")
    public List<Participant> afficherParticipations();

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns gestion.Participant
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getParticipation", targetNamespace = "http://gestion/", className = "gestion.GetParticipation")
    @ResponseWrapper(localName = "getParticipationResponse", targetNamespace = "http://gestion/", className = "gestion.GetParticipationResponse")
    public Participant getParticipation(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<gestion.Compte>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "afficherParticipants", targetNamespace = "http://gestion/", className = "gestion.AfficherParticipants")
    @ResponseWrapper(localName = "afficherParticipantsResponse", targetNamespace = "http://gestion/", className = "gestion.AfficherParticipantsResponse")
    public List<Compte> afficherParticipants(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<gestion.Event>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "afficherParticipationEvents", targetNamespace = "http://gestion/", className = "gestion.AfficherParticipationEvents")
    @ResponseWrapper(localName = "afficherParticipationEventsResponse", targetNamespace = "http://gestion/", className = "gestion.AfficherParticipationEventsResponse")
    public List<Event> afficherParticipationEvents(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @param arg1
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "ajouterParticipation", targetNamespace = "http://gestion/", className = "gestion.AjouterParticipation")
    @ResponseWrapper(localName = "ajouterParticipationResponse", targetNamespace = "http://gestion/", className = "gestion.AjouterParticipationResponse")
    public void ajouterParticipation(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1);

    /**
     * 
     * @param arg1
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "supprParticipation", targetNamespace = "http://gestion/", className = "gestion.SupprParticipation")
    @ResponseWrapper(localName = "supprParticipationResponse", targetNamespace = "http://gestion/", className = "gestion.SupprParticipationResponse")
    public void supprParticipation(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1);

}
