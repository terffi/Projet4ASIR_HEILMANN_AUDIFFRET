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
 * Servlet implementation class SignIn
 */
@WebServlet("/SignIn")
public class SignIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	GestionPBG stub;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignIn() {
        super();
		stub = new GestionPBGService().getGestionPBGPort();
		stub.initialisation();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession maSession = request.getSession();
		
		if(maSession.getAttribute("compte")!=null)response.sendRedirect("/PBG/acceuil"); //l'utilisateur est d�j� connect�
		else this.getServletContext().getRequestDispatcher("/WEB-INF/signin.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession maSession = request.getSession();
		
		String mail = request.getParameter("mail");
		String mdp = request.getParameter("mdp");
		
		Compte compte = stub.connexion(mail, mdp);
		
		if(compte!=null) {
	
			maSession.setAttribute("compte", compte);
			
			response.sendRedirect("/PBG/acceuil");			
		}
		else {
			request.setAttribute("erreur", "Email ou mot de passe incorrecte");
			request.setAttribute("mail", mail);
			doGet(request,response);
		}
	}

}
