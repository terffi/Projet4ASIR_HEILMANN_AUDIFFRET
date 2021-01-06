<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
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

<div class="page-header">
<h1>Informations du compte :</h1>
</div>

<form action="MonCompte" method="post">
<div class="form-group">
  <label for="mail">Mail:</label>
  <c:out value="${sessionScope.compte.mail}"></c:out>
</div>
<div class="form-group">
  <label for="nom">Nom:</label>
  <input type="text" class="form-control" id="nom" name="nom" value="${sessionScope.compte.nom}">
</div>
<div class="form-group">
  <label for="prenom">Prénom:</label>
  <input type="text" class="form-control" id="prenom" name="prenom" value="${sessionScope.compte.prenom}">
</div>

<div class="form-group">
  <label for="mdp">Pour effectuer toute modification ou supprimer votre compte, veuillez entrer votre mot de passe:</label>
  <input type="password" class="form-control" id="mdp" name="mdp">
  <c:out value="${erreurMdp}"></c:out>
</div>

<div>
<input type="submit" name="action" value="Modifier le compte" class="btn btn-info"/>
</div>

<br/><br/><br/>

<div>
<input type="submit" name="action" value="Supprimer le compte" class="btn btn-danger"/>
</div>

<br/><br/><br/>

</form>

<br/><br/><br/>



</body>
</html>