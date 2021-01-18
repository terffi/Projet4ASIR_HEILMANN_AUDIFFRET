package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gestion.Compte;
import gestion.GestionPBG;
import gestion.GestionPBGService;



/**
 * Servlet implementation class Controller
 */
@WebServlet("/GestionComptes")
public class GestionComptes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	GestionPBG stub;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionComptes() {
        super();
		stub = new GestionPBGService().getGestionPBGPort();
		stub.initialisation();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		HttpSession maSession = request.getSession();
		
		Compte compte = (Compte)maSession.getAttribute("compte"); //récupération du compte de la session
		
		//avant toute action, on vérifie que le compte utilisateur est un compte valide et admin
		if(compte!=null && compte.isAdmin()) {
			
			if(maSession.getAttribute("recherche")==null) maSession.setAttribute("recherche", "");
			
			
			if(maSession.getAttribute("recherche").equals("")) {
				request.setAttribute("resultatRecherche", stub.afficherComptes());
			}
			else {
				request.setAttribute("resultatRecherche", stub.rechercheCompte((String)maSession.getAttribute("recherche")));
			}
			
			
			request.setAttribute("resultat", stub.afficherComptes());
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/gestion_comptes.jsp").forward(request, response);
		}
		else {
			//l'utilisateur n'est pas connecté ou n'est pas administrateur
			response.sendRedirect("/PBG/acceuil");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession maSession = request.getSession();

		Compte compteAdmin = (Compte) maSession.getAttribute("compte"); //récupération du compte de la session
		
		//avant toute action, on vérifie que l'utilisateur est connecté avec un compte administrateur
		if(compteAdmin!=null && stub.isAdmin(compteAdmin))
		{	
			
			String action = request.getParameter("action");
			

			if(action.equals("Ajout")) {
				
				String mail = request.getParameter("mail");
				String nom = request.getParameter("nom");
				String prenom = request.getParameter("prenom");
				String mdp = request.getParameter("mdp");
	
				stub.ajoutCompte(nom, prenom, mail, mdp);
			}
			
			
			if(action.equals("Modifier")) {
				
				String mail = request.getParameter("mailModif");
				String nom = request.getParameter("nomModif");
				String prenom = request.getParameter("prenomModif");
	
				stub.modifCompte(nom, prenom, mail);	
			}
			
			
			
			if(action.equals("Supprimer")) {
				String mail = request.getParameter("mailSuppr");
	
				stub.supprCompte(mail);
			}
			
			
			
			if(action.equals("Rechercher")) {
				String motClef = request.getParameter("recherche");
				maSession.setAttribute("recherche", motClef);	
			}
		
		
			if(action.equals("Ajouter en tant qu'administrateur")) {
				String mail = request.getParameter("mailAdmin");
				stub.setAdmin(mail);
			
			}
			
			
			if(action.equals("Modifier le mot de passe")) {
				String mail = request.getParameter("mailModifMdp");
				String mdp = request.getParameter("mdpModif");
				stub.modifMdpCompte(mail, mdp);
			}
			
			response.sendRedirect("/PBG/gestion_comptes");
		}
		else {
			//le compte utilisateur n'est pas admin
			response.sendRedirect("/PBG/acceuil");
		}
	}

}
