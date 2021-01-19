<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@ include file="menu.jsp" %>

Bonjour
<%
String nom = (String)request.getAttribute("nom");
String prenom = (String)request.getAttribute("prenom");

out.println(nom+ " " + prenom);
%> et bienvenu dans le cours de 
Cours de Réseau ....

String nom = "Brice";

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