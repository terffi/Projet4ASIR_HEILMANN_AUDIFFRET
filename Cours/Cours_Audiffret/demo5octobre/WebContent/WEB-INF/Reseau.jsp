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

Cours de R�seau ....


<p>
${!empty nom ? nom : "La valeur du param�tre est vide" }
</p>

</body>
</html>