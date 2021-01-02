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
 * Servlet implementation class SignUp
 */
@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/signup.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String prenom = request.getParameter("prenom");
		String nom = request.getParameter("nom");
		String mail = request.getParameter("mail");
		String mdp = request.getParameter("mdp");
		String confirmMdp = request.getParameter("confirmMdp");
		
		boolean valide=true;
		
		String champVide="Champ obligatoire";
		if(prenom.equals("")) {
			request.setAttribute("erreurPrenom", champVide);
			valide=false;
		}
		else {
			request.setAttribute("prenom", prenom);
		}
		if(nom.equals("")) {
			request.setAttribute("erreurNom", champVide);
			valide=false;
		}
		else {
			request.setAttribute("nom", nom);
		}
		if(mail.equals("")) {
			request.setAttribute("erreurMail", champVide);
			valide=false;
		}
		else {
			request.setAttribute("mail", mail);
		}
		if(mdp.equals("")) {
			request.setAttribute("erreurMdp", champVide);
			valide=false;
		}
		else {
			if(!mdp.equals(confirmMdp)) {
				request.setAttribute("erreurMdp", "Les mots de passe de correspondent pas");
				valide=false;
			}
		}
		
		if(valide) {
			Compte compte = new Compte(nom,prenom,mail,mdp);
			
			compte = new Comptes().ajouterUnCompte(compte);
			
			if(compte!=null) {
				HttpSession maSession = request.getSession();
				
				maSession.setAttribute("compte", compte);
				response.sendRedirect("/PBG");
			}
			else {
				request.setAttribute("erreurMail", "Cette adresse mail est déjà utilisée");
				doGet(request,response);
			}
		}
		else {
			doGet(request,response);
		}
		
		
	}

}
