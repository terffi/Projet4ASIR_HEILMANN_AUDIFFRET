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
 * Servlet implementation class controller
 */
@WebServlet("/pbg")
public class Acceuil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Acceuil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession maSession = request.getSession();
		
		Compte compte = (Compte) maSession.getAttribute("compte"); //récupération du compte de la session
		
		if(compte!=null) {
		
			compte = new Comptes().connexion(compte.getMail(), compte.getMdp()); //vérification de la validité du compte (on reconnecte l'utilisateur)
			
			if(compte!=null) {
				request.setAttribute("connecté", true);
			}
			else {
				maSession.setAttribute("compte", null);
				request.setAttribute("connecté", false);
			}
		
		}
		else {
			request.setAttribute("connecté", false);
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/acceuil.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("/PBG");
	}

}
