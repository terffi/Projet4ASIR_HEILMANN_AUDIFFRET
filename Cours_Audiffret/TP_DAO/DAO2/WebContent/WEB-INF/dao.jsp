<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<h1>Ajouter/Modifier/Supprimer un étudiant</h1>


<form action="dao" method="post">

<p>
<label for="id">Identifiant</label>
<input type="text" id="id" name="id">
</p>

<p>
<label for="nom">Nom</label>
<input type="text" id="nom" name="nom">
</p>

<p>
<label for="prenom">Prenom</label>
<input type="text" id="prenom" name="prenom">
</p>

<input type="submit" name="action" value="AjoutEtudiant"/>

<input type="submit" name="action" value="ModifierEtudiant"/>

<input type="submit" name="action" value="SupprimerEtudiant"/>

</form>

<br/><br/><br/>

<h1>Moteur de recherche</h1>

<form action="dao" method="post">

<p>
<label for="recherche">Recherche</label>
<input type="text" id="recherche" name="recherche">
<input type="submit" name="action" value="RechercherEtudiant"/>
</p>

</form>

<c:forEach items="${resultatRecherche}" var="etudiant">

<ul>

<li><c:out value="${etudiant.numero}"></c:out></li>
<li><c:out value="${etudiant.nom}"></c:out></li>
<li><c:out value="${etudiant.prenom}"></c:out></li>

</ul>

</c:forEach>

<br/><br/><br/>


<h1>Liste de tout les étudiants</h1>

<c:forEach items="${resultat}" var="etud">

<ul>

<li><c:out value="${etud.nom}"></c:out></li>

</ul>

</c:forEach>



</body>
</html>