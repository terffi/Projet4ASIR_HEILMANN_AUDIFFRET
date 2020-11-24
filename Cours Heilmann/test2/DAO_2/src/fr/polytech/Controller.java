package fr.polytech;

import java.io.IOException;
import java.util.ArrayList;

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
		// TODO Auto-generated method stub
		java.util.List<Etudiant> etudiants = new ArrayList<Etudiant>();
		etudiants.add(new Etudiant(1,"A","B"));
		etudiants.add(new Etudiant(1,"C","D"));
		etudiants.add(new Etudiant(1,"E","F"));
		etudiants.add(new Etudiant(1,"G","H"));
		
		request.setAttribute("etudiants", etudiants);
		
		Etudiants etudiants2 = new Etudiants();
		request.setAttribute("resultat", etudiants2.afficherTousLesEtudiants());
		
//		Etudiants etudiants3 = new Etudiants();
//		request.setAttribute("resultatRecherche", etudiants3.rechercher(request.getParameter("recherche")));

		
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/dao2.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// on d�finis un objet de la classe m�tier .... on fait appel � la m�thode ajouterUnEtudiant(Etudiant etudiant)
		
		Etudiant etudiant = new Etudiant();
		
		etudiant.setIdentifiant(Integer.parseInt(request.getParameter("id")));
		etudiant.setNom(request.getParameter("nom"));
		etudiant.setPrenom(request.getParameter("prenom"));
		request.getParameter("recherche");
		
		String action = request.getParameter("action");
		String action1 = request.getParameter("action1");
		Etudiants etudiants = new Etudiants();
		
		if(action.equals("ajouter")) {
			etudiants.ajouterUnEtudiant(etudiant);
		}
		if(action.equals("modifier")) {
			etudiants.modifierInformationEtudiant(etudiant);
		}
		if(action.equals("supprimer")) {
			etudiants.supprimerUnEtudiant(etudiant);
		}
		if(action1.equals("rechercher")) {
			etudiants.rechercher(etudiant);
		}

		

		
		
		
		
		doGet(request, response);
	}

}
