<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

 <script src="https://code.jquery.com/jquery-3.5.1.js"></script>

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




	<form action="ControllerClient" method="post" enctype="multipart/form-data">
	<fieldset>
		<legend>Recherche d'event</legend>
		<div class="form-group">
  			<label for="nom">Nom de l'event :</label>
  			<input type="text" class="form-control" id="recherche" name="recherche">
		</div>
       	<div>
        	<input type="submit" name="action" value="Rechercher" class="btn btn-info"/>
        </div>
	</fieldset>
	</form>
	
	
	
	
	<div class="container">

	<table class="table" id="list">
	
	<thead>
	      <tr>
	      	<th>identifiant</th>
	        <th>nom</th>
	        <th>image</th>
	        <th>description</th>
	        <th>date</th>
	      </tr>
	</thead>
	
	<tbody>
	
	<c:forEach items="${list}" var="event">
	
	<tr>
	<td><c:out value="${event.id}"></c:out></td>
	<td><c:out value="${event.nom}"></c:out></td>
	<td><img src="data:image/jpg;base64,${event.base64Image}" width="200" height="200"></td>
	<td><c:out value="${event.description}"></c:out></td>
	<td><c:out value="${event.date}"></c:out></td>
	<td><input type="submit" name="action" value="S'inscrire à ${event.nom }" class="btn btn-info"/></td>
	</tr>
	
	</c:forEach>
	
	</tbody>
	
	</table>
		
	<script type="text/javascript">
	$(document).ready(function() {
	    $('#list').DataTable();
	} );
	</script>
	
	</div>
	
	<br/><br/><br/>
	
	





<!--  
	<form action="ControllerClient" method="post" enctype="multipart/form-data">
			<h2>Recherche d'event</h2>
			<label for="nom">Nom de l'event</label>
			<input type="text" id="recherche" name="recherche" value=""/>
			<input type="submit" name="action" value="Rechercher" class="sansLabel"/>
	</form>
	
	<form action="ControllerClient" method="post" enctype="multipart/form-data">
			<h2>Inscription</h2>
			<label for="identifiant">Identifiant de l'event</label>
			<input type="text" id="inscription" name="inscription" value=""/>
			<input type="submit" name="action" value="S'inscire" class="sansLabel"/>
	</form>


	
	<c:forEach items="${list}"  var = "image" >
		<li><c:out value ="${image.id }"></c:out></li>
		<li><c:out value ="${image.nom }"></c:out></li>
		<li><img src="data:image/jpg;base64,${image.base64Image}" width="200" height="200"></li>
		<li><c:out value ="${image.description }"></c:out></li>
		<input type="submit" name="action" value="S'inscrire à ${image.nom }" class="sansLabel"/>
		
	</c:forEach>

-->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.22/js/dataTables.bootstrap.min.js"></script>

</body>
</html>