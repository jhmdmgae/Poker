<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Login</h1>
<%if(request.getAttribute("message")!=null) out.print(request.getAttribute("message"));%>
<form method="post" action="<%=getServletContext().getContextPath()%>/Action?task=Login">
	<table>
		<tr>
			<td>Login</td>
			<td><input type="text" id="login" name="login"/></td>
		</tr>
		<tr>
			<td>Password</td>
			<td><input type="password" id="password" name="password"/></td>
		</tr>
		<tr>
			<td><input type="submit" id="Valider" value="Login"/></td>
		</tr>
	</table>
</form>
<h5>Pas encore enregistrez ? <a href="<%=getServletContext().getContextPath()%>/page/createAccount.jsp"> Créer un compte</a> </h5>
</body>
</html>