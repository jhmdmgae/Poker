<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Enregistrez-vous : </h1>
<form method="get" action="#">
	<table>
		<tr>
			<td>Identifiant : </td>
			<td><input type="text" id="identifiant" name="identifiant" required/>*</td>
		</tr>
		<tr>
			<td>Mot de passe : </td>
			<td><input type="password" id="mdp" name="mdp" required/>*</td>
		</tr>
		<tr>
			<td>Mot de passe (confirm) : </td>
			<td><input type="password" id="mdpconfirm" name="mdpconfirm" required/>*</td>
		</tr>
		<tr>
			<td>Nom : </td>
			<td><input type="text" id="nom" name="nom" required/>*</td>
		</tr>
		<tr>
			<td>Prénom : </td>
			<td><input type="text" id="prenom" name="prenom" required/>*</td>
		</tr>
		<tr>
			<td>Adresse : </td>
			<td><input type="text" id="adresse" name="adresse"/></td>
		</tr>
		<tr>
			<td>Code postal : </td>
			<td><input type="text" id="code" name="code"/></td>
		</tr>
		<tr>
			<td>Ville</td>
			<td><input type="text" id="ville" name="ville"/></td>
		</tr>
		<tr>
			<td>Pays</td>
			<td><input type="text" id="pays" name="pays"/></td>
		</tr>
		<tr>
			<td><input type="submit" id="enregistrer" value="Enregistrer"/></td>
		</tr>
	</table>
</form>
<h5>* Champs obligatoire</h5>
</body>
</html>