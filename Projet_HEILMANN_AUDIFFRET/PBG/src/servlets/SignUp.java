package servlets;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gestion.Compte;
import gestion.GestionPBG;
import gestion.GestionPBGService;
import io.joshworks.restclient.http.HttpResponse;
import io.joshworks.restclient.http.Unirest;

import javax.json.*;



/**
 * Servlet implementation class SignUp
 */
@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	GestionPBG stub;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUp() {
        super();
		stub = new GestionPBGService().getGestionPBGPort();
		stub.initialisation();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession maSession = request.getSession();

		if(maSession.getAttribute("compte")!=null)response.sendRedirect("/PBG/acceuil"); //l'utilisateur est déjà connecté
		else this.getServletContext().getRequestDispatcher("/WEB-INF/signup.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String prenom = request.getParameter("prenom");
		String nom = request.getParameter("nom");
		String mail = request.getParameter("mail").replaceAll(" ", "");
		String mdp = request.getParameter("mdp");
		String confirmMdp = request.getParameter("confirmMdp");
		
		//vérification des données rentrées
		
		boolean valide=true;
		
		//vérification que tout les champs sont bien remplis
		String champVide="champ obligatoire";
		
		//verif prenom
		if(prenom.equals("")) {
			request.setAttribute("erreurPrenom", champVide);
			valide=false;
		}
		else {
			//Dans le cas où il y'a une erreur dans un autre champ, on renvoit les champs non vide pour que l'utilisateur
			//n'ait pas à tout réécrire
			request.setAttribute("prenom", prenom);
		}
		
		//verif nom
		if(nom.equals("")) {
			request.setAttribute("erreurNom", champVide);
			valide=false;
		}
		else {
			request.setAttribute("nom", nom);
		}
		
		//verif mail
		if(mail.equals("")) {
			request.setAttribute("erreurMail", champVide);
			valide=false;
		}
		else {
			//vérification du format de l'adresse mail
			String[] mailSplit = mail.split("@");
			if(mailSplit.length!=2) {
				request.setAttribute("erreurMail", "format invalide");
				valide=false;	
			}
			
			else {
				//vérification de l'adresse mail via ZeroBounce (https://rapidapi.com/leeann/api/zerobounce1)

				HttpResponse<String> responseMail = Unirest.get("https://zerobounce1.p.rapidapi.com/v2/validate?ip_address=%20&email="+mailSplit[0]+"%40"+mailSplit[1])
						.header("x-rapidapi-key", "7b136cdb65msh78a73bc2ee67aa4p13a1b1jsn10e1977237bf")
						.header("x-rapidapi-host", "zerobounce1.p.rapidapi.com")
						.asString();

				
				//traitement de la reponse de l'API
				
				/*
				Exemple de reponse :					
				{
				"address":"example@gmail.com",
				"status":"invalid",
				"sub_status":"mailbox_not_found",
				"free_email":true,
				"did_you_mean":NULL,
				"account":"example",
				"domain":"gmail.com",
				"domain_age_days":"9289",
				"smtp_provider":"google",
				"mx_found":"true",
				"mx_record":"gmail-smtp-in.l.google.com",
				"firstname":NULL,
				"lastname":NULL,
				"gender":NULL,
				"country":NULL,
				"region":NULL,
				"city":NULL,
				"zipcode":NULL,
				"processed_at":"2021-01-16 13:54:40.002"
				}	
				*/
					
				//convertion de la réponse string en objet Json
				JsonReader jsonReader = Json.createReader(new StringReader(responseMail.getBody()));
				JsonObject reponseVerifMail = jsonReader.readObject();
				jsonReader.close();					
					
				if(!reponseVerifMail.getString("status").equals("valid"))	{
					//adresse mail invalide
					request.setAttribute("erreurMail", "mail invalide");
					valide=false;	
				}
			}
			
			request.setAttribute("mail", mail);
		}
		
		//verif mdp
		if(mdp.equals("")) {
			request.setAttribute("erreurMdp", champVide);
			valide=false;
		}
		else {
			//vérification du mot de passe
			if(!mdp.equals(confirmMdp)) {
				request.setAttribute("erreurMdp", "les mots de passe de correspondent pas");
				valide=false;
			}
		}
		
		
		if(valide) {
			//création du compte
			Compte compte = stub.ajoutCompte(nom, prenom, mail, mdp);
			
			//vérification de la disponibilité de l'adresse mail (Comptes().ajouterUnCompte renvoie null si l'adresse mail est
			//déjà prise)
			if(compte!=null) {
				HttpSession maSession = request.getSession();
				
				maSession.setAttribute("compte", compte);
				response.sendRedirect("/PBG/acceuil");
			}
			else {
				request.setAttribute("erreurMail", "cette adresse mail est déjà utilisée");
				doGet(request,response);
			}
		}
		else {
			doGet(request,response);
		}
		
		
	}

}
