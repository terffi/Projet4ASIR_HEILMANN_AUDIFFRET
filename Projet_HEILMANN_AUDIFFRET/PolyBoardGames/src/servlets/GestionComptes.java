package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import EJBs.Compte;
import EJBs.Comptes;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/GestionComptes")
public class GestionComptes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionComptes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		HttpSession maSession = request.getSession();
		
		Comptes comptesListe = new Comptes();
		
		Compte compte = (Compte)maSession.getAttribute("compte"); //récupération du compte de la session
		
		//avant toute action, on vérifie que le compte utilisateur est un compte valide et admin
		if(compte!=null && compte.isAdmin()) {
			
			if(maSession.getAttribute("recherche")==null) maSession.setAttribute("recherche", "");
			
			
			if(maSession.getAttribute("recherche").equals("")) {
				request.setAttribute("resultatRecherche", comptesListe.afficherTousLesComptes());
			}
			else {
				request.setAttribute("resultatRecherche", comptesListe.recherche((String)maSession.getAttribute("recherche")));
			}
			
			
			request.setAttribute("resultat", comptesListe.afficherTousLesComptes());
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/gestion_comptes.jsp").forward(request, response);
		}
		else {
			//l'utilisateur n'est pas connecté ou n'est pas administrateur
			response.sendRedirect("/PolyBoardGames/acceuil");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession maSession = request.getSession();

		Compte compteAdmin = (Compte) maSession.getAttribute("compte"); //récupération du compte de la session
		
		Comptes comptes = new Comptes();
		
		//avant toute action, on vérifie que l'utilisateur est connecté avec un compte administrateur
		if(compteAdmin!=null && comptes.isAdmin(compteAdmin))
		{	
			String action = request.getParameter("action");
			
			
			
			if(action.equals("Ajout") || action.equals("Modifier")) {
				Compte compte = new Compte();
				
				compte.setMail(request.getParameter("mail"));
				compte.setNom(request.getParameter("nom"));
				compte.setPrenom(request.getParameter("prenom"));
				
				
				if(action.equals("Ajout")) {
					compte=comptes.ajouterUnCompte(compte,request.getParameter("mdp"));
				}
				if(action.equals("Modifier")) {
					comptes.modifierUnCompte(compte);
				}		
			}
			if(action.equals("Supprimer")) {
				String mail = request.getParameter("mail");
	
				comptes.supprimerUnCompte(mail);
			}
			if(action.equals("Rechercher")) {
				String motClef = request.getParameter("recherche");
				maSession.setAttribute("recherche", motClef);	
			}
		
		
			if(action.equals("Ajouter en tant qu'admin")) {
				String mail = request.getParameter("mailAdmin");
				comptes.compteAdmin(mail);
			
			}
			
			
			response.sendRedirect("/PolyBoardGames/gestion_comptes");
		}
		else {
			//le compte utilisateur n'est pas admin
			response.sendRedirect("/PolyBoardGames/acceuil");
		}
	}

}
