package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Utilisateur;
import services.UtilisateurServices;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		
		String pwd = request.getParameter("password");
		
		UtilisateurServices us = new UtilisateurServices();
		Utilisateur u = us.find(request.getParameter("login"));
		if(u!=null && pwd.equals(u.getPassword())){
			HttpSession session = request.getSession();
			session.setAttribute("login", u.getIdentifiant());
			response.sendRedirect(getServletContext().getContextPath()+ "/Action");
		}else{
			request.setAttribute("message", "Utilisateur inconnu");
			getServletContext().getRequestDispatcher("/page/login.jsp").forward(request, response);
		}
			
		
		
			
			
		
	}

}
