package services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import beans.Article;

public class ArticleServices {
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public ArticleServices(EntityManagerFactory emf, EntityManager em) {
		emf = Persistence.createEntityManagerFactory("Assoc_EMN");
		em = emf.createEntityManager();
	}
	
	public Article find(String code){
		return em.find(Article.class,code);
	}
	
	public void create(Article u){
		em.getTransaction().begin();
		em.persist(u);
		em.getTransaction().commit();
	}
	
	public void close(){
		em.close();
		emf.close();
	}
	
}
