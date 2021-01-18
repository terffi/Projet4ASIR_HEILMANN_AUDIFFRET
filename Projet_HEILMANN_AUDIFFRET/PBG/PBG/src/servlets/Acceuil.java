package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gestion.GestionPBG;
import gestion.GestionPBGService;



/**
 * Servlet implementation class controller
 */
@WebServlet("/pbg")
public class Acceuil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	GestionPBG stub;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Acceuil() {
        super();
		stub = new GestionPBGService().getGestionPBGPort();
		stub.initialisation();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("resultat", stub.afficherComptes());

		this.getServletContext().getRequestDispatcher("/WEB-INF/acceuil.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("/PBG/acceuil");
	}

}
