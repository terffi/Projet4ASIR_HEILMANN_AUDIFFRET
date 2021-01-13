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
	
	private static Compte compte;
	private static Comptes comptes = new Comptes();
       
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
		HttpSession maSession = request.getSession();

		if(maSession.getAttribute("compte")!=null) {
			this.getServletContext().getRequestDispatcher("/WEB-INF/moncompte.jsp").forward(request, response);
		}
		else {
			//l'utilisateur n'est pas connect�
			response.sendRedirect("/PBG/acceuil");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession maSession = request.getSession();
		
		compte = (Compte) maSession.getAttribute("compte"); //r�cup�ration du compte de la session
		
		//avant toute action, on v�rifie que l'utilisateur est connect� avec un compte valide (pr�sent dans la BDD)
		if(compte!=null && comptes.compteValide(compte))
		{
			String action = request.getParameter("action");
	
			String mail = compte.getMail(); //r�cup�ration du mail
			
			if(action.equals("Modifier le compte")) {
				String mdp = request.getParameter("mdp"); //r�cup�ration du mot de passe rentr�
				
				compte = comptes.connexion(mail, mdp); //v�rification du mot de passe (on reconnecte l'utilisateur)
				
				if(compte!=null) {
					
					String nom = request.getParameter("nom");
					String prenom = request.getParameter("prenom");
					
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
				String mdp = request.getParameter("modifMdp"); //r�cup�ration du mot de passe rentr�
				
				compte = comptes.connexion(mail, mdp); //v�rification du mot de passe (on reconnecte l'utilisateur)
				
				if(compte!=null) {
					
					String newMdp = request.getParameter("newMdp"); //r�cup�ration du nouveau mot de passe
					String confirmMdp = request.getParameter("confirmMdp");
					
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
					
						compte.setMdp(comptes.modifierMotDePasse(mail, newMdp));
						
						if(compte.getMdp()==null) {
							//erreur lors de l'encryption
							request.setAttribute("erreurMdp", "Une erreur est survenu");
							
							compte = comptes.connexion(mail, mdp); //on r�cup�re l'ancien mot de passe dans la session
							
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
				
				String mdp = request.getParameter("mdpSuppr"); //r�cup�ration du mot de passe rentr�
				
				compte = comptes.connexion(mail, mdp); //v�rification du mot de passe (on reconnecte l'utilisateur)
				
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
		else {
			//le compte n'est pas valide, on d�connecte l'utilisateur et on redirige � la page d'acceuil
			compte=null;
			maSession.setAttribute("compte", compte);
			response.sendRedirect("/PBG/acceuil");
		}
		
		
		
	}

}
