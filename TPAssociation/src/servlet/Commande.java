package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.ArticleServices;
import services.CommandeServices;


/**
 * Servlet implementation class Commande
 */
@WebServlet("/Commande")
public class Commande extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Commande() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		beans.Commande c = new beans.Commande();
		c.setCode(request.getParameter("article"));
		c.setIdentifiant((String) request.getSession().getAttribute("login"));
		CommandeServices cs = new CommandeServices();
		cs.create(c);
		cs.close();
		
		getServletContext().getRequestDispatcher("/Action?page=commandes").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
