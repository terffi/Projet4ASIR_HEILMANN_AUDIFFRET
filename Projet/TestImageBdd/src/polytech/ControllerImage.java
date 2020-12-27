package polytech;

import java.io.IOException;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping;






/**
 * Servlet implementation class Image
 */
@WebServlet("/Image")
public class ControllerImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerImage() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public int erreur=0;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		
	
		
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/Im.jsp").forward(request, response);
	}


		
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		InputStream input = null;
		
		//récupération des données 
		Image image = new Image();
		Images images = new Images();
		
		String action = request.getParameter("action");
		
		String nom = request.getParameter("nom");
		//request.setAttribute("nom", nom);
				
			//les données sont multipart, donc pour les traiter on utilise une certaine méthode
		try {
			
		
			Part part = request.getPart("fichier");
				
			
		//on détermine si c'est un champ classique ou non
				
		//String nomFichier = getNomFichier(part);
				
		//if (nomFichier != null ) {
		//	String nomChamp = part.getName();
		//	request.setAttribute( nomChamp, nomFichier );
			        
		//	System.out.println(part.getName());
		//	System.out.println(part.getName());
		
		
		//	System.out.println(part.getName());
			        
			       
		//}
		
			if(part!=null) {
			
				System.out.println(part.getName());
				System.out.println(part.getSize());
				System.out.println(part.getContentType());
				
				input = part.getInputStream();
				erreur=0;
			}
					
		    if(action.contentEquals("Envoyer") && validationImage(images, part)) {
		    	
		    	images.uploadImage(nom, input);
		  	
		    }
		    else {
		    	request.setAttribute("erreur", erreur);
		    }
		}catch(IllegalStateException e) {
			e.printStackTrace();
			
			images.setErreur("fichier", "fichier trop volumineux, limite = 1Mo");
			
		}
		
		
		
		if(action.equals("Rechercher")) {
			request.setAttribute("list", images.rechercher(nom));
		}
			
				
		
		
		doGet(request, response);
	}
	
	

	private void validationNom(String nom) throws FormValidationException {
		if(nom != null) {
			if(nom.length() > 20) {
				throw new FormValidationException("nom invalide !");
                 
			}
		}
		else {
			throw new FormValidationException("un nom est nécessaire");
		}
	}
	
	private boolean validationImage(Images images, Part part) {
		if(part.getSize()>1048576) {
			images.setErreur("fichier", "fichier trop volumineux, limite = 1Mo");
			erreur=1;
			return false;
		}
		else {
			return true;
		}
	}
	
	/*private String validationImage(HttpServletRequest request) throws IOException, ServletException, FormValidationException {
		String nomFichier = null;
		
		try {
			Part part = request.getPart("fichier");
			nomFichier = getNomFichier(part);
			
		}catch(IllegalStateException e) {
			e.printStackTrace();
			
			throw new FormValidationException("Le fichier envoyé ne peut dépasser 1 Mo");
		}
		return nomFichier;
	}*/
	
		
	
	
	
	
	private String getNomFichier(Part part) {
		//Boucle sur chacun des paramètres de l'en-tête "content-disposition"
		for(String contentDisposition : part.getHeader("content-disposition").split(";")) {
			 //Recherche de l'éventuelle présence du paramètre "fichier"
			if(contentDisposition.trim().startsWith("fichier")) {
				//Si "filename" est présent, alors renvoi de sa valeur, c'est-à-dire du nom de fichier
				return contentDisposition.substring(contentDisposition.indexOf('='));
			}
		}
		return null;
	}

}
