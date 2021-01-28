package servlets;

import java.io.ByteArrayOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import eu.medsea.mimeutil.MimeUtil;
import gestion.GestionPBG;
import gestion.GestionPBGService;

//servlet de gestion des events accessibles uniquement aux administrateurs

/**
 * Servlet implementation class ControllerEvent
 */

public class GestionEvents extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	GestionPBG stub; //communication avec le service web SOAP
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionEvents() {
        super();
        stub = new GestionPBGService().getGestionPBGPort();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession maSession = request.getSession();
		
		gestion.Compte compte = (gestion.Compte)maSession.getAttribute("compte"); //récupération du compte de la session
		
		
		//vérifie si l'utilisateur est bien connecté et admin
		if(compte!=null && compte.isAdmin()) {
			
			//affichage des résultats de la recherche
			if(maSession.getAttribute("rechercheEvent")==null) maSession.setAttribute("rechercheEvent", "");
			
			if(maSession.getAttribute("rechercheEvent").equals("")) {
				request.setAttribute("resultatRecherche", stub.afficherEvents());
			}
			else {
				request.setAttribute("resultatRecherche", stub.rechercheEvent((String) maSession.getAttribute("rechercheEvent")));	
			}
			
			//affichage de tout les events
			request.setAttribute("resultat", stub.afficherEvents());
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/gestion_events.jsp").forward(request, response);
		}
		else {
			//utilisateur non connecté ou non admin
			response.sendRedirect("/PBG/accueil");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession maSession = request.getSession();
		
		String action = request.getParameter("action");

		gestion.Compte compte = (gestion.Compte)maSession.getAttribute("compte"); //récupération du compte de la session
		
		//avant toute action, on vérifie que l'utilisateur est connecté avec un compte administrateur
		if(compte!=null && stub.isAdmin(compte.getMail(),compte.getMdp())) {
			
			
			//Rechercher un event
			if(action.equals("Rechercher")) {
				
				//récupération des paramètres du form de la jsp + passage de "recherche" à la session
				String idCle = request.getParameter("rechercheEvent");
				maSession.setAttribute("rechercheEvent", idCle);
				
				response.sendRedirect("/PBG/gestion_events");				
			}
			
			
			//créer un nouvel event 
			if(action.equals("Envoyer")) {
				
				//récupération des paramètres du formulaires de la jsp
				String idStr = request.getParameter("id");
				String nom = request.getParameter("nom");
				String description = request.getParameter("description");
				String date1 = request.getParameter("date");
				
				//initialisation de variables
				int id;
				String image=null;
				
				//vérification des champs
				boolean valide=true;
				
				//vérif id
				try {
					Integer.parseInt(idStr);
				}catch(Exception e){
					valide=false;
					request.setAttribute("erreurId", "Id invalide");
				}
				
				//verif date
				//conversion de la date 
				XMLGregorianCalendar date = convertionDate(date1); //renvoi null si format incorrect
				if(date==null) {
					valide=false;
					request.setAttribute("erreurDate", "format incorrect");
				}		
	
				//récupération et verification du fichier
				try {
					Part part = request.getPart("fichier");
						
					//vérifie si le fichier est non vide 
					if(part!=null && !part.getSubmittedFileName().equals("")) {
						
						InputStream input = part.getInputStream();
								
						//vérifie si le fichier est bien une image
						if(validationImage(input, part)) {
							
							//extraction de l'image
							ByteArrayOutputStream out = new ByteArrayOutputStream();
							byte[] buffer = new byte[4096];
							int bytesRead = -1;
									
							while ((bytesRead = input.read(buffer)) != -1) {
								out.write(buffer, 0, bytesRead);
							}
									 
							byte[] imageBytes = out.toByteArray();
									 
									
							image = Base64.getEncoder().encodeToString(imageBytes);

							}
							else {
								valide = false;
								request.setAttribute("erreurFichier", "fichier invalide");
							}	
						}					   
					}catch(IllegalStateException e) {		
						valide = false;
						request.setAttribute("erreurFichier", "fichier invalide");
					}
					
				
				if(valide) {
					
					id=Integer.parseInt(idStr);
					
					//ajout de l'event
					stub.ajoutEvent(id, nom, description, date, image);
					
					response.sendRedirect("/PBG/gestion_events");
				}
				else {
					doGet(request, response);
				}
				
			}
	
		
			//Modifier l'image d'un event
			if(action.equals("Modifier l'image")) {
				
				
				//récupération des paramètres du formulaires de la jsp
				String idStr = request.getParameter("idModifImage");
				
				//initialisation de variables
				int id;
				String image=null;
				
				//vérification des champs
				boolean valide=true;
				
				//vérif id
				try {
					Integer.parseInt(idStr);
				}catch(Exception e){
					valide=false;
					request.setAttribute("erreurIdModifImage", "Id invalide");
				}
				
				//récupération et verification du fichier
				try {
					Part part = request.getPart("fichierModifImage");
						
					//vérifie si le fichier est non vide 
					if(part!=null && !part.getSubmittedFileName().equals("")) {
						
						InputStream input = part.getInputStream();
								
						//vérifie si le fichier est bien une image
						if(validationImage(input, part)) {
							
							//extraction de l'image
							ByteArrayOutputStream out = new ByteArrayOutputStream();
							byte[] buffer = new byte[4096];
							int bytesRead = -1;
									
							while ((bytesRead = input.read(buffer)) != -1) {
								out.write(buffer, 0, bytesRead);
							}
									 
							byte[] imageBytes = out.toByteArray();
									 
									
							image = Base64.getEncoder().encodeToString(imageBytes);

							}
							else {
								valide = false;
								request.setAttribute("erreurFichierModifImage", "fichier invalide");
							}	
						}					   
					}catch(IllegalStateException e) {		
						valide = false;
						request.setAttribute("erreurFichierModifImage", "fichier invalide");
					}	
				
				
				if(valide) {
					
					id=Integer.parseInt(idStr);
					
					//modification de l'image
					stub.modifImageEvent(id, image);
					
					response.sendRedirect("/PBG/gestion_events");
				}
				else {
					doGet(request, response);
				}
			}
			
			
			//Modifier le nom, la description et la date 
			if(action.equals("Modifier")) {
				
				//récupération des paramètres du formulaires de la jsp
				String idStr = request.getParameter("idModif");
				String nom = request.getParameter("nomModif");
				String description = request.getParameter("descriptionModif");
				String date1 = request.getParameter("dateModif");
				
				//initialisation de variables
				int id;
				
				//vérification des champs
				boolean valide=true;
				
				//vérif id
				try {
					Integer.parseInt(idStr);
				}catch(Exception e){
					valide=false;
					request.setAttribute("erreurIdModif", "Id invalide");
				}
				
				//verif date
				//conversion de la date 
				XMLGregorianCalendar date = convertionDate(date1); //renvoi null si format incorrect
				if(date==null) {
					valide=false;
					request.setAttribute("erreurDateModif", "format incorrect");
				}	
				
				if(valide) {
					
					id=Integer.parseInt(idStr);
					
					//modification du nom, de la description et de la date de l'event 
					stub.modifEvent(id, nom, description, date);
					
					response.sendRedirect("/PBG/gestion_events");
				}
				else {
					doGet(request, response);
				}				
			}
			
			
			//Suppression d'un event
			if(action.equals("Supprimer")) {
				
				//récupération des paramètres du formulaires de la jsp
				String idStr = request.getParameter("idSuppr");
				
				//initialisation de variables
				int id;
				
				//vérification des champs
				boolean valide=true;
				
				//vérif id
				try {
					Integer.parseInt(idStr);
				}catch(Exception e){
					valide=false;
					request.setAttribute("erreurIdSuppr", "Id invalide");
				}
				
				if(valide) {
					
					id=Integer.parseInt(idStr);
					
					//suppression de l'event
					stub.supprEvent(id);
					
					response.sendRedirect("/PBG/gestion_events");
				}
				else {
					doGet(request, response);
				}
			}
			
			
			//reset de la liste
			if(action.equals("Reset")) {
				stub.resetEvents();
				response.sendRedirect("/PBG/gestion_events");
			}
		}
		else {
			//l'utilisateur n'a pas un compte admin valide
			response.sendRedirect("/PBG/sign-out");
		}
	}
	
	
	
	
	//méthode qui vérifie si le fichier est bien une image de la bonne taille 
	private boolean validationImage(InputStream input, Part part) {
		
		
		MimeUtil.registerMimeDetector("eu.medsea.mimeutil.detector.MagicMimeMimeDetector");
		Collection<?> mimeTypes = MimeUtil.getMimeTypes(input);
	
			/*
			 * Si le fichier est bien une image, alors son en-tête MIME
			 * commence par la chaîne "image"
			 */
		if (mimeTypes.toString().startsWith("image")) {
			   return true;
		} 
		else {
			return false;
		}

	}

	
	
	//conversion de la date sous forme de string en XMLGregorianCalendar
	private XMLGregorianCalendar convertionDate(String date1) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {

			Date dateBis = formatter.parse(date1);//on doit passer par une conversion en simple Date pour imposer la format souhaité, ici yyyy-MM-dd
			
			GregorianCalendar c = new GregorianCalendar();
			c.setTime(dateBis);
			
			XMLGregorianCalendar date = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);

			return date;
	           

	        } catch (ParseException | DatatypeConfigurationException e) {
	            return null;
	        }
	}

}
