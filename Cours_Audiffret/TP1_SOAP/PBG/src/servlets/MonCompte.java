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
 * Servlet implementation class MonCompte
 */
@WebServlet("/MonCompte")
public class MonCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MonCompte() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/moncompte.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession maSession = request.getSession();
		
		// On définit un objet de la classe métier .... on fait appel à la méthode ajouterUnEtudiant(Etudiant etudiant)
		
		String action = request.getParameter("action");
		
		Comptes comptes = new Comptes();
		
		Compte compte = (Compte) maSession.getAttribute("compte"); //récupération du compte de la session
		String mail = compte.getMail(); //récupération du mail
		
		String mdp = request.getParameter("mdp"); //récupération du mot de passe rentré
		
		compte = comptes.connexion(mail, mdp); //vérification du mot de passe (on reconnecte l'utilisateur)
		
		if(compte!=null) {
		
			if(action.equals("Modifier le compte")) {
				compte.setNom(request.getParameter("nom"));
				compte.setPrenom(request.getParameter("prenom"));
				
				comptes.modifierUnCompte(compte);
				
				maSession.setAttribute("compte", compte);	
				
				response.sendRedirect("/PBG/MonCompte");
			}
			if(action.equals("Supprimer le compte")) {				
				comptes.supprimerUnCompte(mail,mdp);
				
				maSession.setAttribute("compte", null);
				
				response.sendRedirect("/PBG/acceuil");
			}
		}
		else {
			request.setAttribute("erreurMdp", "Mot de passe incorrecte");
			doGet(request,response);
		}
	
	}

}
