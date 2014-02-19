package services;

import beans.Article;
import beans.Commande;
import beans.Utilisateur;

public class Test {
	public static void main(String []args){
		UtilisateurServices as = new UtilisateurServices();
		ArticleServices ar = new ArticleServices();
		CommandeServices cs = new CommandeServices();
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
		for (Article a : ar.listAll()) {
			System.out.println(a.getNom());
		}

		for (Commande a : cs.listAll("GILDAS")) {
			System.out.println(a.getCode());
		}
		
		
	}
}
