package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import gestion.GestionPBG;
import gestion.GestionPBGService;

//servlet permettant aux utilisateurs de s'inscrire � des events

/**
 * Servlet implementation class ControllerClient
 * @param <GestionPGB>
 */

public class InscriptionEvents extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	GestionPBG stub; //communication avec le service web SOAP
       
   
    public InscriptionEvents() {
        super();
        stub = new GestionPBGService().getGestionPBGPort();
    }


    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession maSession = request.getSession();
			
		//affichage des r�sultats de la recherche
		if(maSession.getAttribute("rechercheEventInscription")==null) maSession.setAttribute("rechercheEventInscription", "");
		
		if(maSession.getAttribute("rechercheEventInscription").equals("")) {
			request.setAttribute("resultatRecherche", stub.afficherEvents());
		}
		else {
				
		}
		
		gestion.Compte compte = (gestion.Compte)maSession.getAttribute("compte"); //r�cup�ration du compte de la session
		
		if(compte!=null) {
			//r�cup�ration des evenements auxquel l'utilisateur est deja inscrit
			request.setAttribute("ListEventInscrit", stub.afficherParticipationEvents(compte.getMail()));
		}
			
		this.getServletContext().getRequestDispatcher("/WEB-INF/events.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession maSession = request.getSession();
		
		String action = request.getParameter("action");
		
		gestion.Compte compte = (gestion.Compte)maSession.getAttribute("compte"); //r�cup�ration du compte de la session
		

		//Rechercher un event
		if(action.equals("Rechercher")) {
				
			//r�cup�ration des param�tres du form de la jsp + passage de "recherche" � la session
			String idCle = request.getParameter("rechercheEventInscription");
			maSession.setAttribute("rechercheEventInscription", idCle);	
		}
			
		//inscription � un event
		if(action.equals("S'inscrire")) {	
			
			//l'utilisateur doit etre connect� pour s'inscrire
			if(compte!=null) {
				stub.ajouterParticipation(Integer.parseInt(request.getParameter("idEvent")), compte.getMail());
			}
		}
		
		
		//d�sinscription � un event
		if(action.equals("Se d�sinscrire")) {
	
			//l'utilisateur doit etre connect�
			if(compte!=null) {
				stub.supprParticipation(Integer.parseInt(request.getParameter("idEvent")), compte.getMail());
			}
	
		}
		
	
		response.sendRedirect("/PBG/events");
	}

}
