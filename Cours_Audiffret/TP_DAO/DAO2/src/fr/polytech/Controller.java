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
		Etudiants etudiantsListe = new Etudiants();
		request.setAttribute("resultat", etudiantsListe.afficherTousLesEtudiants());
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/dao.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// On définit un objet de la classe métier .... on fait appel à la méthode ajouterUnEtudiant(Etudiant etudiant)
		
		String action = request.getParameter("action");
		
		Etudiants etudiants = new Etudiants();
		
		if(action.equals("AjoutEtudiant") || action.equals("ModifierEtudiant")) {
			Etudiant etudiant = new Etudiant();
			
			etudiant.setNumero(Integer.parseInt(request.getParameter("id")));
			etudiant.setNom(request.getParameter("nom"));
			etudiant.setPrenom(request.getParameter("prenom"));
			
			if(action.equals("AjoutEtudiant")) {
				etudiants.ajouterUnEtudiant(etudiant);
			}
			if(action.equals("ModifierEtudiant")) {
				etudiants.modifierUnEtudiant(etudiant);
			}		
		}
		if(action.equals("SupprimerEtudiant")) {
			int id = Integer.parseInt(request.getParameter("id"));
			
			etudiants.supprimerUnEtudiant(id);
		}
		if(action.equals("RechercherEtudiant")) {
			String motClef = request.getParameter("recherche");
			
			if(motClef.equals("")) {
				request.setAttribute("resultatRecherche", etudiants.afficherTousLesEtudiants());
			}
			else {
				request.setAttribute("resultatRecherche", etudiants.recherche(motClef));
			}
		}
		
		doGet(request, response);
	}

}
