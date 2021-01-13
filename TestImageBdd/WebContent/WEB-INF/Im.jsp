<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>    
<%@ page import="java.util.Date" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">   
<html>
<head>
<meta charset="ISO-8859-1">
<title>Envoi de fichier</title>
 <link type="text/css" rel="stylesheet" href="<c:url value="/inc/form.css"/>" />
</head>
<body>

	<form action="ControllerImage" method="post" enctype="multipart/form-data">
		<fieldset>
			<legend>Créer un event</legend>
			
			<label for="id">Identifiant de l'event :</label>
			<input type="text" id="id" name="id" value=""/>
			<br/>
		
			<label for="nom">Nom de l'event :</label>
			<input type="text" id="nom" name="nom" value=""/>
			<br/>
			
			<label for="description">Description de l'event :</label>
			<input type="text" id="description" name="description" value=""/>
			<br/>
			
			<label for="date">Date de l'event :</label>
			<input type="text" id="date" name="date" value=""/>
			<br/>
			
			<label for="fichier">Emplacement du fichier image :<span class="requis">*</span></label>
			<input type="file" name ="fichier" id="fichier" value="<c:out value="${fichier.nom}"/>"/>
			<br/>
			
			<input type="submit" name="action" value="Envoyer" class="sansLabel" />
			<br />
			
		</fieldset>
	</form>
	
	
	<form action="ControllerImage" method="post" enctype="multipart/form-data">
		<fieldset>
			<legend>Modifier l'image d'un event</legend>
			
			<label for="id">Identifiant de l'event :</label>
			<input type="text" id="idModif1" name="idModif1" value=""/>
			<br/>
			
			<label for="fichier">Emplacement du fichier image :<span class="requis">*</span></label>
			<input type="file" name ="fichierModif" id="fichierModif" value="<c:out value="${fichier.nom}"/>"/>
			<span class="erreur">${erreurs['fichier']}</span>
			<br/>
			
			<input type="submit" name="action" value="ModifierImage" class="sansLabel" />
			<br />
			
		</fieldset>
	</form>
	
	
		<form action="ControllerImage" method="post" enctype="multipart/form-data">
		<fieldset>
			<legend>Modifier le nom et la description d'un event</legend>
			
			<label for="id">Identifiant de l'event :</label>
			<input type="text" id="idModif2" name="idModif2" value=""/>
			<br/>
		
			<label for="nom">Nom de l'event :</label>
			<input type="text" id="nomModif2" name="nomModif2" value=""/>
			<br/>
			
			<label for="description">Description de l'event :</label>
			<input type="text" id="descriptionModif" name="descriptionModif2" value=""/>
			<br/>
			
			<input type="submit" name="action" value="ModifierNomDescription" class="sansLabel" />
			<br />
			
		
		</fieldset>
	
	</form>
	
	<form action="ControllerImage" method="post" enctype="multipart/form-data">
		<h2>Supprimer un event</h2>
		<label for="idSupp">Identifiant de l'event</label>
		<input type="text" id="idSupp" name="idSupp" value=""/>
		<input type="submit" name="action" value="Supprimer" class="sansLabel"/>
	
	</form>
	
	<form action="ControllerImage" method="post" enctype="multipart/form-data">
		<h2>Recherche d'event</h2>
		<label for="nom">Nom de l'event</label>
		<input type="text" id="recherche" name="recherche" value=""/>
		<input type="submit" name="action" value="Rechercher" class="sansLabel"/>
	
	</form>

	
	<c:if test="${erreurNom}">
		<h1>Le nom du fichier est invalide, veuillez recommencer</h1>
	</c:if>
	<c:if test="${erreurNonImage}">
		<h1>Vous n'avez pas rentrer une image, veuillez recommencer</h1>
	</c:if>
	<c:if test="${erreurTailleFichier}">
		<h1>le fichier est trop volumineux, limite = 1 Mo</h1>
	</c:if>
	<c:if test="${erreurDescription}">
		<h1>la description est invalide</h1>
	</c:if>
	<c:if test="${erreurIdentifiant}">
		<h1>l'identifiant est invalide</h1>
	</c:if>
	<c:if test="${erreurFichierManquant}">
		<h1>le fichier est manquant</h1>
	</c:if>
	<c:if test="${erreurDate}">
		<h1>La date est invalide</h1>
	</c:if>
	

	<c:forEach items="${list}"  var = "image" >
		<li><c:out value ="${image.id }"></c:out></li>
		<li><c:out value ="${image.nom }"></c:out></li>
		<li><img src="data:image/jpg;base64,${image.base64Image}" width="200" height="200"></li>
		<li><c:out value ="${image.description }"></c:out></li>
		<li><c:out value ="${image.date }"></c:out></li>
	</c:forEach>
	



</body>
</html>