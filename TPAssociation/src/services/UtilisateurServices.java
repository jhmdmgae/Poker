package services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import beans.Utilisateur;


public class UtilisateurServices {
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public UtilisateurServices(){
		emf = Persistence.createEntityManagerFactory("TPAssociation");
		em = emf.createEntityManager();
		
	}
	
	public Utilisateur find(String identifiant){
		return em.find(Utilisateur.class,identifiant);
	}
	
	public void create(Utilisateur u){
		em.getTransaction().begin();
		em.persist(u);
		em.getTransaction().commit();
	}
	
	public void close(){
		em.close();
		emf.close();
	}
}
