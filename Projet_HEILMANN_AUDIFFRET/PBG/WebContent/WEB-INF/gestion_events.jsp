<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<meta charset="utf-8">
	<meta name="viewport"    content="width=device-width, initial-scale=1.0">
	
<title>Gestion des events</title>

	<script src="https://code.jquery.com/jquery-3.5.1.js"></script>

	<link rel="shortcut icon" href="assets/images/gt_favicon.png">
	
	<link rel="stylesheet" media="screen" href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,700">
	<link rel="stylesheet" href="assets/css/bootstrap.min.css">
	<link rel="stylesheet" href="assets/css/font-awesome.min.css">

	<!-- Custom styles for our template -->
	<link rel="stylesheet" href="assets/css/bootstrap-theme.css" media="screen" >
	<link rel="stylesheet" href="assets/css/main2.css">
	
	<!-- chargement des bandeaux -->  
  	<script type="text/javascript">
	$(function(){
		$(".BandeauSuperieur").load("assets/jsp/bandeau_superieur.jsp");
	})
	$(function(){
		$(".BandeauInferieur").load("assets/jsp/bandeau_inferieur.jsp");
	})
	</script>
  
</head>

<body>


<div class="BandeauSuperieur"></div> 

<header id="head" class="secondary"></header>


<!-- container -->
<div class="container">


		<ol class="breadcrumb">
			<li><a href="accueil">Home</a></li>
			<li class="active">Gestion_events</li>
		</ol>


<!-- Moteur de recherche -->	
<div class="page-header">
<h1>Recherche d'event</h1>
</div>
	
	<!-- Mot à rechercher -->
	<form action="gestion_events" method="post">
		<div class="form-group">
  			<label for="rechercheEvent">Recherche :</label>
  			<input type="text" class="form-control" id="rechercheEvent" name="rechercheEvent" value="${sessionScope.rechercheEvent}">
		</div>
		<input type="hidden" name="action" value="Rechercher"/>
	</form>
	
	<!-- Resultats de la recherche -->
	<div class="table-responsive">

	<table class="table table-hover text-center table-vcenter" id="listeRecherche">
	
	<thead>
	      <tr>
	      	<th class="text-center">identifiant</th>
	        <th class="text-center">nom</th>
	        <th class="text-center">image</th>
	        <th class="text-center">description</th>
	        <th class="text-center">date</th>
	      </tr>
	</thead>
	
	<tbody>
	
	<c:forEach items="${resultatRecherche}" var="eventRecherche">
	
	<tr>
	<td><c:out value="${eventRecherche.id}"></c:out></td>
	<td><c:out value="${eventRecherche.nom}"></c:out></td>
	<td>
	<c:choose>
		<c:when test="${eventRecherche.base64Image!=null}">
			<img src="data:image/jpg;base64,${eventRecherche.base64Image}" height="200">
		</c:when>
	</c:choose>
	</td>
	<td><c:out value="${eventRecherche.description}"></c:out></td>
	<td><c:out value="${eventRecherche.date.getDay()}/${eventRecherche.date.getMonth()}/${eventRecherche.date.getYear()}"></c:out></td>
	
	
	</tr>
	
	</c:forEach>
	
	</tbody>
	
	</table>
		
	<script type="text/javascript">
	$(document).ready(function() {
	    $('#listeRecherche').DataTable();
	} );
	</script>
	
	</div>
	
	
	<br/><br/><br/>
	
	
<!-- Affichage de tout les events -->
<div class="page-header">
<h1>Liste de tous les events</h1>
</div>

<div class="table-responsive">

<table class="table table-hover text-center table-vcenter" id="listeEvent">

<thead>
      <tr>
	      <th class="text-center">identifiant</th>
	      <th class="text-center">nom</th>
	      <th class="text-center">image</th>
	      <th class="text-center">description</th>
	      <th class="text-center">date</th>
      </tr>
</thead>

<tbody>

<c:forEach items="${resultat}" var="event">

<tr>
	<td><c:out value="${event.id}"></c:out></td>
	<td><c:out value="${event.nom}"></c:out></td>
	<td>
	<c:choose>
		<c:when test="${event.base64Image!=null}">
			<img src="data:image/jpg;base64,${event.base64Image}" height="200">
		</c:when>
	</c:choose>
	</td>
	<td><c:out value="${event.description}"></c:out></td>
	<td><c:out value="${event.date.getDay()}/${event.date.getMonth()}/${event.date.getYear()}"></c:out></td>
</tr>

</c:forEach>

</tbody>

</table>

<script type="text/javascript">
$(document).ready(function() {
    $('#listeEvent').DataTable();
} );
</script>

</div>
	
	
	<br/><br/><br/>
	
	
<!-- Ajout d'un event -->	
<div class="page-header">
<h1>Créer un event</h1>
</div>

<form action="gestion_events" method="post" enctype="multipart/form-data">
<div class="form-group">
	<label for="id">Identifiant de l'event :</label>
  	<input type="text" class="form-control" id="id" name="id">
  	<c:out value="${erreurId}"></c:out>
</div>
<div class="form-group">
  <label for="nom">Nom de l'event :</label>
  <input type="text" class="form-control" id="nom" name="nom">
</div>
<div class="form-group">
	<label for="description">Description de l'event :</label>
	<textarea class="form-control" id="description" name="description" rows="3"></textarea>
</div>
<div class="form-group">
	<label for="date">Date de l'event : (à mettre sous la forme AAAA-MM-JJ) </label>
	<input type="text" class="form-control" id="date" name="date">
	<c:out value="${erreurDate}"></c:out>
</div>
<div class="form-group">
	<label for="fichier">Emplacement du Fichier : </label>
	<input type="file" id="fichier" name="fichier" value="<c:out value="${fichier.nom}"/>">
	<c:out value="${erreurFichier}"></c:out>
</div>

<input type="submit" name="action" value="Envoyer" class="btn btn-info"/>

</form>


<br/><br/><br/>


<!-- Modification des détails d'un event -->	
<div class="page-header">
<h1>Modifier le nom, la description et la date d'un event</h1>
</div>
	
	<form action="gestion_events" method="post">
		<div class="form-group">
  			<label for="idModif">Identifiant de l'event :</label>
  			<input type="text" class="form-control" id="idModif" name="idModif">
  			<c:out value="${erreurIdModif}"></c:out>
		</div>
		<div class="form-group">
 			 <label for="nomModif">Nom de l'event :</label>
  			 <input type="text" class="form-control" id="nomModif" name="nomModif">
		</div>
		<div class="form-group">
  			 <label for="descriptionModif">Description de l'event :</label>
             <textarea class="form-control" id="descriptionModif" name="descriptionModif" rows="3"></textarea>
        </div>
		<div class="form-group">
             <label for="dateModif">Date de l'event : (à mettre sous la forme AAAA-MM-JJ) </label>
  			 <input type="text" class="form-control" id="dateModif" name="dateModif">
  			 <c:out value="${erreurDateModif}"></c:out>
		</div>
		
        	<input type="submit" name="action" value="Modifier" class="btn btn-info"/>
        
	</form>
	
	
	<br/><br/><br/>


<!-- Modification de l'image d'un event -->	
<div class="page-header">
<h1>Modifier l'image d'un event</h1>
</div>
	
	<form action="gestion_events" method="post" enctype="multipart/form-data">
		<div class="form-group">
  			<label for="idModifImage">Identifiant de l'event :</label>
  			<input type="text" class="form-control" id="idModifImage" name="idModifImage">
  			<c:out value="${erreurIdModifImage}"></c:out>
		</div>
		<div class="form-group">
             <label for="fichierModifImage">Emplacement du Fichier : </label>
  			 <input type="file" class="form-control" id="fichierModifImage" name="fichierModifImage" value="<c:out value="${fichier.nom}"/>">
  			 <c:out value="${erreurFichierModifImage}"></c:out>
		</div>
       	
        <input type="submit" name="action" value="Modifier l'image" class="btn btn-info"/>
        
	</form>
	
	
	<br/><br/><br/>
	

<!-- Supprimer un event -->	
<div class="page-header">
<h1>Supprimer un event</h1>
</div>
	
	<form action="gestion_events" method="post">
		<div class="form-group">
  			<label for="idSuppr">Identifiant de l'event :</label>
  			<input type="text" class="form-control" id="idSuppr" name="idSuppr">
  			<c:out value="${erreurIdSuppr}"></c:out>
		</div>
       	
        	<input type="submit" name="action" value="Supprimer" class="btn btn-danger"/>
        
	</form>
	
	
<br/><br/><br/>


<!-- Reset de la liste des events -->
<div class="page-header">
<h1>Reset de la liste des events</h1>
</div>

<form action="gestion_events" method="post">

<input type="submit" name="action" value="Reset" class="btn btn-danger"/>

</form>
	

</div>
<!-- container -->


<div class="BandeauInferieur"></div> 	


<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.22/js/dataTables.bootstrap.min.js"></script>

</body>
</html>