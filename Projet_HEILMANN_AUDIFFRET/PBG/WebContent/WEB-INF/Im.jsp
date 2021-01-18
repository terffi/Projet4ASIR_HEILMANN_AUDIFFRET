<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>    
<%@ page import="java.util.Date" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">   
<html>
<head>
<meta charset="ISO-8859-1">
<title>Envoi de fichier</title>
 <script src="https://code.jquery.com/jquery-3.5.1.js"></script>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
  <script src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>
  <script src="https://cdn.datatables.net/1.10.22/js/dataTables.bootstrap.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	

</head>
<body>

<!--	<form action="ControllerImage" method="post" enctype="multipart/form-data">
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
	</form>  -->
	
	
	<form action="ControllerEvent" method="post" enctype="multipart/form-data">
	<fieldset>
		<legend>Créer un event</legend>
		<div class="form-group">
  			<label for="id">Identifiant de l'event :</label>
  			<input type="text" class="form-control" id="id" name="id">
  			<c:out value="${erreurIdentifiant}"></c:out>
		</div>
		<div class="form-group">
 			 <label for="nom">Nom de l'event :</label>
  			 <input type="text" class="form-control" id="nom" name="nom">
  			 <c:out value="${erreurNom}"></c:out>
		</div>
		<div class="form-group">
  			 <label for="description">Description de l'event :</label>
             <textarea class="form-control" id="description" name="description" rows="3"></textarea>
             <c:out value="${erreurDescription}"></c:out>
        </div>
		<div class="form-group">
             <label for="date">Date de l'event : (à mettre sous la forme AAAA-MM-JJ) </label>
  			 <input type="text" class="form-control" id="date" name="date">
  			 <c:out value="${erreurDate}"></c:out>
		</div>
		<div class="form-group">
             <label for="fichier">Emplacement du Fichier : </label>
  			 <input type="file" class="form-control" id="fichier" name="fichier" value="<c:out value="${fichier.nom}"/>">
  			 <c:out value="${erreurNonImage}"></c:out>
  			 <c:out value="${erreurTailleFichier}"></c:out>
  			 <c:out value="${erreurFichierManquant}"></c:out>
		</div>
       	<div>
        	<input type="submit" name="action" value="Envoyer" class="btn btn-info"/>
        </div>
	</fieldset>
	</form>
	
	
	<form action="ControllerEvent" method="post" enctype="multipart/form-data">
	<fieldset>
		<legend>Modifier l'image d'un event</legend>
		<div class="form-group">
  			<label for="id">Identifiant de l'event :</label>
  			<input type="text" class="form-control" id="idModif1" name="idModif1">
  			<c:out value="${erreurIdentifiant}"></c:out>
		</div>
		<div class="form-group">
             <label for="fichier">Emplacement du Fichier : </label>
  			 <input type="file" class="form-control" id="fichierModif" name="fichierModif" value="<c:out value="${fichier.nom}"/>">
  			 <c:out value="${erreurNonImage}"></c:out>
  			 <c:out value="${erreurTailleFichier}"></c:out>
  			 <c:out value="${erreurFichierManquant}"></c:out>
		</div>
       	<div>
        	<input type="submit" name="action" value="ModifierImage" class="btn btn-info"/>
        </div>
	</fieldset>
	</form>
	
	
	<form action="ControllerEvent" method="post">
	<fieldset>
		<legend>Modifier le nom, la description et la date d'un event</legend>
		<div class="form-group">
  			<label for="id">Identifiant de l'event :</label>
  			<input type="text" class="form-control" id="idModif2" name="idModif2">
  			<c:out value="${erreurIdentifiant}"></c:out>
		</div>
		<div class="form-group">
 			 <label for="nom">Nom de l'event :</label>
  			 <input type="text" class="form-control" id="nomModif2" name="nomModif2">
  			 <c:out value="${erreurNom}"></c:out>
		</div>
		<div class="form-group">
  			 <label for="description">Description de l'event :</label>
             <textarea class="form-control" id="descriptionModif2" name="descriptionModif2" rows="3"></textarea>
             <c:out value="${erreurDescription}"></c:out>
        </div>
		<div class="form-group">
             <label for="date">Date de l'event : (à mettre sous la forme AAAA-MM-JJ) </label>
  			 <input type="text" class="form-control" id="dateModif2" name="dateModif2">
  			 <c:out value="${erreurDate}"></c:out>
		</div>
		<div>
        	<input type="submit" name="action" value="ModifierNomDescriptionDate" class="btn btn-info"/>
        </div>
	</fieldset>
	</form>
	
	
	<form action="ControllerEvent" method="post">
	<fieldset>
		<legend>Supprimer un event</legend>
		<div class="form-group">
  			<label for="id">Identifiant de l'event :</label>
  			<input type="text" class="form-control" id="idSupp" name="idSupp">
  			<c:out value="${erreurIdentifiant}"></c:out>
		</div>
       	<div>
        	<input type="submit" name="action" value="Supprimer" class="btn btn-danger"/>
        </div>
	</fieldset>
	</form>
	
	<form action="ControllerEvent" method="post">
	<fieldset>
		<legend>Recherche d'event</legend>
		<div class="form-group">
  			<label for="nom">Nom de l'event :</label>
  			<input type="text" class="form-control" id="recherche" name="recherche">
		</div>
       	<div>
        	<input type="submit" name="action" value="Rechercher" class="btn btn-info"/>
        </div>
	</fieldset>
	</form>
	
	
	
	
	<div class="container">

	<table class="table" id="list">
	
	<thead>
	      <tr>
	      	<th>identifiant</th>
	        <th>nom</th>
	        <th>image</th>
	        <th>description</th>
	        <th>date</th>
	      </tr>
	</thead>
	
	<tbody>
	
	<c:forEach items="${list}" var="event">
	
	<tr>
	<td><c:out value="${event.id}"></c:out></td>
	<td><c:out value="${event.nom}"></c:out></td>
	<td><img src="data:image/jpg;base64,${event.base64Image}" width="200" height="200"></td>
	<td><c:out value="${event.description}"></c:out></td>
	<td><c:out value="${event.date}"></c:out></td>
	
	
	</tr>
	
	</c:forEach>
	
	</tbody>
	
	</table>
		
	<script type="text/javascript">
	$(document).ready(function() {
	    $('#list').DataTable();
	} );
	</script>
	
	</div>
	
	<br/><br/><br/>
	
	
	
	
	
	<!-- 
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
			
			<input type="submit" name="action" value="ModifierNomDescriptionDate" class="sansLabel" />
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
	</c:forEach> -->
	

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.22/js/dataTables.bootstrap.min.js"></script>

</body>
</html>