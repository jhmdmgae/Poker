<%@page import="beans.Article"%>
<%@page import="services.ArticleServices"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h1>Catalogues des articles</h1>

<%
	ArticleServices as = new ArticleServices();
	
%>
<table border="1">
<tr>
<td><h4>Code</h4></td>
<td><h4>Nom</h4></td>
<td><h4>Prix</h4></td>
<td><h4>Stock</h4></td>
</tr>
<c:forEach items="<%= as.listAll()%>" var="article" >
<tr>
<td>${article.getCode()}</td>
<td>${article.getNom()}</td>
<td>${article.getPrix()} </td>
<td>${article.getQuantite()}</td>
<td><a href="<%=getServletContext().getContextPath()%>/Commande?article=${article.getCode()}">Commander</a> </td>
</tr>
</c:forEach>
</table>