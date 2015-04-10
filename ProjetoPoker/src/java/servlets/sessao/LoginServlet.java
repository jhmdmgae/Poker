package servlets.sessao;

/**
 *
 * @author João Henrique 2
 */
import DAO.DAO;
import controller.UsuarioJpaController;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
   
    DAO dao = new DAO();
    UsuarioJpaController userC;

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        dao.getEntityManager();
        
        userC = new UsuarioJpaController(dao.getEmf());
        
        String usuario = request.getParameter("login");
        String senha = request.getParameter("senha");

        if (userC.getLogin(usuario, senha).getIdUsuario() != null) {
            HttpSession session = request.getSession();
            session.setAttribute("userPDSW", "João Entrou");

            session.setMaxInactiveInterval(30 * 60);
            Cookie userName = new Cookie("userPDSW", usuario);
            userName.setMaxAge(30 * 60);
            response.addCookie(userName);
            response.sendRedirect("index.jsp");
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>Usuário ou senha estão errados.</font>");
            rd.include(request, response);
        }

    }

}
