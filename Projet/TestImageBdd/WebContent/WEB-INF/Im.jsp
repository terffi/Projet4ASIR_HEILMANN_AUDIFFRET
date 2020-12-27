<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">   
<html>
<head>
<meta charset="ISO-8859-1">
<title>Envoi de fichier</title>
 <link type="text/css" rel="stylesheet" href="<c:url value="/inc/form.css"/>" />
</head>
<body>

	<form action="dao" method="post" enctype="multipart/form-data">
		<fieldset>
			<legend>Envoi de fichier</legend>
		
			<label for="nom">Nom du fichier</label>
			<input type="text" id="nom" name="nom" value=""/>
			<span class="succes"><c:out value="${description}"/></span>
			<br/>
			
			<label for="fichier">Emplacement du fichier <span class="requis">*</span></label>
			<input type="file" name ="fichier" id="fichier" value="<c:out value="${fichier.nom}"/>"/>
			<span class="erreur">${erreurs['fichier']}</span>

			<br/>
			
			<input type="submit" name="action" value="Envoyer" class="sansLabel" />
			<br />
			
		
		</fieldset>
	
	</form>
	
	<form action="dao" method="post" enctype="multipart/form-data">
		<h2>Recherche d'événements</h2>
		<label for="nom">Nom de l'événement</label>
		<input type="text" id="nom" name="nom" value=""/>
		<input type="submit" name="action" value="Rechercher" class="sansLabel"/>
	
	</form>

	<c:out value="${erreur }"></c:out>
	<c:if test="${erreur==1 }">
		<h1>le fichier est trop volumineux, limite = 1 Mo</h1>
	</c:if>
	
	<c:forEach items="${list }"  var = "image" >
		<li><c:out value ="${image.nom }"></c:out></li>
		<li><img src="data:image/jpg;base64,${image.base64Image}" width="200" height="200"></li>
		
	</c:forEach>
	



</body>
</html>