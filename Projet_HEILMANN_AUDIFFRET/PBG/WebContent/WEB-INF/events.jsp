<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
			<li class="active">events</li>
		</ol>

	<!-- widget date et heure -->
	<a href="https://time.is/Nancy" id="time_is_link" rel="nofollow" style="font-size:36px">Heure à Nancy :</a>
	<span id="Nancy_z71f" style="font-size:36px"></span>
	<script src="//widget.time.is/fr.js"></script>
	<script>
	time_is_widget.init({Nancy_z71f:{template:"TIME<br>DATE", date_format:"dayname dnum monthname year, week"}});
	</script>


	<br/><br/><br/>


<!-- Moteur de recherche -->	
<div class="page-header">
<h1>Events</h1>
</div>
	
	<!-- Mot à rechercher -->
	<form action="events" method="post">
		<div class="form-group">
  			<label for="rechercheEventInscription">Recherche :</label>
  			<input type="text" class="form-control" id="rechercheEventInscription" name="rechercheEventInscription" value="${sessionScope.rechercheEventInscription}">
		</div>
		<input type="hidden" name="action" value="Rechercher"/>
	</form>
	
	<!-- Resultats de la recherche -->
	<div class="table-responsive">
    <table class="table table-hover text-center table-vcenter" id="listeRecherche">
	
	<thead>
	      <tr>
	        <th class="text-center">nom</th>
	        <th class="text-center">image</th>
	        <th class="text-center">description</th>
	        <th class="text-center">date</th>
	        <th class="text-center">s'inscrire</th>
	      </tr>
	</thead>
	
	<tbody>
	
	<c:forEach items="${resultatRecherche}" var="eventRecherche">
	
	<tr>
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
	<td>
	
	<!-- Bouton d'inscription à un event -->
	
	<!-- verification que l'utilisateur n'est pas déjà inscrit à l'évènement -->
	<c:set var="inscrit" value="false" />
	<c:forEach var="item" items="${ListEventInscrit}">
  		<c:if test="${item.id == eventRecherche.id}">
    		<c:set var="inscrit" value="true" />
  		</c:if>
	</c:forEach>
	
	<c:choose>
		<c:when test="${inscrit}">
			<input type="submit" value="Vous êtes déjà inscrit!" class="btn btn-info disabled"/>
		</c:when>
		<c:otherwise>
			<form action=events method="post">
				<input type="hidden" class="form-control" id="idEvent" name="idEvent" value="${eventRecherche.id}">
				<input type="submit" name="action" value="S'inscrire" class="btn btn-info"/>
			</form>
		</c:otherwise>
	</c:choose>
	</td>
	
	
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
	

<c:choose>
<c:when test="${sessionScope.compte != null }">

<br/><br/><br/>

<!-- Affichage des events auxquels l'utilisateur est déjà inscrit -->
<div class="page-header">
<h1>Events auxquels vous êtes inscrit</h1>
</div>

<div class="table-responsive">

<table class="table table-hover text-center table-vcenter" id="listeInsc">

	<thead>
	      <tr>
	        <th class="text-center">nom</th>
	        <th class="text-center">image</th>
	        <th class="text-center">description</th>
	        <th class="text-center">date</th>
	        <th class="text-center">se désinscrire</th>
	      </tr>
	</thead>
	
	<tbody>
	
	<c:forEach items="${ListEventInscrit}" var="eventInsc">
	
	<tr>
	<td><c:out value="${eventInsc.nom}"></c:out></td>
	<td>
	<c:choose>
		<c:when test="${eventInsc.base64Image!=null}">
			<img src="data:image/jpg;base64,${eventInsc.base64Image}"height="200">
		</c:when>
	</c:choose>
	</td>
	<td><c:out value="${eventInsc.description}"></c:out></td>
	<td><c:out value="${eventInsc.date.getDay()}/${eventInsc.date.getMonth()}/${eventInsc.date.getYear()}"></c:out></td>
	<td>
	
	<!-- Bouton de désinscription à un event -->
	
	<form action=events method="post">
		<input type="hidden" class="form-control" id="idEvent" name="idEvent" value="${eventInsc.id}">
		<input type="submit" name="action" value="Se désinscrire" class="btn btn-info"/>
	</form>

	</td>
	
	
	</tr>
	
	</c:forEach>
	
	</tbody>
	
	</table>
		
	<script type="text/javascript">
	$(document).ready(function() {
	    $('#listeInsc').DataTable();
	} );
	</script>
	
	</div>

</c:when>
</c:choose>
	
	
</div>
<!-- container -->


<div class="BandeauInferieur"></div> 	


<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.22/js/dataTables.bootstrap.min.js"></script>

</body>
</html>