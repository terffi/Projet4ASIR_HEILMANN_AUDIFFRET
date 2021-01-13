<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<meta charset="utf-8">
	<meta name="viewport"    content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author"      content="Sergey Pozhilov (GetTemplate.com)">
	
<title>Insert title here</title>

	<script src="https://code.jquery.com/jquery-3.5.1.js"></script>

	<link rel="shortcut icon" href="assets/images/gt_favicon.png">
	
	<link rel="stylesheet" media="screen" href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,700">
	<link rel="stylesheet" href="assets/css/bootstrap.min.css">
	<link rel="stylesheet" href="assets/css/font-awesome.min.css">

	<!-- Custom styles for our template -->
	<link rel="stylesheet" href="assets/css/bootstrap-theme.css" media="screen" >
	<link rel="stylesheet" href="assets/css/main.css">



  <!-- 
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  -->
  
  	<script type="text/javascript">
	$(function(){
		$(".BandeauSuperieur").load("assets/jsp/bandeau_superieur.jsp");
	})
	$(function(){
		$(".BandeauInferieur").load("assets/jsp/bandeau_inferieur.jsp");
	})
	</script>
  
</head>

<div class="BandeauSuperieur"></div> 

<body>

<br/><br/><br/>
<br/>

	
	
<div class="page-header">
<h1>Ajouter/Modifier/Supprimer un compte</h1>
</div>

<form action="GestionComptes" method="post">
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

<input type="submit" name="action" value="Modifier" class="btn btn-info"/>

<input type="submit" name="action" value="Supprimer" class="btn btn-info"/>

</form>

<br/><br/><br/>

<div class="page-header">
<h1>Définir un compte administrateur</h1>
</div>

<form action="GestionComptes" method="post">
<div class="form-group">
  <label for="mailAdmin">Mail:</label>
  <input type="text" class="form-control" id="mailAdmin" name="mailAdmin">
</div>

<input type="submit" name="action" value="Ajouter en tant qu'admin" class="btn btn-info"/>

</form>

<br/><br/><br/>





<div class="page-header">
<h1>Moteur de recherche</h1>
</div>

<form action="GestionComptes" method="post">


<div class="form-group">
  <label for="id">Recherche:</label>
  <input type="text" class="form-control" id="recherche" name="recherche">
</div>
<input type="hidden" name="action" value="Rechercher"/>


</form>



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




<div class="page-header">
<h1>Liste de tous les comptes</h1>
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