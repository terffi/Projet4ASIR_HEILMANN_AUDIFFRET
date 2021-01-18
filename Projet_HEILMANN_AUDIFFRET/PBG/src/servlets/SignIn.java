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

//servlet de connexion

/**
 * Servlet implementation class SignIn
 */
@WebServlet("/sign-in")
public class SignIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	GestionPBG stub; //communication avec le service web SOAP
       
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
		
		//vérification que l'utilisateur ne soit pas déjà connecté
		if(maSession.getAttribute("compte")!=null)response.sendRedirect("/PBG/acceuil"); //l'utilisateur est déjà connecté
		else this.getServletContext().getRequestDispatcher("/WEB-INF/signin.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession maSession = request.getSession();
		
		String mail = request.getParameter("mail");
		String mdp = request.getParameter("mdp");
		
		//connexion de l'utilisateur
		Compte compte = stub.connexion(mail, mdp);
		
		if(compte!=null) {
			
			maSession.setAttribute("compte", compte);
			
			response.sendRedirect("/PBG/accueil");			
		}
		else {
			//mot de passe ou mail incorrect
			request.setAttribute("erreur", "Email ou mot de passe incorrecte");
			request.setAttribute("mail", mail);
			doGet(request,response);
		}
	}

}
