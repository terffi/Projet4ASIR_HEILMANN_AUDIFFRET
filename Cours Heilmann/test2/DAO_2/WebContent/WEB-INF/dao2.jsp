<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<form action="dao" method="post">

<p><label id="id">Identifiant</label>
<input type="text" id="id" name="id"></input>
</p>

<p><label id="nom">Nom</label>
<input type="text" id="nom" name="nom"></input>
</p>

<p><label id="prenom">Prenom</label>
<input type="text" id="prenom" name="prenom"></input>
</p>

<input type="submit" name="action" value="ajouter"/>
<input type="submit" name="action" value="modifier"/>
<input type="submit" name="action" value="supprimer"/>

<p><label id="recherche">Recherche :</label>
<input type="text" id="recherche" name="recherche"></input>
<input type="submit" name="action" value="rechercher"/>
</p>

</form>
<br/>




<c:forEach items="${resultatRecherche}" var="etuds">

<ul>

<li><c:out value="${etuds.identifiant}"></c:out></li>
<li><c:out value="${etuds.nom}"></c:out></li>
<li><c:out value="${etuds.prenom}"></c:out></li>

</ul>

</c:forEach>

<br/><br/><br/>







<br/>


<p>
<c:forEach items="${resultat}" var="etud">

<ul>

<li><c:out value="${etud.nom}"></c:out></li>

</ul>

</c:forEach>


<c:out value="J'arrive à reconnaître la JSTL et prêt à l'utiliser"></c:out>










</body>
</html>