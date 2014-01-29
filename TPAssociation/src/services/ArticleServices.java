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

	public ArticleServices(EntityManagerFactory emf, EntityManager em) {
		emf = Persistence.createEntityManagerFactory("Assoc_EMN");
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

	public List<Article> listAll() {
		Query req = em.createQuery("Select a from ARTICLES a");
		List<Article> listArticle = req.getResultList();
		return listArticle;
	}

	public void close() {
		em.close();
		emf.close();
	}

}
