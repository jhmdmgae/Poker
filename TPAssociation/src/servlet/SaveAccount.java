package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Utilisateur;
import services.UtilisateurServices;

/**
 * Servlet implementation class SaveAccount
 */
@WebServlet("/SaveAccount")
public class SaveAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveAccount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtilisateurServices us = new UtilisateurServices();
		Utilisateur u = new Utilisateur();
		u.setIdentifiant(request.getParameter("identifiant"));
		u.setPassword(request.getParameter("mdp"));
		u.setNom(request.getParameter("nom"));
		u.setPrenom(request.getParameter("prenom"));
		u.setAdresse(request.getParameter("adresse"));
		u.setVille(request.getParameter("ville"));
		u.setCodepostal(request.getParameter("code"));
		u.setPays(request.getParameter("pays"));
		
		us.create(u);
		
		request.setAttribute("message", "Votre compte a bien été créer");
		getServletContext().getRequestDispatcher("/page/login.jsp").forward(request, response);
		
		
	}

}
