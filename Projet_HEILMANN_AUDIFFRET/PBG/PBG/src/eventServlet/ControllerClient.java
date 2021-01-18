package eventServlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import gestion.GestionPBG;
import gestion.GestionPBGService;


/**
 * Servlet implementation class ControllerClient
 * @param <GestionPGB>
 */
@WebServlet("/ControllerClient")
public class ControllerClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	GestionPBG stub;
       
   
    public ControllerClient() {
        super();
        stub = new GestionPBGService().getGestionPBGPort();
        stub.initialisation();
    }


    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession maSession = request.getSession();
		
		String action = request.getParameter("action");
		
		//Events events = new Events();
		gestion.Compte compte = (gestion.Compte)maSession.getAttribute("compte"); //récupération du compte de la session
		
		
		
		if(compte!=null) {
			if(maSession.getAttribute("recherche")==null) maSession.setAttribute("recherche", "");
			
			if(maSession.getAttribute("recherche")!=null) {
			
				if(maSession.getAttribute("recherche").equals("")) {
					
					request.setAttribute("list", stub.afficherEvents());
				}
				else {
					
					if(validation_id((String)maSession.getAttribute("recherche"))) {
						
						request.setAttribute("list", stub.rechercheEvent(Integer.parseInt((String)maSession.getAttribute("recherche"))));
					}
					else {
						request.setAttribute("erreurIdentifiant", "L'identifiant est invalide");
					}
					
				}
			}
			
			
			
		
			this.getServletContext().getRequestDispatcher("/WEB-INF/Client.jsp").forward(request, response);
		}
		else {
			//utilisateur non connecté
			response.sendRedirect("/PBG/acceuil");
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		//gestion.Participants participants = new gestion.Participants();
		
		HttpSession maSession = request.getSession();
		
		String action = request.getParameter("action");
		
		
		gestion.Compte compte = (gestion.Compte)maSession.getAttribute("compte"); //récupération du compte de la session
		
		
		if(compte!=null) {
			
		
		
		
			if(action.equals("Rechercher")) {
				
				String idCle = request.getParameter("recherche");
				maSession.setAttribute("recherche", idCle);
				
			}
			
			if(action.equals("S'inscrire")) {				

				boolean Valide;
				String idInsc = request.getParameter("idInsc");
				
				if(validation_id(idInsc)) {
					
					int id = Integer.parseInt(idInsc);
					
					if(stub.rechercheEvent(id)==null){
						
						Valide = false;
						request.setAttribute("erreurInsc", "il n'y a aucun event qui porte cet identifiant");
						
						
					}
					else {
						
						Valide = true;
						stub.ajouterParticipation(Integer.parseInt(request.getParameter("idInsc")), compte.getMail());
					}
				}
				else {
					
					Valide = false;
					request.setAttribute("erreurIdentifiant", "l'identifiant est invalide");
				}
				
				
				if(Valide) {
					
					response.sendRedirect("/PBG/ControllerClient");
					
				}
				else {
					
					doGet(request, response);
				}
				
				
			}
			
	
			//doGet(request, response);
			response.sendRedirect("/PolyBoardGames/ControllerClient");
		}
		else {
			response.sendRedirect("/PolyBoardGames/acceuil");
		}
	}
	
	
	
	
	
	
	
	
	private boolean validation_id(String TestId) {
		
		if(TestId.equals("")) {return false;}
		
		else {
			
			try {
				
				int i=Integer.parseInt(TestId);
				return true;
				
			}catch(Exception e) {
				return false;
			}
		}
	}

}
