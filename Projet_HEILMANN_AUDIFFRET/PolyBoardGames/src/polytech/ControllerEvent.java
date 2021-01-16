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
@WebServlet("/Event")
public class ControllerEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerEvent() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public boolean erreurNom = false;
    public boolean erreurTailleFichier = false;
    public boolean erreurFichierManquant = false;
    public boolean erreurDescription = false;
    public boolean erreurIdentifiant = false;
    public boolean erreurNonImage = false;
    public boolean erreurDate = false;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession maSession = request.getSession();
		//récupération des données 
		Events images = new Events();
		//request.setAttribute("nom", nom);
				
		//if(maSession.getAttribute("recherche")==null) maSession.setAttribute("recherche", "");
		
		if(maSession.getAttribute("recherche")!=null) {
			if(maSession.getAttribute("recherche").equals("")) {
				request.setAttribute("list", images.afficherTout());
			}
			else {
				request.setAttribute("list", images.rechercher((String)maSession.getAttribute("recherche")));
			}
		}
		
		
		//request.setAttribute("resultat", images.afficherTout());
		request.setAttribute("erreurIdentifiant", maSession.getAttribute("erreurIdentifiant"));
		request.setAttribute("erreurNom", maSession.getAttribute("erreurNom"));
		request.setAttribute("erreurTailleFichier", maSession.getAttribute("erreurTailleFichier"));
		request.setAttribute("erreurFichierManquant", maSession.getAttribute("erreurFichierManquant"));
		request.setAttribute("erreurDescription", maSession.getAttribute("erreurDescription"));
		request.setAttribute("erreurDate", maSession.getAttribute("erreurDate"));
		request.setAttribute("erreurNonImage", maSession.getAttribute("erreurNonImage"));
		
		

		
		
		
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/Im.jsp").forward(request, response);
	}


		
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		InputStream input = null;
		//récupération des données 
		Events events = new Events();
			
		HttpSession maSession = request.getSession();
		
		String action = request.getParameter("action");
		
		
		if (action.equals("Envoyer")) {
			if(validation_id(request.getParameter("id"), events, true)==false || validation_date(request.getParameter("date"))==false) {
				//validationNom(request.getParameter("nom"));
				//validationDescription(request.getParameter("description"));
				if(erreurIdentifiant) {
					erreurIdentifiant = false;
					maSession.setAttribute("erreurIdentifiant", "l'identifiant est invalide");
					}
				
				if(erreurDate) {
					erreurDate = false;
					maSession.setAttribute("erreurDate", "la date est invalide");
					}	

			}
			else {
				//erreurIdentifiant = false;
				int id = Integer.parseInt(request.getParameter("id"));
				String nom = request.getParameter("nom");
				String description = request.getParameter("description");
				String date1 = request.getParameter("date");
				
				//conversion de la string date en une Date java.util
			    Date date = convertionDate(date1);
				try {
					Part part = request.getPart("fichier");
				
					if(part!=null) {
					
						System.out.println(part.getName());
						System.out.println(part.getSize());
						System.out.println(part.getContentType());
						
						input = part.getInputStream();
						
					}
							
				    if(validationImage(events, input, part) && events.verification_id2(id, true) && validationNom(nom) && validationDescription(description)) {

				    	//conversion de java util date en sql date pour la base de donnée 
				    	java.sql.Date date2 = new java.sql.Date(date.getTime());
				    	
				    	events.uploadImage(id, nom, description, date2, input);
				  	
				    }
				    else {
				    	
				    	if(erreurNonImage) {
							erreurNonImage = false;
							maSession.setAttribute("erreurDate", "le fichier n'est pas une image");
						}
						if(erreurTailleFichier) {
							erreurTailleFichier = false;
							maSession.setAttribute("erreurTailleFichier", "le fichier est trop lourd, limite = 1Mo");
							}
						if(erreurNom) {
							erreurNom = false;
							maSession.setAttribute("erreurNom", "le nom est invalide");
						}
						if(erreurDescription) {
							erreurDescription = false;
							maSession.setAttribute("erreurDescription", "la description est invalide");
						}
						
				    }
				    
				}catch(IllegalStateException e) {
					e.printStackTrace();
				}
			}

		}
		
		
		
		if(action.equals("Supprimer")) {
			if(validation_id(request.getParameter("idSupp"), events, false)) {
				int idSupp = Integer.parseInt(request.getParameter("idSupp"));
				events.supprimerImage(idSupp);
			}
			else {
				maSession.setAttribute("erreurIdentifiant", "l'identifiant est invalide");
			}
		}

		
		
		if(action.equals("ModifierImage")) {
			
			if(validation_id(request.getParameter("idModif1"), events, false)==false) {
				maSession.setAttribute("erreurIdentifiant", "l'identifiant est invalide");
			
			}
			else {
				erreurIdentifiant = false;
				int idModif = Integer.parseInt(request.getParameter("idModif1"));
				
				try {
					Part part = request.getPart("fichierModif");
				
					if(part!=null) {
					
						System.out.println(part.getName());
						System.out.println(part.getSize());
						System.out.println(part.getContentType());
						
						input = part.getInputStream();
						
					    if(validationImage(events, input, part) && events.verification_id2(idModif, false)) {
					    	erreurIdentifiant = events.verification_id2(idModif, true);
					    	events.modifierImage(idModif, input);
							
					    }
					    else {
					    	erreurIdentifiant = events.verification_id2(idModif, false);
					    	if(erreurNonImage) {
								erreurNonImage = false;
								maSession.setAttribute("erreurDate", "le fichier n'est pas une image");
							}
							if(erreurTailleFichier) {
								erreurTailleFichier = false;
								maSession.setAttribute("erreurTailleFichier", "le fichier est trop lourd, limite = 1Mo");
								}
							if(erreurIdentifiant) {
								erreurIdentifiant = false;
								maSession.setAttribute("erreurIdentifiant", "l'identifiant est invalide");
								}
					    }
						
					}
					else {
							maSession.setAttribute("erreurFichierManquant", "le fichier est manquant");

					}
				}catch(IllegalStateException e) {
					e.printStackTrace();
					
				}
			}
		
		}
		
		
		
		
		if(action.equals("ModifierNomDescriptionDate")) {
			
			String nomModif = request.getParameter("nomModif2");
			String descriptionModif = request.getParameter("descriptionModif2");
			String dateModif = request.getParameter("dateModif2");
			
			if(validation_date(dateModif) && validation_id(request.getParameter("idModif2"), events, true)) {
				int idModif = Integer.parseInt(request.getParameter("idModif2"));
				Date date = convertionDate(dateModif);
				
				if(validationNom(nomModif) && validationDescription(descriptionModif) && events.verification_id2(idModif, false)) {
				
				    java.sql.Date date2 = new java.sql.Date(date.getTime());
				    events.modifierNomDescriptionDate(idModif, nomModif, descriptionModif, date2);
					
				}
				else {
					erreurIdentifiant = events.verification_id2(idModif, true);
					if(erreurNom) {
						erreurNom = false;
						maSession.setAttribute("erreurNom", "le nom est invalide");
					}
					if(erreurDescription) {
						erreurDescription = false;
						maSession.setAttribute("erreurDescription","la description est invalide");
					}
				}
			}
			else {
				
				if(erreurDate) {
					erreurDate = false;
					maSession.setAttribute("erreurDate",  "la date est invalide");
					}	
				if(erreurIdentifiant) {
					erreurIdentifiant = false;
					maSession.setAttribute("erreurIdentifiant", "l'identifiant est invalide");
					}
			}
		}
		
		
		
		
		
		if(action.equals("Rechercher")) {
			
			String motCle = request.getParameter("recherche");
			
			maSession.setAttribute("recherche", motCle);	
		}
		
		
		 erreurNom = false;
		 erreurTailleFichier = false;
		 erreurFichierManquant = false;
		 erreurDescription = false;
		 erreurIdentifiant = false;
		 erreurNonImage = false;
		 erreurDate = false;
		
		
    	
		
		//doGet(request, response);
		response.sendRedirect("/TestImageBdd/ControllerEvent");
		
		
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
				erreurDescription = false;
				return true;
			}
		}
		else {
			erreurDescription = true;
			return false;
		}
	}
	
	private boolean validationImage(Events images, InputStream input, Part part) {
		/* Extraction du type MIME du fichier depuis l'InputStream nommé "contenu" */
		if(part!=null) {

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
			if(part.getSize()>1100000) {
				erreurTailleFichier = true;
				return false;
			}
			else {
				return true;
			}
		}
		else {
			erreurFichierManquant = true;
			return false;
		}
	}
	
	
	private boolean validation_id(String TestId, Events images, boolean est_pas_present) {
		if(TestId.equals("")) {return false;}
		else {
			try {
				int i=Integer.parseInt(TestId);
				if(images.verification_id2(i, est_pas_present)) {
					return true;
				}
				else {
					return false;
				}
				
			}catch(Exception e) {
				return false;
			}
		}
	}
	
	
	private boolean validation_date(String date1) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		if(date1.equals("")) {
			erreurDate = true;
			return false;
		}
		else {
			try {
		           java.util.Date date = formatter.parse(date1);
		           System.out.println(date);
		           System.out.println(formatter.format(date));
		           erreurDate = false;
		           return true;
	
		        } catch (ParseException e) {
		        	erreurDate = true;
		            return false;
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
