<%-- 
    Document   : login
    Created on : 17/03/2015, 10:23:06
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

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="pt-br" lang="pt-br" >
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Login</title>
            <link type="text/css" rel="stylesheet" href="css/estilo.css" />
            <style type="text/css" >
                form {width: 220px;position: absolute;left: 50%;margin-left: -110px;top: 50%;margin-top: -100px;}
                form a {display: block;font-size:130%;}
            </style>
    </head>
    <body>
        <div class="geral">
            <div class="conteudo">
                <form action="LoginServlet" method="post">
                    <div>
                        <div class="campo">
                            <label for='login'>Login:</label>
                            <input type='text' id='login' name='login' value=''/>
                        </div>
                        <div class="campo">
                            <label for='senha'>Senha:</label>
                            <input type='password' id='senha' name='senha' value='' />
                        </div>
                        <div class="campo">
                            <a href="recuperar_senha.html">Esqueceu a senha?</a>
                        </div>
                        <div class="campo">
                            <a href="cadastro.jsp">Cadastre-se?</a>
                        </div>
                        <div class="campo">
                            <input type='submit' value="Entrar"/>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
