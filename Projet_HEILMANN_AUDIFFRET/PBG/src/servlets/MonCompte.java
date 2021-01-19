package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gestion.Compte;
import gestion.GestionPBG;
import gestion.GestionPBGService;

//servlet de gestion personnelle de compte

/**
 * Servlet implementation class MonCompte
 */
@WebServlet("/mon_compte")
public class MonCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	GestionPBG stub; //communication avec le service web SOAP
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MonCompte() {
        super();
		stub = new GestionPBGService().getGestionPBGPort();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession maSession = request.getSession();
		
		//on v�rifie que l'utilisateur est connect�
		if(maSession.getAttribute("compte")!=null) {
			this.getServletContext().getRequestDispatcher("/WEB-INF/mon_compte.jsp").forward(request, response);
		}
		else {
			//l'utilisateur n'est pas connect�
			response.sendRedirect("/PBG/accueil");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession maSession = request.getSession();
		
		Compte compte = (Compte) maSession.getAttribute("compte"); //r�cup�ration du compte de la session
		
		//avant toute action, on v�rifie que l'utilisateur est connect� avec un compte valide (pr�sent dans la liste)
		if(compte!=null && stub.compteValide(compte.getMail(),compte.getMdp()))
		{
			String action = request.getParameter("action");
	
			String mail = compte.getMail(); //r�cup�ration du mail
			
			//modification du comptes
			if(action.equals("Modifier le compte")) {
				
				String mdp = request.getParameter("mdp"); //r�cup�ration du mot de passe rentr�
				
				compte = stub.connexion(mail, mdp); //v�rification du mot de passe (on reconnecte l'utilisateur)
				
				if(compte!=null) {
					
					String nom = request.getParameter("nom");
					String prenom = request.getParameter("prenom");
					
					//v�rification des champs
					boolean valide=true;
					
					String champVide="Ce champ ne peut pas �tre vide";
					if(prenom.equals("")) {
						request.setAttribute("erreurPrenom", champVide);
						valide=false;
					}
					if(nom.equals("")) {
						request.setAttribute("erreurNom", champVide);
						valide=false;
					}
					
					if(valide) {
						//modification du compte
						
						compte.setNom(nom);
						compte.setPrenom(prenom);
						
						stub.modifCompte(nom, prenom, mail);
						
						maSession.setAttribute("compte", compte); //mise � jour du compte de la session	
						
						response.sendRedirect("/PBG/mon_compte");
					}
					else {
						//un ou plusieurs champs ne sont pas remplis
						doGet(request,response);
					}
				}
				else {
					//mot de passe incorrect
					request.setAttribute("erreurMdp", "Mot de passe incorrecte");
					doGet(request,response);
				}	
			}
			
			//modification du mot de passe
			if(action.equals("Changer de mot de passe")) {
				
				String mdp = request.getParameter("modifMdp"); //r�cup�ration du mot de passe rentr�
				
				compte = stub.connexion(mail, mdp); //v�rification du mot de passe (on reconnecte l'utilisateur)

				if(compte!=null) {
					
					String newMdp = request.getParameter("newMdp"); //r�cup�ration du nouveau mot de passe
					String confirmMdp = request.getParameter("confirmMdp");
					
					//v�rifications des champs
					boolean valide=true;
					
					if(newMdp.equals("")) {
						request.setAttribute("erreurNewMdp", "Ce champ ne peut pas �tre vide");
						valide=false;
					}
					else {
						if(!newMdp.equals(confirmMdp)) {
							request.setAttribute("erreurConfirmMdp", "Les mots de passe de correspondent pas");
							valide=false;
						}
					}
					
					if(valide) {
						//modification du mot de passe
						
						compte.setMdp(stub.modifMdpCompte(mail, newMdp));
						
						if(compte.getMdp()==null) {
							//erreur lors de l'encryption
							request.setAttribute("erreurMdp", "Une erreur est survenu");
							
							compte = stub.connexion(mail, mdp); //on r�cup�re l'ancien mot de passe dans la session
							
							doGet(request,response);						
						}
							
						maSession.setAttribute("compte", compte); //mise � jour du compte de la session
							
						response.sendRedirect("/PBG/mon_compte");
					}
					else {
						//champs incorrect
						doGet(request,response);
					}				
				}
				else {
					//mot de passe incorrect
					request.setAttribute("erreurModifMdp", "Mot de passe incorrecte");
					doGet(request,response);
				}
			}		
			
			//suppression du compte
			if(action.equals("Supprimer le compte")) {
				
				String mdp = request.getParameter("mdpSuppr"); //r�cup�ration du mot de passe rentr�
				
				compte = stub.connexion(mail, mdp); //v�rification du mot de passe (on reconnecte l'utilisateur)
				
				if(compte!=null) {
					stub.supprCompte(mail);;
					
					response.sendRedirect("/PBG/sign-out"); //d�connexion de l'utilisateur
				}
				else {
					//mot de passe incorrect
					request.setAttribute("erreurSupprMdp", "Mot de passe incorrecte");
					doGet(request,response);
				}
			}
			
		}
		else {
			//le compte n'est pas valide, on d�connecte l'utilisateur et on redirige � la page d'acceuil
			compte=null;
			maSession.setAttribute("compte", compte);
			response.sendRedirect("/PBG/accueil");
		}
		
		
		
	}

}
