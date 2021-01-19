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
import javax.servlet.annotation.WebServlet;
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


/**
 * Servlet implementation class ControllerEvent
 */
@WebServlet("/ControllerEvent")
public class ControllerEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	GestionPBG stub;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerEvent() {
        super();
        stub = new GestionPBGService().getGestionPBGPort();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession maSession = request.getSession();
		
		//Events events = new Events();
		gestion.Compte compte = (gestion.Compte)maSession.getAttribute("compte"); //récupération du compte de la session
		
		
		//vérifie si l'utilisateur est bien connecté et admin
		if(compte!=null && compte.isAdmin()) {
			if(maSession.getAttribute("recherche")==null) maSession.setAttribute("recherche", "");
			
			if(maSession.getAttribute("recherche")!=null) {
			
				if(maSession.getAttribute("recherche").equals("")) {
					
					request.setAttribute("list", stub.afficherEvents());
				}
				else {//vérifit si l'identifiant entré est bien un int 
					if(validation_id((String)maSession.getAttribute("recherche"))) {
						
						request.setAttribute("list", stub.rechercheEvent(Integer.parseInt((String)maSession.getAttribute("recherche"))));
					}
					else {
						request.setAttribute("erreurIdentifiant", "l'identifiant est invalide");
					}
					
				}
			}
			
			
			
		
			this.getServletContext().getRequestDispatcher("/WEB-INF/Im.jsp").forward(request, response);
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
		
		
		
		//vérifie si l'utilisateur est bien connecté et admin
		if(compte!=null && compte.isAdmin()) {
			
			//créer un nouvel event 
			if(action.equals("Envoyer")) {
				boolean valide;
				
				//vérifie si l'identifiant entré est bien un int 
				if(validation_id(request.getParameter("id"))) {
					
					//récupération des paramètres du formulaires de la jsp
					int id = Integer.parseInt(request.getParameter("id"));
					String nom = request.getParameter("nom");
					String description = request.getParameter("description");
					String date1 = request.getParameter("date");
					
					
					//vérifie si nom et description sont valides, ainsi que la date
					if(validationNom(nom) && validationDescription(description) && validation_date(date1)) {
					   
						//conversion de la date 
						XMLGregorianCalendar date = convertionDate(date1);
						
						//récupération du fichier
						try {
							Part part = request.getPart("fichier");
						
							
							//vérifie si le fichier est non vide 
							if(part!=null) {
							
								System.out.println(part.getName());
								System.out.println(part.getSize());
								System.out.println(part.getContentType());
								
								InputStream input = part.getInputStream();
								
								
								//vérifie si le fichier est bien une image de la bonne taille
								if(validationImage(input, part)) {
									
									valide = true;
									
									//extraction de l'image
									ByteArrayOutputStream out = new ByteArrayOutputStream();
									byte[] buffer = new byte[4096];
									int bytesRead = -1;
									
									while ((bytesRead = input.read(buffer)) != -1) {
									    out.write(buffer, 0, bytesRead);
									}
									 
									byte[] imageBytes = out.toByteArray();
									 
									
									String image = Base64.getEncoder().encodeToString(imageBytes);
									
									//ajout de l'event
									stub.ajoutEvent(id, nom, description, date, image);// c'est quoi ton xmltrucjesaispasquoila C'EST UNE DATE C'EST TOUT 
									
								}
								else {
									
									valide = false;
									request.setAttribute("erreurFichier", "le fichier Image est invalide");
								}
								
							}
							else {
								
								valide = false;
								request.setAttribute("erreurFichierManquant", "vous devez mettre un fichier image");
							}
							
							
						    
						}catch(IllegalStateException e) {
							
							valide = false;
							e.printStackTrace();
							request.setAttribute("erreurFichier", "le fichier Image est invalide");
						}
					}
					else {
						
						valide = false;
						request.setAttribute("erreurNomDescription", "nom ou description invalide !");
					}
				}
				else {
					
					valide = false;
					request.setAttribute("erreurIdDate", "identifiant ou date invalide !");
				}
				
				
				if(valide) {
					
					response.sendRedirect("/PGB/ControllerEvent");
				}
				else {
					doGet(request, response);
				}
				
			}
			
		
		
		
		
		
		//Modifier l'image d'un event
			if(action.equals("ModifierImage")) {
				
				
				boolean valide;
				
				//vérifie si l'identifiant entré est bien un int 
				if(validation_id(request.getParameter("id"))) {
					
					int id = Integer.parseInt(request.getParameter("id"));
					
					//vérifie si l'identifiant entré est bien un identifiant d'un event existant 
					if(stub.rechercheEvent(id)==null) {
						
						valide = false;
						request.setAttribute("erreurId", "il n'y a aucun event qui porte cet identifiant");
						
					}
					else {
						
						//récupération du fichier de l'image 
						try {
							Part part = request.getPart("fichier");
						
							
							if(part!=null) {
							
								System.out.println(part.getName());
								System.out.println(part.getSize());
								System.out.println(part.getContentType());
								
								InputStream input = part.getInputStream();
								
								
								if(validationImage(input, part)) {
									
									valide = true;
									
									//extraction de l'image
									ByteArrayOutputStream out = new ByteArrayOutputStream();
									byte[] buffer = new byte[4096];
									int bytesRead = -1;
									
									while ((bytesRead = input.read(buffer)) != -1) {
									    out.write(buffer, 0, bytesRead);
									}
									 
									byte[] imageBytes = out.toByteArray();
									 
									String image = Base64.getEncoder().encodeToString(imageBytes);
									
									
									//modification de l'image
									stub.modifImageEvent(id,image);
									
								}
								else {
									
									valide = false;
									request.setAttribute("erreurFichier", "le fichier Image est invalide");
								}
								
							}
							else {
								
								valide = false;
								request.setAttribute("erreurFichierManquant", "vous devez mettre un fichier image");
							}
							
							
						    
						}catch(IllegalStateException e) {
							
							valide = false;
							e.printStackTrace();
							request.setAttribute("erreurFichier", "le fichier Image est invalide");
						}
						
					}
			
				}
				else {
					
					valide= false;
					request.setAttribute("erreurIdDate", "identifiant ou date invalide !");
				}
				
				
				if(valide) {
					
					response.sendRedirect("/PGB/ControllerEvent");
				}
				else {
					
					doGet(request, response);
				}
				
			}
			
			
			
			//Modifier le nom, la description et la date 
			if(action.equals("ModifierNomDescriptionDate")) {
				
				boolean valide = true;
				
				//récupération des paramètres du formulaire de la jsp
				String id = request.getParameter("id");
				String nomModif = request.getParameter("nomModif2");
				String descriptionModif = request.getParameter("descriptionModif2");
				String dateModif = request.getParameter("dateModif2");
				
				
				//vérifie si l'identifiant entré n'est pas un int 
				if(validation_id(id)==false) {
					
					valide = false;
					request.setAttribute("erreurIdentifiant", "l'identifiant est invalide");
				}
				
				//vérifie si le nom entré est invalide 
				if(validationNom(nomModif)==false) {
					
					valide = false;
					request.setAttribute("erreurNom", "le nom est invalide");
				}
				
				//vérifie si la description entrée est invalide 
				if(validationDescription(descriptionModif)==false) {
					
					valide = false;
					request.setAttribute("erreurDescription", "la description est invalide");
				}
				
				//vérifie si la date entrée est invalide 
				if(validation_date(dateModif)) {
					
					valide = false;
					request.setAttribute("erreurDate", "la date est invalide");
				}
				
				if(valide) {
					
					int idModif = Integer.parseInt(id);
					
					//vérifie si l'identifiant de l'event entré correspond bien à un event existant 
					if(stub.rechercheEvent(idModif)==null) {
						
						request.setAttribute("erreurId", "il n'y a aucun event qui porte cet identifiant");
						doGet(request, response);
						
					}
					else {
						
						//convertion de la date
						XMLGregorianCalendar date = convertionDate(dateModif);
						
						//modification du nom, de la description et de la date de l'event 
						stub.modifEvent(idModif, nomModif, descriptionModif, date);
						
						response.sendRedirect("/PBG/ControllerEvent");
					}
					
					
					
				}
				else {
					doGet(request, response);
				}
				
				
				
			}
			
			
			
			//Suppression d'un event
			if(action.equals("Supprimer")) {
				
				
				
				boolean Valide;
				
				//récupération du paramètre du form de la jsp
				String idSupp = request.getParameter("idSupp");
				
				//vérifie que l'identifiant est un int
				if(validation_id(idSupp)) {
					
					int id = Integer.parseInt(idSupp);
					
					//vérifie que l'identifiant correspond bien à un event
					if(stub.rechercheEvent(id)==null){
						
						Valide = false;
						request.setAttribute("erreurId", "il n'y a aucun event qui porte cet identifiant");
						
						
					}
					else {
						
						Valide = true;
						stub.supprEvent(id);
					}
				}
				else {
					
					Valide = false;
					request.setAttribute("erreurIdentifiant", "l'identifiant est invalide");
				}
				
				
				if(Valide) {
					
					response.sendRedirect("/PBG/ControllerEvent");
					
				}
				else {
					
					doGet(request, response);
				}
				
			}
		

		}
		else {
			
			response.sendRedirect("/PBG/accueil");
		}
		
		
		
		response.sendRedirect("/PBG/ControllerEvent");
	}
	
	
	
	
	
	private boolean validationNom(String nom)  {
		if(!nom.equals("")) {
			if(nom.length() > 15) {
				return false; 
			}
			else {
				return true;
			}
		}
		else {
			return false;
			
		}
	}
	
	
	//méthode qui vérifie que la description n'est pas vide et qu'elle n'est pas trop grande 
	private boolean validationDescription(String description)  {
		if(!description.equals("")) {
			if(description.length() > 60000) {
				return false; 
			}
			else {
				return true;
			}
		}
		else {
			return false;
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
		if (mimeTypes.toString().startsWith("image") && part.getSize()>1100000) {
			   return true;
		} 
		else {
			return false;
		}

	}
	
	
	//vérifie que l'identifiant est bien un int 
	private boolean validation_id(String TestId) {
		if(TestId.equals("")) {return false;}
		
		else {
			try {
				
				int i=Integer.parseInt(TestId);

				return true;
				
			}catch(Exception e) {
				return false;
			}
		}
	}
	
	
	//vérifie que la date récupérée sous forme de string peut être convertie en une date de la bonne forme et qu'elle n'est pas vide
	private boolean validation_date(String date1) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");//définition du format atendue de la date
		if(date1.equals("")) {
			return false;
		}
		else {
			try {

				Date dateBis = formatter.parse(date1);
				
				GregorianCalendar c = new GregorianCalendar();
				c.setTime(dateBis);
				
				//XMLGregorianCalendar date = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);

				return true;
		           

		        } catch (ParseException e) {
		            return false;
		        }
		}
	}
	
	
	//conversion de la date sous forme de string en XMLGregorianCalendar
	private XMLGregorianCalendar convertionDate(String date1) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
	           /*java.util.Date date = formatter.parse(date1);
	           System.out.println(date);
	           System.out.println(formatter.format(date));*/
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
