package fr.polytech;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		Comptes comptesListe = new Comptes();
		request.setAttribute("resultat", comptesListe.afficherTousLesComptes());
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/dao.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// On définit un objet de la classe métier .... on fait appel à la méthode ajouterUnEtudiant(Etudiant etudiant)
		
		String action = request.getParameter("action");
		
		Comptes comptes = new Comptes();
		
		if(action.equals("AjoutEtudiant") || action.equals("ModifierEtudiant")) {
			Compte compte = new Compte();
			
			compte.setId(Integer.parseInt(request.getParameter("id")));
			compte.setNom(request.getParameter("nom"));
			compte.setPrenom(request.getParameter("prenom"));
			
			if(action.equals("AjoutEtudiant")) {
				comptes.ajouterUnCompte(compte);
			}
			if(action.equals("ModifierEtudiant")) {
				comptes.modifierUnCompte(compte);
			}		
		}
		if(action.equals("SupprimerEtudiant")) {
			int id = Integer.parseInt(request.getParameter("id"));
			
			comptes.supprimerUnCompte(id);
		}
		if(action.equals("RechercherEtudiant")) {
			String motClef = request.getParameter("recherche");
			
			if(motClef.equals("")) {
				request.setAttribute("resultatRecherche", comptes.afficherTousLesComptes());
			}
			else {
				request.setAttribute("resultatRecherche", comptes.recherche(motClef));
			}
		}
		
		doGet(request, response);
	}

}
