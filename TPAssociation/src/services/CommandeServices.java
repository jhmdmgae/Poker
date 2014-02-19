package services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;



import beans.Article;
import beans.Commande;
import beans.Utilisateur;

public class CommandeServices {
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public CommandeServices(){
		emf = Persistence.createEntityManagerFactory("TPAssociation");
		em = emf.createEntityManager();
		
	}
	
	public void create(Commande c){
		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();
	}
	
	public List<Commande> listAll(String id){
		
		Query query = em.createQuery("select a from Commande a where identifiant=:id");
		query.setParameter("id", id);
		List<Commande> list =  query.getResultList();
		
		return list;
		
	}
	
	public Commande find(String code) {
		return em.find(Commande.class, code);
	}
	
	public void delete(Commande c){
		em.getTransaction().begin();
		em.remove(c);
		em.getTransaction().commit();
	}
	
	public void close(){
		em.close();
		emf.close();
	}
}
