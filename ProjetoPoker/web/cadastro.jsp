<%-- 
    Document   : cadastro
    Created on : 19/03/2015, 21:33:29
    Author     : João Henrique 2
--%>

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
                <form method="GET" action="UsuarioBean">
                    <div class="campo">
                        <label for='login'>Login:</label>
                        <input type="login" id="login" name="login" value="" />
                    </div>
                    <div class="campo">
                        <label for='login'>E-mail:</label>
                        <input type="login" id="email" name="email" value="" />
                    </div>
                    <div class="campo">
                        <label for='senha'>Senha:</label>
                        <input type="password" id="senha" name="senha" value="" />
                    </div>
                    <div class="campo">
                        <label for='confirmar'>Confirmar senha:</label>
                        <input type="password" id="confirmar" name="confirmar" value="" />
                    </div>
                    <div class="campo">
                        <label for='nome'>Nome:</label>
                        <input type="text" id="nome" name="nome" value="" />
                    </div>
                    <div class="campo">
                        <label for='nome'>Foto</label>
                       <input type='file' id='foto' name='foto' value='' size="39"/>
                    </div>
                    <input type="submit" value="cadastrar" />           
                </form>
            </div>
        </div>
    </body>
</html>
