<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form action="ControllerClient" method="post" enctype="multipart/form-data">
			<h2>Recherche d'event</h2>
			<label for="nom">Nom de l'event</label>
			<input type="text" id="recherche" name="recherche" value=""/>
			<input type="submit" name="action" value="Rechercher" class="sansLabel"/>
	</form>
	
	<form action="ControllerClient" method="post" enctype="multipart/form-data">
			<h2>Inscription</h2>
			<label for="identifiant">Identifiant de l'event</label>
			<input type="text" id="inscription" name="inscription" value=""/>
			<input type="submit" name="action" value="S'inscire" class="sansLabel"/>
	</form>


	
	<c:forEach items="${list}"  var = "image" >
		<li><c:out value ="${image.id }"></c:out></li>
		<li><c:out value ="${image.nom }"></c:out></li>
		<li><img src="data:image/jpg;base64,${image.base64Image}" width="200" height="200"></li>
		<li><c:out value ="${image.description }"></c:out></li>

	</c:forEach>




</body>
</html>