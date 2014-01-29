<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<table width=60% border="1">
		<tr>
			<td><a href="<%=getServletContext().getContextPath()%>/Action?page=accueil">Accueil</a></td>
			<td><a href="<%=getServletContext().getContextPath()%>/Action?page=articles">Articles</a></td>
			<td><a href="<%=getServletContext().getContextPath()%>/Action?page=commandes">Commandes</a></td>
			<td>Utilisateur : </td>
		</tr>
	</table>
	
	<%if(request.getParameter("page")!=null) {%> <jsp:include
					page='<%= "/part/"+  request.getParameter("page")+".jsp" %>' /> <%}else{ %>
				<jsp:include page="/part/accueil.jsp" /> <%} %>
	
</body>
</html>