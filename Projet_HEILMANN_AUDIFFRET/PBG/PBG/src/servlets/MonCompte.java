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

/**
 * Servlet implementation class MonCompte
 */
@WebServlet("/MonCompte")
public class MonCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	GestionPBG stub;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MonCompte() {
        super();
		stub = new GestionPBGService().getGestionPBGPort();
		stub.initialisation();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession maSession = request.getSession();

		if(maSession.getAttribute("compte")!=null) {
			this.getServletContext().getRequestDispatcher("/WEB-INF/moncompte.jsp").forward(request, response);
		}
		else {
			//l'utilisateur n'est pas connecté
			response.sendRedirect("/PBG/acceuil");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession maSession = request.getSession();
		
		Compte compte = (Compte) maSession.getAttribute("compte"); //récupération du compte de la session
		
		//avant toute action, on vérifie que l'utilisateur est connecté avec un compte valide (présent dans la liste)
		if(compte!=null && stub.compteValide(compte))
		{
			String action = request.getParameter("action");
	
			String mail = compte.getMail(); //récupération du mail
			
			if(action.equals("Modifier le compte")) {
				String mdp = request.getParameter("mdp"); //récupération du mot de passe rentré
				
				compte = stub.connexion(mail, mdp); //vérification du mot de passe (on reconnecte l'utilisateur)
				
				if(compte!=null) {
					
					String nom = request.getParameter("nom");
					String prenom = request.getParameter("prenom");
					
					boolean valide=true;
					
					String champVide="Ce champ ne peut pas être vide";
					if(prenom.equals("")) {
						request.setAttribute("erreurPrenom", champVide);
						valide=false;
					}
					if(nom.equals("")) {
						request.setAttribute("erreurNom", champVide);
						valide=false;
					}
					
					if(valide) {
					
						compte.setNom(nom);
						compte.setPrenom(prenom);
						
						stub.modifCompte(nom, prenom, mail);
						
						maSession.setAttribute("compte", compte);	
						
						response.sendRedirect("/PBG/mon_compte");
					}
					else {
						doGet(request,response);
					}
				}
				else {
					request.setAttribute("erreurMdp", "Mot de passe incorrecte");
					doGet(request,response);
				}	
			}
			
			
			
			
			if(action.equals("Changer de mot de passe")) {
				String mdp = request.getParameter("modifMdp"); //récupération du mot de passe rentré
				
				compte = stub.connexion(mail, mdp); //vérification du mot de passe (on reconnecte l'utilisateur)
				
				if(compte!=null) {
					
					String newMdp = request.getParameter("newMdp"); //récupération du nouveau mot de passe
					String confirmMdp = request.getParameter("confirmMdp");
					
					boolean valide=true;
					
					if(newMdp.equals("")) {
						request.setAttribute("erreurNewMdp", "Ce champ ne peut pas être vide");
						valide=false;
					}
					else {
						if(!newMdp.equals(confirmMdp)) {
							request.setAttribute("erreurConfirmMdp", "Les mots de passe de correspondent pas");
							valide=false;
						}
					}
					
					if(valide) {
					
						compte.setMdp(stub.modifMdpCompte(mail, newMdp));
						
						if(compte.getMdp()==null) {
							//erreur lors de l'encryption
							request.setAttribute("erreurMdp", "Une erreur est survenu");
							
							compte = stub.connexion(mail, mdp); //on récupère l'ancien mot de passe dans la session
							
							doGet(request,response);						
						}
							
						maSession.setAttribute("compte", compte);	
							
						response.sendRedirect("/PBG/mon_compte");
					}
					else {
						doGet(request,response);
					}				
				}
				else {
					request.setAttribute("erreurModifMdp", "Mot de passe incorrecte");
					doGet(request,response);
				}
			}		
			
			
			
			if(action.equals("Supprimer le compte")) {
				
				String mdp = request.getParameter("mdpSuppr"); //récupération du mot de passe rentré
				
				compte = stub.connexion(mail, mdp); //vérification du mot de passe (on reconnecte l'utilisateur)
				
				if(compte!=null) {
					stub.supprCompte(mail);;
					
					maSession.setAttribute("compte", null);
					
					response.sendRedirect("/PolyBoardGames/acceuil");
				}
				else {
					request.setAttribute("erreurSupprMdp", "Mot de passe incorrecte");
					doGet(request,response);
				}
			}
			
		}
		else {
			//le compte n'est pas valide, on déconnecte l'utilisateur et on redirige à la page d'acceuil
			compte=null;
			maSession.setAttribute("compte", compte);
			response.sendRedirect("/PBG/acceuil");
		}
		
		
		
	}

}
