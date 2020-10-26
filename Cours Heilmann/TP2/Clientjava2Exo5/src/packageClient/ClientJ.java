package packageClient;
import webservice2.ServiceEtudiant;


import java.util.Scanner;

import fr.polytech.ServiceEtudiantService;
import webservice2.Etudiant;
import java.util.ArrayList;
import java.util.List;


public class ClientJ {
	

	public static String afficher_etudiant(String nom, String prenom, String classe, int num_etudiant) {
		return "Etudiant [nom=" + nom + ", prenom=" + prenom + ", classe=" + classe + ", num_etudiant=" + num_etudiant
				+ "]";
	}
	
	public static void main(String[] args) {
		// définition d'un stub ....
		fr.polytech.ServiceEtudiant stub = new ServiceEtudiantService().getServiceEtudiantPort();
		boolean decision = true;
		List<fr.polytech.Etudiant> etudiant1 = stub.creerListe();
		
		while(decision==true) {
			Scanner sc1 = new Scanner(System.in);
			System.out.println("Que voulez vous faire ?");
			System.out.println("1:ajouter un étudiant");
			System.out.println("2:supprimer un étudiant");
			System.out.println("3:rechercher un étudiant");
			System.out.println("4:afficher tout les étudiants");
			System.out.println("5:sortir");
			
			int str1 = sc1.nextInt();
			
			if(str1==1) {
				Scanner scnom = new Scanner(System.in);
				System.out.println("nom :");
				String strnom = sc1.nextLine();
				
				Scanner scprenombis = new Scanner(System.in);
				System.out.println("test :");
				String strnombis = sc1.nextLine();
				
				Scanner scprenom = new Scanner(System.in);
				System.out.println("prenom :");
				String strprenom = sc1.nextLine();
				
				Scanner scclasse = new Scanner(System.in);
				System.out.println("classe :");
				String strclasse = sc1.nextLine();
				
				Scanner scnum = new Scanner(System.in);
				System.out.println("num :");
				int strnum = sc1.nextInt();
				
				stub.ajout(strnom, strprenom, strclasse, strnum, etudiant1);
				String s = afficher_etudiant(strnom, strprenom, strclasse, strnum);
				System.out.println(s);


				}
			
			if(str1==2) {
				Scanner scnum = new Scanner(System.in);
				System.out.println("num :");
				int strnum = sc1.nextInt();
				
				stub.supprimer(strnum, etudiant1);
				
			}
			
			if(str1==3) {
				Scanner scnum = new Scanner(System.in);
				System.out.println("num :");
				int strnum = sc1.nextInt();
				
				fr.polytech.Etudiant e = stub.recherche(strnum, etudiant1);
				e.toString();
			}
			
			
			if(str1==4) {
                for(int i=0; i<etudiant1.size(); i++) {
                	String s = afficher_etudiant(etudiant1(i), strprenom, strclasse, strnum);
    				System.out.println(s);
                }
			}
		}
	}

}
