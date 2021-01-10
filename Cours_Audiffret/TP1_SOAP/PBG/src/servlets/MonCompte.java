package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import EJBs.Compte;
import EJBs.Comptes;

/**
 * Servlet implementation class MonCompte
 */
@WebServlet("/MonCompte")
public class MonCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MonCompte() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/moncompte.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession maSession = request.getSession();
		
		String action = request.getParameter("action");
		
		Comptes comptes = new Comptes();
		
		Compte compte = (Compte) maSession.getAttribute("compte"); //récupération du compte de la session
		String mail = compte.getMail(); //récupération du mail
		
		
		
		if(action.equals("Modifier le compte")) {
			String mdp = request.getParameter("mdp"); //récupération du mot de passe rentré
			
			compte = comptes.connexion(mail, mdp); //vérification du mot de passe (on reconnecte l'utilisateur)
			
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
					
					comptes.modifierUnCompte(compte);
					
					maSession.setAttribute("compte", compte);	
					
					response.sendRedirect("/PBG/MonCompte");
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
			
			compte = comptes.connexion(mail, mdp); //vérification du mot de passe (on reconnecte l'utilisateur)
			
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
				
					compte.setMdp(comptes.modifierMotDePasse(mail, newMdp));
					
					if(compte.getMdp()==null) {
						//erreur lors de l'encryption
						request.setAttribute("erreurMdp", "Une erreur est survenu");
						
						compte = comptes.connexion(mail, mdp); //on récupère l'ancien mot de passe dans la session
						
						doGet(request,response);						
					}
						
					maSession.setAttribute("compte", compte);	
						
					response.sendRedirect("/PBG/MonCompte");
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
			
			compte = comptes.connexion(mail, mdp); //vérification du mot de passe (on reconnecte l'utilisateur)
			
			if(compte!=null) {
				comptes.supprimerUnCompte(mail);
				
				maSession.setAttribute("compte", null);
				
				response.sendRedirect("/PBG/acceuil");
			}
			else {
				request.setAttribute("erreurSupprMdp", "Mot de passe incorrecte");
				doGet(request,response);
			}
		}
		
		
		
	}

}
