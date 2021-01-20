package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gestion.Compte;
import gestion.Event;
import gestion.GestionPBG;
import gestion.GestionPBGService;

//servlet de gestion des participations

/**
 * Servlet implementation class GestionParticipations
 */
@WebServlet("/gestion_participations")
public class GestionParticipations extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	GestionPBG stub; //communication avec le service web SOAP
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionParticipations() {
        super();
        stub = new GestionPBGService().getGestionPBGPort();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession maSession = request.getSession();
		
		gestion.Compte compte = (gestion.Compte)maSession.getAttribute("compte"); //r�cup�ration du compte de la session
		
		
		
		//v�rifie si l'utilisateur est bien connect� et admin
		if(compte!=null && compte.isAdmin()) {
			
			//affichage des r�sultats de la recherche des participants d'un event
			if(request.getParameter("rechercheParticipants")!=null && !request.getParameter("rechercheParticipants").equals("")) {
				
				int idEvent;
				
				//verification que l'id�e entr�e est bien un nombre
				try {
					idEvent = Integer.parseInt(request.getParameter("rechercheParticipants"));
					
					request.setAttribute("rechercheParticipants", request.getParameter("rechercheParticipants"));
					
					//recherche de l'event
					Event e = stub.rechercherUnEvent(idEvent);
					
					if(e!=null) {
						request.setAttribute("eventExist", true);
						request.setAttribute("nomEvent", e.getNom());
						request.setAttribute("resultatRechercheParticipants", stub.afficherParticipants(idEvent));
					}
					
				}catch(Exception e){
					//ID incorrect
					request.setAttribute("erreurID", "ID incorrect");
				}
		
				
			}
			else {
				//erreur eventuelle de l'ID rentr�e
				request.setAttribute("erreurID", request.getParameter("erreurID"));
			}
			
			
			//affichage des r�sultats de la recherche des participations d'un compte
			if(request.getParameter("rechercheParticipations")!=null && !request.getParameter("rechercheParticipations").equals("")) {
				
				request.setAttribute("rechercheParticipations", request.getParameter("rechercheParticipations"));
			
				//recherche du compte 
				Compte c = stub.rechercheUnCompte(request.getParameter("rechercheParticipations"));
				
				if(c!=null) {
					request.setAttribute("compteExist", true);
					request.setAttribute("nomCompte", c.getNom());
					request.setAttribute("resultatRechercheParticipations", stub.afficherParticipationEvents(c.getMail()));
				}	
			}

			
			this.getServletContext().getRequestDispatcher("/WEB-INF/gestion_participations.jsp").forward(request, response);
		}
		else {
			//utilisateur non connect� ou non admin
			response.sendRedirect("/PBG/accueil");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession maSession = request.getSession();

		Compte compteAdmin = (Compte) maSession.getAttribute("compte"); //r�cup�ration du compte de la session
		
		//avant toute action, on v�rifie que l'utilisateur est connect� avec un compte administrateur
		if(compteAdmin!=null && stub.isAdmin(compteAdmin.getMail(),compteAdmin.getMdp()))
		{	
			
			String action = request.getParameter("action");
			
			String rechercheParticipants=request.getParameter("rechercheParticipants");
			if(rechercheParticipants==null) {
				rechercheParticipants="";
			}
			
			String rechercheParticipations=request.getParameter("rechercheParticipations");
			if(rechercheParticipations==null) {
				rechercheParticipations="";
			}

			//ajout d'une participation
			if(action.equals("Ajouter")) {
				String id=request.getParameter("id");
				String mail = request.getParameter("mail");
				try {
					int idEvent = Integer.parseInt(id);
					stub.ajouterParticipation(idEvent, mail);
				}catch(Exception e) {
					//l'id rentr�e est incorrect
				}
			}
			
			//suppression d'une participation
			if(action.equals("Supprimer")) {
				try {
					int id = Integer.parseInt(request.getParameter("id"));
					String mail = request.getParameter("mail");
					
					stub.supprParticipation(id, mail);
					
				}catch(Exception e) {
					//l'ID rentr�e est incorrecte
				}
			}
		
			////suppression de toutes les participations d'un compte
			if(action.equals("Supprimer toutes les participations du comptes")) {
				String mail = request.getParameter("mail");
				
				stub.supprParticipationMail(mail);
			
			}
			
			
			//suppression de toutes les participations d'un event
			if(action.equals("Supprimer toutes les participations de l'event")) {
				try {
					int id = Integer.parseInt(request.getParameter("id"));
					
					stub.supprParticipationEvent(id);
				}catch(Exception e) {
					//l'ID rentr�e est incorrecte
				}
			
			}
			
			
			//reset de la liste
			if(action.equals("Reset")) {
				stub.resetParticipations();
			}
			
			response.sendRedirect("/PBG/gestion_participations?rechercheParticipants="+rechercheParticipants+"&rechercheParticipations="+rechercheParticipations);
		}
		else {
			//le compte utilisateur n'est pas admin
			response.sendRedirect("/PBG/sign-out");
		}
	}

}
