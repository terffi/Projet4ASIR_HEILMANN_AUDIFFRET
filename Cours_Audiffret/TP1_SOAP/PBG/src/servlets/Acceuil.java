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
		
		boolean connected = false;
		
		if(compte!=null) {
		
			//vérification de la validité du compte
			if(new Comptes().compteValide(compte)) {
				connected=true;
			}
			else {
				//compte invalide, on déconnecte l'utilisateur
				maSession.setAttribute("compte", null);
			}
		}
		request.setAttribute("connecté", connected);
		
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
