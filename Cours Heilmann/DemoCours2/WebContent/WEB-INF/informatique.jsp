<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title</title>
</head>
<body>
<%@ include file="menu.jsp" %>

Bonjour
<%
String nom = (String)request.getAttribute("nom");
String prenom = (String)request.getAttribute("prenom");

out.println(nom+ " " + prenom);
%> et bienvenue dans le cours de 
<h1>Cours d'informatique</h1>


<br/><br/>
<p>
<!--  Utilisation de Expression Language (EL) -->
Bonjour ${nom} ${prenom}

${t[0]}
${t[1]}
${t[2]}
${t[3]}
</p>

<p>
${!empty nom ? nom : "la valeur du nom est vide"}
</p>
</body>
</html>