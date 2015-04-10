<%-- 
    Document   : LoginSuccess
    Created on : 16/03/2015, 22:07:36
    Author     : João Henrique 2
--%>
<%

//allow access only if session exists
    String user = (String) session.getAttribute("userPDSW");
    String userName = null;
    String sessionID = null;
    Cookie[] cookies = request.getCookies();

    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("userPDSW")) {
                userName = cookie.getValue();
            }
            if (cookie.getName().equals("JSESSIONID")) {
                sessionID = cookie.getValue();
            }
        }
    }

    System.out.println(user);
    System.out.println(userName);
    System.out.println(sessionID);
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <link type="text/css" rel="stylesheet" href="css/estilo.css" />
    </head>
    <body>
        <div class="geral">
            <div class="cabecalho">
                <div class="logomarca">
                    <a href="index.jsp">
                        <img src="imagens/logo.png" width="420px" height="97px" title="" alt="" border="0"/>
                    </a>
                </div>
                <div class="navegacao-usuario">
                    <% if (session == null) { %>
                    <a>
                        <img src="imagens/usuario/foto-usuario.png" width="40px" height="40px" title="" alt="" border="0"/>
                    </a>
                    <p><a href="login.jsp">Entrar</a></p>
                    <% } else {%>
                    <h2>Oi <%=userName%>.</h2><br />
                    <form action="LogoutServlet" method="post">
                        <input type="submit" value="Sair" >
                    </form>
                    <% }%>
                </div>
            </div>
            <div class="conteudo">
                <p>HOME</p>
            </div>
        </div>
    </body>
</html>
