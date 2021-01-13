package polytech;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ControllerStaff
 */
@WebServlet("/ControllerStaff")
public class ControllerClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerClient() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession maSession = request.getSession();
		
		Images images = new Images();
		
		if(maSession.getAttribute("recherche")==null) maSession.setAttribute("recherche", "");
		
		
		if(maSession.getAttribute("recherche").equals("")) {
			request.setAttribute("List", images.afficherTout());
		}
		else {
			request.setAttribute("List", images.rechercher((String)maSession.getAttribute("recherche")));
		}
		
		
		request.setAttribute("resultat", images.afficherTout());
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/Client.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Images images = new Images();
		
		HttpSession maSession = request.getSession();
		String action = request.getParameter("action");
		
		
		
		if(action.equals("Rechercher")) {
			
			String motCle = request.getParameter("recherche");
			
			if(motCle.equals("")) {
				request.setAttribute("list", images.afficherTout());
			}
			else {
				request.setAttribute("list", images.rechercher(motCle));
			}
			
			maSession.setAttribute("recherche", motCle);	
		}
		
		
		
		doGet(request, response);
		response.sendRedirect("/TestImageBdd/ControllerClient");
		
	}

}
