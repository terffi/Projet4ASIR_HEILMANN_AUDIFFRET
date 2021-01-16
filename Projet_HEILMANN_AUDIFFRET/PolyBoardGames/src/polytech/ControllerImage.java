package polytech;

import java.io.IOException;



import java.io.InputStream;
import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Formatter;


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
    
    public boolean erreurNom;
    public boolean erreurTailleFichier;
    public boolean erreurFichierManquant;
    public boolean erreurDescription;
    public boolean erreurIdentifiant;
    public boolean erreurNonImage;
    public boolean erreurDate;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession maSession = request.getSession();
		//récupération des données 
		Images images = new Images();
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
		Images images = new Images();
			
		HttpSession maSession = request.getSession();
		
		String action = request.getParameter("action");
		
		
		if (action.equals("Envoyer")) {
			if(validation_id(request.getParameter("id")) && validation_date(request.getParameter("date"))) {
				erreurIdentifiant=true;
				validationNom(request.getParameter("nom"));
				validationDescription(request.getParameter("description"));
			}
			else {
				erreurIdentifiant = false;
				int id = Integer.parseInt(request.getParameter("id"));
				String nom = request.getParameter("nom");
				String description = request.getParameter("description");
				String date1 = request.getParameter("date");
				
				//conversion de la string date en une Date java.util
			    Date date = convertionDate(date1);
				try {
					Part part = request.getPart("fichier");
				
					if(part!=null) {
					
						erreurs.clear();//suppression des anciennes erreurs
						System.out.println(part.getName());
						System.out.println(part.getSize());
						System.out.println(part.getContentType());
						
						input = part.getInputStream();
						
					}
							
				    if(validationImage(images, input, part) && images.verification_id2(id, true) && validationNom(nom) && validationDescription(description)) {
				    	erreurIdentifiant = images.verification_id2(id, true);  
				    	erreurNom = validationNom(nom);
				    	System.out.println(erreurDate);
				    	//conversion de java util date en sql date pour la base de donnée 
				    	java.sql.Date date2 = new java.sql.Date(date.getTime());
				    	
				    	images.uploadImage(id, nom, description, date2, input);
				  	
				    }
				}catch(IllegalStateException e) {
					e.printStackTrace();
					
					images.setErreur("fichier", "fichier trop volumineux, limite = 128Mo");
					
				}
			}
			request.setAttribute("erreurNom", erreurNom);
	    	request.setAttribute("erreurTailleFichier", erreurTailleFichier);
	    	request.setAttribute("erreurFichierManquant", erreurFichierManquant);
	    	request.setAttribute("erreurDescription", erreurDescription);
	    	request.setAttribute("erreurIdentifiant", erreurIdentifiant);
	    	request.setAttribute("erreurDate", erreurDate);
		}
		
		
		if(action.equals("Supprimer")) {
			if(validation_id(request.getParameter("idSupp"))) {
				erreurIdentifiant=true;
			}
			else {
				erreurIdentifiant = false;
				int idSupp = Integer.parseInt(request.getParameter("idSupp"));
				images.supprimerImage(idSupp);
			}
		}

		if(action.equals("ModifierImage")) {
			
			if(validation_id(request.getParameter("idModif1"))) {
				erreurIdentifiant=true;
			}
			else {
				erreurIdentifiant = false;
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
							
				    if(validationImage(images, input, part) && images.verification_id2(idModif, false)) {
				    	erreurIdentifiant = images.verification_id2(idModif, true);
				    	images.modifierImage(idModif, input);
				  	
				    }
				}catch(IllegalStateException e) {
					e.printStackTrace();
					
				}
			}
			request.setAttribute("erreurTailleFichier", erreurTailleFichier);
	    	request.setAttribute("erreurFichierManquant", erreurFichierManquant);
	    	request.setAttribute("erreurIdentifiant", erreurIdentifiant);
			
			
		}
		
		if(action.equals("ModifierNomDescriptionDate")) {
			
			int idModif = Integer.parseInt(request.getParameter("idModif2"));
			String nomModif = request.getParameter("nomModif2");
			String descriptionModif = request.getParameter("descriptionModif2");
			String dateModif = request.getParameter("dateModif");
			Date date = convertionDate(dateModif);
			
			if(validation_date(request.getParameter("dateModif"))) {
				if(validationNom(nomModif) && validationDescription(descriptionModif) && images.verification_id2(idModif, false)) {
					erreurIdentifiant = images.verification_id2(idModif, true);

					
				    java.sql.Date date2 = new java.sql.Date(date.getTime());
				    images.modifierNomDescriptionDate(idModif, nomModif, descriptionModif, date2);
					erreurDate = true ;
					
					
				}
			}
			
			request.setAttribute("erreurIdentifiant", erreurIdentifiant);
			request.setAttribute("erreurNom", erreurNom);
			request.setAttribute("erreurDescription", erreurDescription);
			request.setAttribute("erreurDate", erreurDate);
			
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
	
	
	
	
	
	
	
	
	
	//méthodes de validation des paramètres des form de la jsp
	private boolean validationNom(String nom)  {
		if(!nom.equals("")) {
			if(nom.length() > 15) {
				erreurNom = true;
				return false; 
			}
			else {
				erreurNom = false;
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
			erreurTailleFichier = true;
			return false;
		}
		else {
			return true;
		}
	}
	
	private boolean validation_id(String TestId) {
		if(TestId.equals("")) {return true;}
		else {
			try {
				int i=Integer.parseInt(TestId);
				return false;
			}catch(Exception e) {
				return true;
			}
		}
	}
	
	private boolean validation_date(String date1) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		if(date1.equals("")) {
			erreurDate = true;
			return true;
		}
		else {
			try {
		           java.util.Date date = formatter.parse(date1);
		           System.out.println(date);
		           System.out.println(formatter.format(date));
		           erreurDate = false;
		           return false;
	
		        } catch (ParseException e) {
		        	erreurDate = true;
		            return true;
		        }
		}
	}
	
	private Date convertionDate(String date1) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
	           java.util.Date date = formatter.parse(date1);
	           System.out.println(date);
	           System.out.println(formatter.format(date));
	           erreurDate = false;
	           return date;
	           

	        } catch (ParseException e) {
	        	erreurDate = true;
	            return null;
	        }
	}
	

	
	
	
	

}
