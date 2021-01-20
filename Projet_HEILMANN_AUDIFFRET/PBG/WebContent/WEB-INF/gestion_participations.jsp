<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<meta charset="utf-8">
	<meta name="viewport"    content="width=device-width, initial-scale=1.0">
	
<title>Gestion des participations</title>

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
			<li class="active">Gestion_participations</li>
		</ol>


<!-- Participants d'un event -->	
<div class="page-header">
<h1>Participants d'un event</h1>
</div>
	
	<!-- ID à rechercher -->
	<form action="gestion_participations" method="post">
		<div class="form-group">
  			<label for="rechercheParticipants">ID de l'event :</label>
  			<input type="text" class="form-control" id="rechercheParticipants" name="rechercheParticipants" value="${rechercheParticipants}">
  			<c:out value="${erreurID}"></c:out>
		</div>
		<input type="hidden" name="action" value="RechercherComptes"/>
	</form>
	
	<!-- Resultats de la recherche -->
	
	<c:choose>
	<c:when test="${eventExist and not empty rechercheParticipants}">
	
	<h2>Participants de <c:out value="${nomEvent}"/></h2>
	
	<c:choose>
	<c:when test="${not empty resultatRechercheParticipants}">
	
	<div class="table-responsive">

	<table class="table table-hover text-center table-vcenter" id="listeRecherche">
	
	<thead>
	      <tr>
        	<th class="text-center">nom</th>
       		<th class="text-center">prénom</th>
        	<th class="text-center">mail</th>
	      </tr>
	</thead>
	
	<tbody>
	
	<c:forEach items="${resultatRechercheParticipants}" var="participants">
	
	<tr>
		<td><c:out value="${participants.nom}"></c:out></td>
		<td><c:out value="${participants.prenom}"></c:out></td>
		<td><c:out value="${participants.mail}"></c:out></td>
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
	
	</c:when>
	
	<c:otherwise>
	Aucun participants
	</c:otherwise>
	
	</c:choose>
	
	</c:when>
	
	<c:when test="${not eventExist and not empty rechercheParticipants}">
	
	Aucun event trouvé
	
	</c:when>
	
	</c:choose>
	
	
	<br/><br/><br/>
	
	
<!-- Participants d'un event -->	
<div class="page-header">
<h1>Participations d'un compte</h1>
</div>
	
	<!-- Mail à rechercher -->
	<form action="gestion_participations" method="post">
		<div class="form-group">
  			<label for="rechercheParticipations">Mail du compte :</label>
  			<input type="text" class="form-control" id="rechercheParticipations" name="rechercheParticipations" value="${rechercheParticipations}">
		</div>
		<input type="hidden" name="action" value="RechercherEvents"/>
	</form>
	
	<!-- Resultats de la recherche -->
	
	<c:choose>
	<c:when test="${compteExist and not empty rechercheParticipations}">
	
	<h2>Participations de <c:out value="${nomCompte}"/></h2>
	
	<c:choose>
	<c:when test="${not empty rechercheParticipations}">
	
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
	
	<c:forEach items="${resultatRechercheParticipations}" var="events">
	
	<tr>
	<td><c:out value="${events.id}"></c:out></td>
	<td><c:out value="${events.nom}"></c:out></td>
	<td>
	<c:choose>
		<c:when test="${events.base64Image!=null}">
			<img src="data:image/jpg;base64,${events.base64Image}" height="200">
		</c:when>
	</c:choose>
	</td>
	<td><c:out value="${events.description}"></c:out></td>
	<td><c:out value="${events.date.getDay()}/${events.date.getMonth()}/${events.date.getYear()}"></c:out></td>
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
	
	</c:when>
	
	<c:otherwise>
	Aucune participations
	</c:otherwise>
	
	</c:choose>
	
	</c:when>
	
	<c:when test="${not compteExist and not empty rechercheParticipations}">
	
	Aucun compte trouvé
	
	</c:when>
	
	</c:choose>
	
	
	<br/><br/><br/>
	
	
<!-- Ajout d'une participation -->	
<div class="page-header">
<h1>Gérer les participations</h1>
</div>

<form action="gestion_participations" method="post">
<div class="form-group">
	<label for="id">Identifiant de l'event :</label>
  	<input type="text" class="form-control" id="id" name="id">
</div>
<div class="form-group">
  <label for="mail">Mail du participant :</label>
  <input type="text" class="form-control" id="mail" name="mail">
</div>

<input type="submit" name="action" value="Ajouter" class="btn btn-info"/>
<input type="submit" name="action" value="Supprimer" class="btn btn-danger"/>
<input type="submit" name="action" value="Supprimer toutes les participations du comptes" class="btn btn-danger"/>
<input type="submit" name="action" value="Supprimer toutes les participations de l'event" class="btn btn-danger"/>

</form>


<br/><br/><br/>


<!-- Reset de la liste des participations -->
<div class="page-header">
<h1>Reset de la liste des participations</h1>
</div>

<form action="gestion_participations" method="post">

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