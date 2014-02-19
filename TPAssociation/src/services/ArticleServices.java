package services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import beans.Article;

import java.util.List;

public class ArticleServices {
	private EntityManagerFactory emf;
	private EntityManager em;

	public ArticleServices() {
		emf = Persistence.createEntityManagerFactory("TPAssociation");
		em = emf.createEntityManager();
	}

	public Article find(String code) {
		return em.find(Article.class, code);
	}

	public void create(Article u) {
		em.getTransaction().begin();
		em.persist(u);
		em.getTransaction().commit();
	}

	public void update(Article a){
		em.merge(a);
	}
	
	
	public List<Article> listAll(){
		Query query = em.createQuery("select a from Article a");
		List<Article> list =  query.getResultList();
		
		return list;
		
	}
	
	
	

	public void close() {
		em.close();
		emf.close();
	}

}
