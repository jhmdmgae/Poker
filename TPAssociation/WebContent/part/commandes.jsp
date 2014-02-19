<%@page import="java.lang.reflect.Array"%>
<%@page import="beans.Article"%>
<%@page import="beans.Commande"%>
<%@page import="services.CommandeServices"%>
<%@page import="services.ArticleServices"%>
<%@page import= "java.util.List"%>
<%@page import= "java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h1>Votre commande</h1>

<%CommandeServices cs = new CommandeServices();%>
<%ArticleServices as = new ArticleServices();%>
<% List<Commande> com = cs.listAll((String)session.getAttribute("login"));%>
<% ArrayList<String> list = new ArrayList<String>(); %>	
<c:set var="commandes"  value='<%=com%>'/>	
<c:if test="${commandes.isEmpty() }"><%= "Vous n'avez pas encore commandé"%></c:if>	
<c:if test="${!commandes.isEmpty() }">
<% System.out.println(getServletContext().getContextPath()+"/Action?task=TraitementCommande"); %>
<form method="get" action="<%=getServletContext().getContextPath()%>/Action?task=TraitementCommande">
<table border="1">
<tr>
<td>Code</td>
<td>Nom</td>
<td>Prix</td>
</tr>
<% int index =0; %>
<c:forEach items="${ commandes}" var="commande">

<tr>
<td>${commande.getCode()}</td>
<%Article a = as.find(com.get(index).getCode()); %>
<td><%=a.getNom() %></td>
<td><%=a.getPrix() %></td>
<% list.add(a.getCode());
   request.setAttribute("list", list);
%>
</tr>

<% index ++; %>
</c:forEach> 
<tr>
	<td><input type="submit" value="Valider"/></td>
</tr>

</table>
</form>

</c:if>