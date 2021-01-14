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

<!-- Social links. @TODO: replace by link/instructions in template -->
	<section id="social">
		<div class="container">
			<div class="wrapper clearfix">
				<!-- AddThis Button BEGIN -->
				<div class="addthis_toolbox addthis_default_style">
				<a class="addthis_button_facebook_like" fb:like:layout="button_count"></a>
				<a class="addthis_button_tweet"></a>
				<a class="addthis_button_linkedin_counter"></a>
				<a class="addthis_button_google_plusone" g:plusone:size="medium"></a>
				</div>
				<!-- AddThis Button END -->
			</div>
		</div>
	</section>
	<!-- /social links -->


	<footer id="footer" class="top-space">

		<div class="footer1">
			<div class="container">
				<div class="row">
					
					<div class="col-md-4 widget">
						<h3 class="widget-title">Contact</h3>
						<div class="widget-body">
							<p>Nos mails<br>
								<a href="mailto:#">sam.audiffret@etu.polytech-nancy.univ-lorraine.fr</a><br>
								<a href="mailto:#">brice.heilmann@etu.polytech-nancy.univ-lorraine.fr</a><br>
								<a href="mailto:#">thomas.berviller1@etu.univ-lorraine.fr</a><br>
						</div>
					</div>

					<div class="col-md-2 widget">
						<h3 class="widget-title">Follow us</h3>
						<div class="widget-body">
							<p class="follow-me-icons">
								<a href="https://www.facebook.com/PolyBoardGames"><i class="fa fa-facebook fa-2"></i></a>
								
								<!-- Nous n'avons pas ces réseaux sociaux pour le moment
								
								<a href=""><i class="fa fa-twitter fa-2"></i></a>
								<a href=""><i class="fa fa-dribbble fa-2"></i></a>
								<a href=""><i class="fa fa-github fa-2"></i></a>
								-->
								
							</p>	
						</div>
					</div>

					<div class="col-md-6 widget">
						<h3 class="widget-title">Description</h3>
						<div class="widget-body">
							<p>
							Site du PolyBoard Games, le club jeux de société de l'école d'ingénieur de Polytech Nancy, 
							regardez les évènements que l'on organise au cours de l'année scolaire et inscrivez-vous si vous 
							voulez participer!
							</p>
						</div>
					</div>

				</div> <!-- /row of widgets -->
			</div>
		</div>

		<div class="footer2">
			<div class="container">
				<div class="row">
					
					<div class="col-md-6 widget">
						<div class="widget-body">
							<p class="simplenav">
								<a href="acceuil">Accueil</a> | 
								<a href="acceuil">About</a> |
								<a href="contact">Contact</a> |
								
								<c:choose>
									<c:when test="${sessionScope.compte!=null}">
										<b><a href="sign_out">Sign Out</a></b>
									</c:when>
									<c:otherwise>
										<b><a href="sign-up">Sign up</a></b>
									</c:otherwise>
								</c:choose>
								
							</p>
						</div>
					</div>

					<div class="col-md-6 widget">
						<div class="widget-body">
							<p class="text-right">
								Copyright &copy; 2014, Your name.Progressus Designed by <a href="http://gettemplate.com/" rel="designer">gettemplate</a> 
							</p>
						</div>
					</div>

				</div> <!-- /row of widgets -->
			</div>
		</div>

	</footer>	
	
	
			<!-- JavaScript libs are placed at the end of the document so the pages load faster -->
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
	<script src="assets/js/headroom.min.js"></script>
	<script src="assets/js/jQuery.headroom.min.js"></script>
	<script src="assets/js/template.js"></script>	
	
</body>
</html>