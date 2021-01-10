package polytech;

import java.io.IOException;


import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;


import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping;

import eu.medsea.mimeutil.MimeUtil;






/**
 * Servlet implementation class Image
 */
@WebServlet("/Image")
public class ControllerImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList<Integer> erreurs = new ArrayList<Integer>();
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerImage() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public boolean erreurNom=false;
    public boolean erreurTailleFichier=false;
    public boolean erreurFichierManquant=false;
    public boolean erreurDescription=false;
    public boolean erreurIdentifiant=false;
    public boolean erreurNonImage=false;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession maSession = request.getSession();
		
		InputStream input = null;
		
		//récupération des données 
		Image image = new Image();
		Images images = new Images();
		
		
		
		String nom = request.getParameter("nom");
		//request.setAttribute("nom", nom);
				
		if(maSession.getAttribute("recherche")==null) maSession.setAttribute("recherche", "");
		
		
		if(maSession.getAttribute("recherche").equals("")) {
			request.setAttribute("List", images.afficherTout());
		}
		else {
			request.setAttribute("List", images.rechercher((String)maSession.getAttribute("recherche")));
		}
		
		
		request.setAttribute("resultat", images.afficherTout());

		
		
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/Im.jsp").forward(request, response);
	}


		
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		InputStream input = null;
		
		//récupération des données 
		Image image = new Image();
		Images images = new Images();
			
		
		
		
		HttpSession maSession = request.getSession();
		
		String action = request.getParameter("action");
		
		
		if (action.equals("Envoyer")) {
			int id = Integer.parseInt(request.getParameter("id"));
			String nom = request.getParameter("nom");
			String description = request.getParameter("description");
			try {
				Part part = request.getPart("fichier");
			
				if(part!=null) {
				
					erreurs.clear();//suppression des anciennes erreurs
					System.out.println(part.getName());
					System.out.println(part.getSize());
					System.out.println(part.getContentType());
					
					input = part.getInputStream();
					
				}
						
			    if(validationImage(images, input, part) && images.verification_id(id, true) && validationNom(nom) && validationDescription(description)) {
			    	erreurIdentifiant = images.verification_id(id, true);  
			    	images.uploadImage(id, nom, description, input);
			  	
			    }
			    else {
			
			    	request.setAttribute("erreurNom", erreurNom);
			    	request.setAttribute("erreurTailleFichier", erreurTailleFichier);
			    	request.setAttribute("erreurFichierManquant", erreurFichierManquant);
			    	request.setAttribute("erreurDescription", erreurDescription);
			    	request.setAttribute("erreurIdentifiant", erreurIdentifiant);
			    }
			}catch(IllegalStateException e) {
				e.printStackTrace();
				
				images.setErreur("fichier", "fichier trop volumineux, limite = 128Mo");
				
			}
		}
		
		
		if(action.equals("Supprimer")) {
			
			
			int idSupp = Integer.parseInt(request.getParameter("idSupp"));
			
			images.supprimerImage(idSupp);
		}

		if(action.equals("ModifierImage")) {
			
			int idModif = Integer.parseInt(request.getParameter("idModif1"));
			
			try {
				Part part = request.getPart("fichierModif");
			
				if(part!=null) {
				
					erreurs.clear();//suppression des anciennes erreurs
					System.out.println(part.getName());
					System.out.println(part.getSize());
					System.out.println(part.getContentType());
					
					input = part.getInputStream();
					
				}
						
			    if(validationImage(images, input, part) && images.verification_id(idModif, false)) {
			    	erreurIdentifiant = images.verification_id(idModif, true);
			    	images.modifierImage(idModif, input);
			  	
			    }
			    else {
			    	request.setAttribute("erreurTailleFichier", erreurTailleFichier);
			    	request.setAttribute("erreurFichierManquant", erreurFichierManquant);
			    	request.setAttribute("erreurIdentifiant", erreurIdentifiant);
			    }
			}catch(IllegalStateException e) {
				e.printStackTrace();
				
				images.setErreur("fichier", "fichier trop volumineux, limite = 128Mo");
				
			}
			
			
		}
		
		if(action.equals("ModifierNomDescription")) {
			
			int idModif = Integer.parseInt(request.getParameter("idModif2"));
			String nomModif = request.getParameter("nomModif2");
			String descriptionModif = request.getParameter("descriptionModif2");
			
			if(validationNom(nomModif) && validationDescription(descriptionModif) && images.verification_id(idModif, false)) {
				erreurIdentifiant = images.verification_id(idModif, true);
				images.modifierNomDescription(idModif, nomModif, descriptionModif);
			}
			else {
				request.setAttribute("erreurIdentifiant", erreurIdentifiant);
				request.setAttribute("erreurNom", erreurNom);
				request.setAttribute("erreurDescription", erreurDescription);
			}
			
		}
		
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
		response.sendRedirect("/TestImageBdd/ControllerImage");
		
	}
	
	
	

	private boolean validationNom(String nom)  {
		if(!nom.equals("")) {
			if(nom.length() > 15) {
				erreurNom = true;
				return false; 
			}
			else {
				return true;
			}
		}
		else {
			erreurNom = true;
			return false;
			
		}
	}
	
	
	private boolean validationDescription(String description)  {
		if(!description.equals("")) {
			if(description.length() > 60000) {
				erreurDescription = true;
				return false; 
			}
			else {
				return true;
			}
		}
		else {
			erreurDescription = true;
			return false;
		}
	}
	
	private boolean validationImage(Images images, InputStream input, Part part) {
		/* Extraction du type MIME du fichier depuis l'InputStream nommé "contenu" */
		MimeUtil.registerMimeDetector("eu.medsea.mimeutil.detector.MagicMimeMimeDetector");
		Collection<?> mimeTypes = MimeUtil.getMimeTypes(input);

		/*
		 * Si le fichier est bien une image, alors son en-tête MIME
		 * commence par la chaîne "image"
		 */
		if (mimeTypes.toString().startsWith("image") ) {
		    /* Appeler ici la méthode d'écriture du fichier sur le disque... */
		} 
		else {
		    /* Envoyer ici une exception précisant que le fichier doit être une image... */
			erreurNonImage = true;
			return false;
		}
		if(part.getSize()>128000000) {
			images.setErreur("fichier", "fichier trop volumineux, limite = 128Mo");
			erreurTailleFichier = false;
			return false;
		}
		else {
			return true;
		}
	}
	

	
	
	
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
