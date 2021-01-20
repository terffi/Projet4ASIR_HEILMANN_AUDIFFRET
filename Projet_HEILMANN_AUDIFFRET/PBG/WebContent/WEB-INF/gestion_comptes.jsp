<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<meta charset="utf-8">
	<meta name="viewport"    content="width=device-width, initial-scale=1.0">
	
<title>Gestion des comptes</title>

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
			<li class="active">Gestion_comptes</li>
		</ol>


<!-- Moteur de recherche -->
<div class="page-header">
<h1>Moteur de recherche</h1>
</div>

<!-- Mot à rechercher -->
<form action="gestion_comptes" method="post">
<div class="form-group">
  <label for="rechercheCompte">Recherche :</label>
  <input type="text" class="form-control" id="rechercheCompte" name="rechercheCompte" value="${sessionScope.rechercheCompte}">
</div>
<input type="hidden" name="action" value="Rechercher"/>
</form>

<!-- Resultats de la recherche -->
<div class="container">

<table class="table" id="listeRecherche">

<thead>
      <tr>
      	<th>admin</th>
        <th>nom</th>
        <th>prénom</th>
        <th>mail</th>
        <th>mot de passe</th>
      </tr>
</thead>

<tbody>

<c:forEach items="${resultatRecherche}" var="compteRecherche">

<tr>
<td><c:out value="${compteRecherche.admin}"></c:out></td>
<td><c:out value="${compteRecherche.nom}"></c:out></td>
<td><c:out value="${compteRecherche.prenom}"></c:out></td>
<td><c:out value="${compteRecherche.mail}"></c:out></td>
<td><c:out value="${compteRecherche.mdp}"></c:out></td>
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


<!-- Affichage de tout les comptes -->
<div class="page-header">
<h1>Liste de tout les comptes</h1>
</div>

<div class="container">

<table class="table" id="listeCompte">

<thead>
      <tr>
        <th>admin</th>
        <th>nom</th>
        <th>prénom</th>
        <th>mail</th>
        <th>mot de passe</th>
      </tr>
</thead>

<tbody>

<c:forEach items="${resultat}" var="compte">

<tr>
<td><c:out value="${compte.admin}"></c:out></td>
<td><c:out value="${compte.nom}"></c:out></td>
<td><c:out value="${compte.prenom}"></c:out></td>
<td><c:out value="${compte.mail}"></c:out></td>
<td><c:out value="${compte.mdp}"></c:out></td>
</tr>

</c:forEach>

</tbody>

</table>

<script type="text/javascript">
$(document).ready(function() {
    $('#listeCompte').DataTable();
} );
</script>

</div>


<br/><br/><br/>


<!-- Ajout d'un compte -->	
<div class="page-header">
<h1>Ajouter un compte</h1>
</div>

<form action="gestion_comptes" method="post">
<div class="form-group">
  <label for="nom">Nom:</label>
  <input type="text" class="form-control" id="nom" name="nom">
</div>
<div class="form-group">
  <label for="prenom">Prénom:</label>
  <input type="text" class="form-control" id="prenom" name="prenom">
</div>
<div class="form-group">
  <label for="mail">Mail:</label>
  <input type="text" class="form-control" id="mail" name="mail">
</div>
<div class="form-group">
  <label for="mdp">Mot de passe:</label>
  <input type="text" class="form-control" id="mdp" name="mdp">
</div>

<input type="submit" name="action" value="Ajout" class="btn btn-info"/>

</form>


<br/><br/><br/>


<!-- Modification d'un compte -->
<div class="page-header">
<h1>Modifier un compte</h1>
</div>

<form action="gestion_comptes" method="post">
<div class="form-group">
  <label for="mailModif">Mail:</label>
  <input type="text" class="form-control" id="mailModif" name="mailModif">
</div>
<div class="form-group">
  <label for="nomModif">Nom:</label>
  <input type="text" class="form-control" id="nomModif" name="nomModif">
</div>
<div class="form-group">
  <label for="prenomModif">Prénom:</label>
  <input type="text" class="form-control" id="prenomModif" name="prenomModif">
</div>

<input type="submit" name="action" value="Modifier" class="btn btn-info"/>

</form>


<br/><br/><br/>


<!-- Modification du mot de passe d'un compte -->
<div class="page-header">
<h1>Modifier un mot de passe</h1>
</div>

<form action="gestion_comptes" method="post">
<div class="form-group">
  <label for="mailModifMdp">Mail:</label>
  <input type="text" class="form-control" id="mailModifMdp" name="mailModifMdp">
</div>
<div class="form-group">
  <label for="mdpModif">Mot de passe:</label>
  <input type="text" class="form-control" id="mdpModif" name="mdpModif">
</div>

<input type="submit" name="action" value="Modifier le mot de passe" class="btn btn-info"/>

</form>


<br/><br/><br/>


<!-- Suppression d'un compte -->
<div class="page-header">
<h1>Supprimer un compte</h1>
</div>

<form action="gestion_comptes" method="post">
<div class="form-group">
  <label for="mailSuppr">Mail:</label>
  <input type="text" class="form-control" id="mailSuppr" name="mailSuppr">
</div>

<input type="submit" name="action" value="Supprimer" class="btn btn-info"/>

</form>



<br/><br/><br/>


<!-- Gestion des Administrateurs -->
<div class="page-header">
<h1>Gérer les administrateurs</h1>
</div>

<form action="gestion_comptes" method="post">
<div class="form-group">
  <label for="mailAdmin">Mail:</label>
  <input type="text" class="form-control" id="mailAdmin" name="mailAdmin">
</div>

<input type="submit" name="action" value="Ajouter en tant qu'administrateur" class="btn btn-info"/>

<input type="submit" name="action" value="Retirer en tant qu'administrateur" class="btn btn-danger"/>

</form>


<br/><br/><br/>


<!-- Affichage de tout les admins -->
<div class="page-header">
<h1>Liste de tout les Administrateurs</h1>
</div>

<div class="container">

<table class="table" id="listeAdmins">

<thead>
      <tr>
        <th>nom</th>
        <th>prénom</th>
        <th>mail</th>
      </tr>
</thead>

<tbody>

<c:forEach items="${resultatAdmins}" var="admin">

<tr>
<td><c:out value="${admin.nom}"></c:out></td>
<td><c:out value="${admin.prenom}"></c:out></td>
<td><c:out value="${admin.mail}"></c:out></td>
</tr>

</c:forEach>

</tbody>

</table>

<script type="text/javascript">
$(document).ready(function() {
    $('#listeAdmins').DataTable();
} );
</script>

</div>


<br/><br/><br/>


<!-- Reset de la liste des comptes -->
<div class="page-header">
<h1>Reset de la liste des comptes</h1>
</div>

<form action="gestion_comptes" method="post">

<input type="submit" name="action" value="Reset" class="btn btn-danger"/>

</form>

</div>
<!-- container -->


<div class="BandeauInferieur"></div> 


	<!-- JavaScript libs are placed at the end of the document so the pages load faster -->
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
	<script src="assets/js/headroom.min.js"></script>
	<script src="assets/js/jQuery.headroom.min.js"></script>
	<script src="assets/js/template.js"></script>
	
 	<script src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>
  	<script src="https://cdn.datatables.net/1.10.22/js/dataTables.bootstrap.min.js"></script>

</body>
</html>