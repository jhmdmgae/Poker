package services;

import beans.Utilisateur;

public class Test {
	public static void main(String []args){
		UtilisateurServices as = new UtilisateurServices();
		/*Utilisateur u = new Utilisateur();
		u.setIdentifiant("toto");
		u.setPassword("OK");
		u.setNom("robert");
		u.setPrenom("Michel");
		u.setAdresse("nantes");
		u.setVille("nantes");
		u.setCodepostal("44000");
		u.setPays("FR");
		
		as.create(u);*/
		
		System.out.println(as.find("marie"));
		
		
	}
}
