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

	<a href="https://time.is/Nancy" id="time_is_link" rel="nofollow" style="font-size:36px">Heure à Nancy :</a>
	<span id="Nancy_z71f" style="font-size:36px"></span>
	<script src="//widget.time.is/fr.js"></script>
	<script>
	time_is_widget.init({Nancy_z71f:{template:"TIME<br>DATE", date_format:"dayname dnum monthname year, week"}});
	</script>


	<form action="ControllerClient" method="post" enctype="multipart/form-data">
	<fieldset>
		<legend>Inscription à un event</legend>
		<div class="form-group">
  			<label for="nom">Identifiant de l'event :</label>
  			<input type="text" class="form-control" id="idInsc" name="idInsc">
  			<c:out value="${erreurIdentifiant}"></c:out>
  			<c:out value="${erreurInsc}"></c:out>
		</div>
       	<div>
        	<input type="submit" name="action" value="S'inscrire" class="btn btn-info"/>
        </div>
	</fieldset>
	</form>


	<form action="ControllerClient" method="post" enctype="multipart/form-data">
	<fieldset>
		<legend>Recherche d'event</legend>
		<div class="form-group">
  			<label for="identifiant">Identifiant de l'event :</label>
  			<input type="text" class="form-control" id="recherche" name="recherche">
  			<c:out value="${erreurIdentifiant}"></c:out>
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
	
	






<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.22/js/dataTables.bootstrap.min.js"></script>

</body>
</html>