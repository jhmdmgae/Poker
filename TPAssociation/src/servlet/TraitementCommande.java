package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Article;
import beans.Commande;
import services.ArticleServices;
import services.CommandeServices;

/**
 * Servlet implementation class TraitementCommande
 */
@WebServlet("/TraitementCommande")
public class TraitementCommande extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TraitementCommande() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CommandeServices cs = new CommandeServices();
		ArticleServices as = new ArticleServices();
		Commande co;
		Article ar;
		
		ArrayList<String> listCode = (ArrayList<String>) request.getAttribute("list");
		for(String code:listCode){
			ar=as.find(code);
			ar.setQuantite(ar.getQuantite()-1);
			as.update(ar);
			co=cs.find(code);
			cs.delete(co);
			
		}
		
		getServletContext().getRequestDispatcher(getServletContext().getContextPath()+"/Action?page=commandes").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
