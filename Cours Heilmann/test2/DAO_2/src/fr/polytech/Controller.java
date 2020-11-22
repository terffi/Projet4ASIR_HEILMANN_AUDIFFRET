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
		/*java.util.List<Etudiant> etudiants = new ArrayList<Etudiant>();
		etudiants.add(new Etudiant(1,"A","B"));
		etudiants.add(new Etudiant(1,"C","D"));
		etudiants.add(new Etudiant(1,"E","F"));
		etudiants.add(new Etudiant(1,"G","H"));
		
		request.setAttribute("etudiants", etudiants);*/
		
		Etudiants etudiants = new Etudiants();
		

		request.setAttribute("resultat", etudiants.afficherTousLesEtudiants());
		
		request.setAttribute("resultatRecherche", etudiants.afficherTousLesEtudiants());
		
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/dao2.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// on définis un objet de la classe métier .... on fait appel à la méthode ajouterUnEtudiant(Etudiant etudiant)
		
		Etudiant etudiant = new Etudiant();
		
		
		String action = request.getParameter("action");
		Etudiants etudiants = new Etudiants();
		
		if(action.equals("ajouter") || action.equals("modifier")) {
			
			etudiant.setIdentifiant(Integer.parseInt(request.getParameter("id")));
			etudiant.setNom(request.getParameter("nom"));
			etudiant.setPrenom(request.getParameter("prenom"));
			request.getParameter("recherche");
			
			if(action.equals("modifier")) {
				etudiants.modifierInformationEtudiant(etudiant);
			}
			else {
				etudiants.ajouterUnEtudiant(etudiant);
			}
			

		if(action.equals("supprimer")) {
			
			int id = Integer.parseInt(request.getParameter("id"));
			
			etudiants.supprimerUnEtudiant(id);
		}
			
		}
		if(action.equals("rechercher")) {
			
			String Var = request.getParameter("recherche");
			
			if(Var.equals("")) {
				request.setAttribute("resultatRecherche", etudiants.afficherTousLesEtudiants());
			}
			else {
				request.setAttribute("resultatRecherche", etudiants.rechercher(Var));
			}
		}


		

		
		
		
		
		doGet(request, response);
	}

}
