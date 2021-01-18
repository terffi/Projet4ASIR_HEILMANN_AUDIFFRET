<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mon Compte</title>

<script src="https://code.jquery.com/jquery-3.5.1.js"></script>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
  <script src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>
  <script src="https://cdn.datatables.net/1.10.22/js/dataTables.bootstrap.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  
  
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
			<li class="active">Mon_compte</li>
		</ol>
	

<!-- Modifications du compte -->
<div class="page-header">
<h1>Informations du compte :</h1>
</div>

<form action="mon_compte" method="post">
<div class="form-group">
  <label for="mail">Mail:</label>
  <c:out value="${sessionScope.compte.mail}"></c:out>
</div>
<div class="form-group">
  <label for="nom">Nom:</label>
  <input type="text" class="form-control" id="nom" name="nom" value="${sessionScope.compte.nom}">
  <c:out value="${erreurNom}"></c:out>
</div>
<div class="form-group">
  <label for="prenom">Prénom:</label>
  <input type="text" class="form-control" id="prenom" name="prenom" value="${sessionScope.compte.prenom}">
  <c:out value="${erreurPrenom}"></c:out>
</div>

<div class="form-group">
  <label for="mdp">Pour effectuer toute modification, veuillez entrer votre mot de passe:</label>
  <input type="password" class="form-control" id="mdp" name="mdp">
  <c:out value="${erreurMdp}"></c:out>
</div>

<div>
<input type="submit" name="action" value="Modifier le compte" class="btn btn-info"/>
</div>

</form>


<br/><br/><br/>


<!-- Modification du mot de passe -->
<div class="page-header">
<h1>Changer de mot de passe :</h1>
</div>

<form action="mon_compte" method="post">
<div class="form-group">
  <label for="modifMdp">Mot de passe actuel :</label>
  <input type="password" class="form-control" id="modifMdp" name="modifMdp">
  <c:out value="${erreurModifMdp}"></c:out>
</div>
<div class="form-group">
  <label for="newMdp">Nouveau mot de passe :</label>
  <input type="password" class="form-control" id="newMdp" name="newMdp">
  <c:out value="${erreurNewMdp}"></c:out>
</div>
<div class="form-group">
  <label for="confirmMdp">Confirmez votre nouveau mot de passe :</label>
  <input type="password" class="form-control" id="confirmMdp" name="confirmMdp">
  <c:out value="${erreurConfirmMdp}"></c:out>
</div>
<div>
<input type="submit" name="action" value="Changer de mot de passe" class="btn btn-info"/>
</div>

</form>


<br/><br/><br/>


<!-- Suppression du compte -->
<div class="page-header">
<h1>Suppression du compte :</h1>
</div>

<form action="mon_compte" method="post">
<div class="form-group">
  <label for="mdpSuppr">Pour supprimer votre compte, veuillez entrer votre mot de passe :</label>
  <input type="password" class="form-control" id="mdpSuppr" name="mdpSuppr">
  <c:out value="${erreurSupprMdp}"></c:out>
</div>
<div>
<input type="submit" name="action" value="Supprimer le compte" class="btn btn-danger"/>
</div>

</form>


</div>
<!-- container -->


<div class="BandeauInferieur"></div> 


</body>
</html>