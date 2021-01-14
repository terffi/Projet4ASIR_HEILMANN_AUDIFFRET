<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
	<link rel="stylesheet" media="screen" href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,700">
	<link rel="stylesheet" href="assets/css/bootstrap.min.css">
	<link rel="stylesheet" href="assets/css/font-awesome.min.css">

	<!-- Custom styles for our template -->
	<link rel="stylesheet" href="assets/css/bootstrap-theme.css" media="screen" >
	<link rel="stylesheet" href="assets/css/main2.css">
</head>

<body>

<!-- Fixed navbar -->
	<div class="navbar navbar-inverse navbar-fixed-top headroom" >
		<div class="container">
			<div class="navbar-header">
				<!-- Button for smallest screens -->
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse"><span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
				<a class="navbar-brand" href="acceuil"><img src="assets/images/logoPBG.png" alt="Progressus HTML5 template"></a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav pull-right">
					<li class="active"><a href="acceuil">Acceuil</a></li>
					<li><a href="about">About</a></li>
					<li><a href="contact">Contact</a></li>
					
					<c:choose>
						<c:when test="${sessionScope.compte.isAdmin()}">
							<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">Interfaces administrateur <b class="caret"></b></a>
								<ul class="dropdown-menu">
									<li><a href="gestion_comptes">Gestion des comptes</a></li>
									<li class="active"><a href="sidebar-right.html">Gestion des évènements</a></li>
								</ul>
							</li>
						</c:when>
					</c:choose>
					
					<c:choose>
						<c:when test="${sessionScope.compte!=null}">
							<li><a href="sign_out">Sign Out</a></li>
							<li><a class="btn" href="mon_compte">MON COMPTE</a></li>
						</c:when>
						<c:otherwise>
							<li><a class="btn" href="sign-in">SIGN IN / SIGN UP</a></li>
						</c:otherwise>
					</c:choose>
					
				</ul>
			</div><!--/.nav-collapse -->
		</div>
	</div> 
	<!-- /.navbar -->
	
	
			<!-- JavaScript libs are placed at the end of the document so the pages load faster -->
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
	<script src="assets/js/headroom.min.js"></script>
	<script src="assets/js/jQuery.headroom.min.js"></script>
	<script src="assets/js/template.js"></script>
	
</body>
</html>