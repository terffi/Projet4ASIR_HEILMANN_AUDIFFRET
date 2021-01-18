<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport"    content="width=device-width, initial-scale=1.0">
	
	<title>Sign up</title>
	
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

	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
	<script src="assets/js/html5shiv.js"></script>
	<script src="assets/js/respond.min.js"></script>
	<![endif]-->
</head>

<body>

	<div class="BandeauSuperieur"></div> 

	<header id="head" class="secondary"></header>

	<!-- container -->
	<div class="container">

		<ol class="breadcrumb">
			<li><a href="accueil">Home</a></li>
			<li class="active">Registration</li>
		</ol>

		<div class="row">
			
			<!-- Article main content -->
			<article class="col-xs-12 maincontent">
				<header class="page-header">
					<h1 class="page-title">Registration</h1>
				</header>
				
				<div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
					<div class="panel panel-default">
						<div class="panel-body">
							<h3 class="thin text-center">Register a new account</h3>
							<p class="text-center text-muted">
								Si vous possédez déjà un compte, connectez vous <a href="sign-in">ici</a>. 
							</p>
							<hr>

							<form action="sign-up" method="post">
								<div class="top-margin">
									<label for="prenom">Prénom</label>
									<input type="text" class="form-control" id="prenom" name="prenom" value="${prenom}">
									<c:out value="${erreurPrenom}"></c:out>
								</div>
								<div class="top-margin">
									<label for="nom">Nom</label>
									<input type="text" class="form-control" id="nom" name="nom" value="${nom}">
									<c:out value="${erreurNom}"></c:out>
								</div>
								<div class="top-margin">
									<label for="mail">Mail<span class="text-danger">*</span></label>
									<input type="text" class="form-control" id="mail" name="mail" value="${mail}">
									<c:out value="${erreurMail}"></c:out>
								</div>

								<div class="row top-margin">
									<div class="col-sm-6">
										<label for="mdp">Password <span class="text-danger">*</span></label>
										<input type="password" class="form-control" id="mdp" name="mdp">
									</div>
									<div class="col-sm-6">
										<label for="confirmMdp">Confirm Password <span class="text-danger">*</span></label>
										<input type="password" class="form-control" id="confirmMdp" name="confirmMdp">
									</div>
									<c:out value="${erreurMdp}"></c:out>
								</div>

								<hr>

								<div class="row">
									<div class="col-lg-8">
										<label class="checkbox">
											<input type="checkbox"> 
											I've read the <a href="conditions_utilisation">Terms and Conditions</a>
										</label>
										<p>(pas besoin de cocher cette case pour le moment)</p>                        
									</div>
									<div class="col-lg-4 text-right">
										<button class="btn btn-action" type="submit">Register</button>
									</div>
								</div>
							</form>
						</div>
					</div>

				</div>
				
			</article>
			<!-- /Article -->

		</div>
	</div>	<!-- /container -->
	

<div class="BandeauInferieur"></div> 	

	<!-- JavaScript libs are placed at the end of the document so the pages load faster -->
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
	<script src="assets/js/headroom.min.js"></script>
	<script src="assets/js/jQuery.headroom.min.js"></script>
	<script src="assets/js/template.js"></script>
</body>
</html>