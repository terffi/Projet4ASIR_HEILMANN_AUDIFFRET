<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport"    content="width=device-width, initial-scale=1.0">
	
	<title>PolyBoard Games</title>
	
	<script src="https://code.jquery.com/jquery-3.5.1.js"></script>

	<link rel="shortcut icon" href="assets/images/gt_favicon.png">
	
	<link rel="stylesheet" media="screen" href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,700">
	<link rel="stylesheet" href="assets/css/bootstrap.min.css">
	<link rel="stylesheet" href="assets/css/font-awesome.min.css">

	<!-- Custom styles for our template -->
	<link rel="stylesheet" href="assets/css/bootstrap-theme.css" media="screen" >
	<link rel="stylesheet" href="assets/css/main2.css">
	
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

<body class="home">


     <div class="BandeauSuperieur"></div>     


	<!-- Header -->
	<header id="head">
		<div class="container">
			<div class="row">
				<c:choose>
					<c:when test="${sessionScope.compte==null}">
						<h1 class="lead">Bienvenue sur le site du PolyBoard Games !</h1>
					</c:when>
					<c:otherwise>
						<h1 class="lead">Bonjour <c:out value="${compte.prenom}"/> !</h1>
					</c:otherwise>
				</c:choose>
				
				<br/>
				
				<p>
				<c:choose>
					<c:when test="${sessionScope.compte==null}">
						<a class="btn btn-default btn-lg" role="button" href="sign-in">SE CONNECTER</a>
					</c:when>
					<c:otherwise>
						<a class="btn btn-default btn-lg" role="button" href="mon_compte">MON COMPTE</a> 
				
					</c:otherwise>
				</c:choose>
				
				<a class="btn btn-action btn-lg" role="button" href="events">VOIR LES EVENEMENTS</a>
				
				</p>
			</div>
		</div>
	</header>
	<!-- /Header -->


	<!-- Intro -->
	<div class="container text-center">
		<br> <br>
		<h2 class="thin">Inscrivez-vous � des �v�nements pour pouvoir y participer !</h2>
			<p class="text-muted">
			Le PolyBoard Games organise r�guli�rement des �v�nements � Polytech Nancy, venez r�guli�rement sur ce site pour
			ne rien louper !
		</p>
	</div>
	<!-- /Intro-->
	
	
	<div class="jumbotron top-space">
	<!-- container -->
		<div class="container">
	
			<h2 class="text-center top-space">Frequently Asked Questions</h2>
			<br>
	
			<div class="row">
				<div class="col-sm-6">
					<h3>Puis-je m'inscrire � un �v�nement si je n'ai pas de compte?</h3>
					<p>
					Non, mais se cr�er un compte prend � peine quelques secondes! Il suffit de renseigner son nom, pr�nom et 
					adresse mail <a href="sign-up">ici</a>!
					</p>
				</div>
				<div class="col-sm-6">
					<h3>Puis-je participer � un �v�nement si je ne suis pas �tudiant � Polytech Nancy?</h3>
					<p>
						H�las, il faut �tre c�tisant au Cercle des Eleves de Polytech Nancy ou faire partie du personnel de
						Polytech Nancy pour pouvoir participer � la plupart des �v�nements organis�s par le club.
					</p>
				</div>
			</div> <!-- /row -->
	
			<div class="row">
				<div class="col-sm-6">
					<h3>J'ai oubli� mon mot de passe et je ne peux pas le changer</h3>
					<p>
						D�sol�, la fonctionnalit� "j'ai oubli� mon mot de passe" ne fonctionne pas pour le moment, n'h�sitez pas
						� <a href="contact">contacter un piviste</a>, ils peuvent changer votre mot de passe! 
					</p>
				</div>
				<div class="col-sm-6">
					<h3>La page des conditions d'utilisation est vide, est-ce normal?</h3>
					<p>
						Oui, il n'y a pas vraiment de conditions d'utilisations pour le moment, ce site est un projet pour la
						4e ann�e IA2R sp�cialit� SIR, nous n'avons pas eu le temps de r�diger un document de conditions
						d'utilisation...
					</p>
				</div>
			</div> <!-- /row -->
	
		</div>	<!-- /container -->
	</div>


	<div class="BandeauInferieur"></div> 
	
		
		<!-- JavaScript libs are placed at the end of the document so the pages load faster -->
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
	<script src="assets/js/headroom.min.js"></script>
	<script src="assets/js/jQuery.headroom.min.js"></script>
	<script src="assets/js/template.js"></script>

</body>
</html>