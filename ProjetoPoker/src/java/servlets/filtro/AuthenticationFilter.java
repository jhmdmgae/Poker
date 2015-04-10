package servlets.filtro;

/**
 *
 * @author João Henrique 2
 */
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter implements Filter {

    private ServletContext context;

    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
        this.context.log("Autenticação inicilizada");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        this.context.log("Requested Resource::" + uri);

        HttpSession session = req.getSession(false);

        String loginURL = req.getContextPath() + "/login.jsp";
        String cadastroURL = req.getContextPath() + "/cadastro.jsp";
        String recuperarSenhaURL = req.getContextPath() + "/recuperar_senha.html";
                
        boolean loggedIn = session != null && session.getAttribute("userPDSW") != null;
        boolean loginRequest = loginURL.equals(req.getRequestURI());
        boolean pagCadastro = cadastroURL.equals(req.getRequestURI());
        boolean recuperarSenha = recuperarSenhaURL.equals(req.getRequestURI());

        if (loggedIn || recuperarSenha || loginRequest || uri.endsWith("LoginServlet") || uri.endsWith(".css") || pagCadastro || uri.endsWith("UsuarioBean")) {
            chain.doFilter(request, response);
        } else {
            this.context.log("Unauthorized access request");
            res.sendRedirect(loginURL);
        }
    }

    public void destroy() {
        //close any resources here
    }

}
