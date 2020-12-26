package fr.polytech;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		HttpSession maSession = request.getSession();
		
		Comptes comptesListe = new Comptes();
		
		if(maSession.getAttribute("recherche")==null) maSession.setAttribute("recherche", "");
		
		
		if(maSession.getAttribute("recherche").equals("")) {
			request.setAttribute("resultatRecherche", comptesListe.afficherTousLesComptes());
		}
		else {
			request.setAttribute("resultatRecherche", comptesListe.recherche((String)maSession.getAttribute("recherche")));
		}
		
		
		request.setAttribute("resultat", comptesListe.afficherTousLesComptes());
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/dao.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession maSession = request.getSession();
		
		// On définit un objet de la classe métier .... on fait appel à la méthode ajouterUnEtudiant(Etudiant etudiant)
		
		String action = request.getParameter("action");
		
		Comptes comptes = new Comptes();
		
		if(action.equals("Ajout") || action.equals("Modifier")) {
			Compte compte = new Compte();
			
			compte.setMail(request.getParameter("mail"));
			compte.setMdp(request.getParameter("mdp"));
			compte.setNom(request.getParameter("nom"));
			compte.setPrenom(request.getParameter("prenom"));
			
			
			if(action.equals("Ajout")) {
				comptes.ajouterUnCompte(compte);
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
		
		response.sendRedirect("/PBG/Controller");
	}

}
