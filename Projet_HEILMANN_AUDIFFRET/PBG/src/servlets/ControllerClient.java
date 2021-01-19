package servlets;

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
		
		HttpSession maSession = request.getSession();//r�cup�ration de la session
		
		String action = request.getParameter("action");
		

		gestion.Compte compte = (gestion.Compte)maSession.getAttribute("compte"); //r�cup�ration du compte de la session
		
		
		//v�rifie si l'utilisateur est connect�
		if(compte!=null) {
			
			if(maSession.getAttribute("recherche")==null) maSession.setAttribute("recherche", "");
			
			if(maSession.getAttribute("recherche")!=null) {
			
				if(maSession.getAttribute("recherche").equals("")) {
					
					request.setAttribute("list", stub.afficherEvents());
				}
				else {
					
					//v�rification que l'identifiant est bien un int avant d'appeler la m�thode rechercheEvent
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
			//utilisateur non connect�, retour � la page d'accueil
			response.sendRedirect("/PBG/accueil");
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
		
		
		gestion.Compte compte = (gestion.Compte)maSession.getAttribute("compte"); //r�cup�ration du compte de la session
		
		
		//v�rifie si l'utilisateur est connect�
		if(compte!=null) {
			
		
		
			//Rechercher un event
			if(action.equals("Rechercher")) {
				
				//r�cup�ration des param�tres du form de la jsp + passage de "recherche" � la session
				String idCle = request.getParameter("recherche");
				maSession.setAttribute("recherche", idCle);
				
			}
			
			
			//Inscription � un event
			if(action.equals("S'inscrire")) {				

				boolean Valide;
				String idInsc = request.getParameter("idInsc");
				
				//v�rifie si l'identifiant entr� est bien un int 
				if(validation_id(idInsc)) {
					
					int id = Integer.parseInt(idInsc);
					
					//v�rifie si l'identifiant correspond � un event
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
			//utilisateur non connect�
			response.sendRedirect("/PolyBoardGames/accueil");
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
