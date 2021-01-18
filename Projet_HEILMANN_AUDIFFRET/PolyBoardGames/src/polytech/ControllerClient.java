package polytech;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.spi.ImageWriterSpi;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import EJBs.Compte;

/**
 * Servlet implementation class ControllerStaff
 */
@WebServlet("/ControllerStaff")
public class ControllerClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerClient() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession maSession = request.getSession();
		List<Event> liste = new ArrayList<Event>();
		String action = request.getParameter("action");
		
		Events events = new Events();
		
		//if(maSession.getAttribute("recherche")==null) maSession.setAttribute("recherche", "");
		
		if(maSession.getAttribute("recherche")!=null) {
		
			if(maSession.getAttribute("recherche").equals("")) {
				liste=events.afficherTout();
				request.setAttribute("list", events.afficherTout());
			}
			else {
				request.setAttribute("list", events.rechercher((String)maSession.getAttribute("recherche")));
				liste= events.rechercher((String)maSession.getAttribute("recherche"));
			}
		}
		
		request.setAttribute("inscription", (String)maSession.getAttribute("inscription"));
		
	
		this.getServletContext().getRequestDispatcher("/WEB-INF/Client.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Events events = new Events();
		Event event = new Event();
		Participants participants = new Participants();
		
		HttpSession maSession = request.getSession();
		String action = request.getParameter("action");
		List<Event> liste = new ArrayList<Event>();
		
		Compte compte = (Compte)maSession.getAttribute("compte"); //récupération du compte de la session
		
		
		if(compte!=null) {
			
		
		
		
			if(action.equals("Rechercher")) {
				
				String motCle = request.getParameter("recherche");
				maSession.setAttribute("recherche", motCle);
				
				if(motCle.equals("")) {
					liste = events.afficherTout();
				}
				else {
					liste = events.rechercher(motCle);
				}
				
			}
			
			if(action.equals("S'inscrire")) {				
				/*
				for(Event event1 : liste){
					String bouton = "S'inscrire à "+event1.getNom();
					System.out.println(bouton);
					
					if(action.equals(bouton)) {
						System.out.println(compte.getMail());
						String mail = compte.getMail();
						participants.ajouter_un_participant(event1.getId(), mail);
						maSession.setAttribute("inscription", "tu viens de t'inscrire à cet event !");
					}
				}
				*/
				participants.ajouter_un_participant(Integer.parseInt(request.getParameter("idEvent")), compte.getMail());
			}
			
	
			//doGet(request, response);
			response.sendRedirect("/PolyBoardGames/ControllerClient");
}
		else {
			response.sendRedirect("/PolyBoardGames/acceuil");
		}
	}

}
