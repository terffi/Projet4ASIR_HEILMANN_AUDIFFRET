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
<h1>Ajouter/Modifier/Supprimer un étudiant</h1>
</div>

<form action="dao" method="post">
<div class="form-group">
  <label for="id">Identifiant:</label>
  <input type="text" class="form-control" id="id" name="id">
</div>
<div class="form-group">
  <label for="nom">Nom:</label>
  <input type="text" class="form-control" id="nom" name="nom">
</div>
<div class="form-group">
  <label for="prennom">Prénom:</label>
  <input type="text" class="form-control" id="prenom" name="prenom">
</div>

<input type="submit" name="action" value="AjoutEtudiant" class="btn btn-info"/>

<input type="submit" name="action" value="ModifierEtudiant" class="btn btn-info"/>

<input type="submit" name="action" value="SupprimerEtudiant" class="btn btn-info"/>

</form>

<br/><br/><br/>



<div class="page-header">
<h1>Moteur de recherche</h1>
</div>

<form action="dao" method="post">


<div class="form-group">
  <label for="id">Recherche:</label>
  <input type="text" class="form-control" id="recherche" name="recherche">
</div>
<input type="hidden" name="action" value="RechercherEtudiant"/>


</form>



<div class="container">

<table class="table" id="listeRecherche">

<thead>
      <tr>
        <th>id</th>
        <th>nom</th>
        <th>prénom</th>
      </tr>
</thead>

<tbody>

<c:forEach items="${resultatRecherche}" var="etudiant">

<tr>
<td><c:out value="${etudiant.numero}"></c:out></td>
<td><c:out value="${etudiant.nom}"></c:out></td>
<td><c:out value="${etudiant.prenom}"></c:out></td>
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
<h1>Liste de tous les étudiants</h1>
</div>


<div class="container">

<table class="table" id="listeEtudiant">

<thead>
      <tr>
        <th>id</th>
        <th>nom</th>
        <th>prénom</th>
      </tr>
</thead>

<tbody>

<c:forEach items="${resultat}" var="etud">

<tr>
<td><c:out value="${etud.numero}"></c:out></td>
<td><c:out value="${etud.nom}"></c:out></td>
<td><c:out value="${etud.prenom}"></c:out></td>
</tr>

</c:forEach>

</tbody>

</table>

<script type="text/javascript">
$(document).ready(function() {
    $('#listeEtudiant').DataTable();
} );
</script>

</div>



</body>
</html>