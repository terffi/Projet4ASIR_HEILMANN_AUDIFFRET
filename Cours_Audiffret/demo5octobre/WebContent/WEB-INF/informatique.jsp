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

Site du cours Informatique

<br/><br/>
<p>
<!-- Utilisation de Expression Language (EL) -->
Bonjour ${nom} ${prenom}

${t[0]}
${t[1]}
${t[2]}
${t[3]}


</p>

<p>
${!empty nom ? nom : "La valeur du paramètre est vide" }
</p>



</body>
</html>