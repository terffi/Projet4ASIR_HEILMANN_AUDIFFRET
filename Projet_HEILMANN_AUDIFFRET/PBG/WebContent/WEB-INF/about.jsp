<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport"    content="width=device-width, initial-scale=1.0">
	
	<title>About</title>
	
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

<body>


<div class="BandeauSuperieur"></div> 


	<header id="head" class="secondary"></header>

	<!-- container -->
	<div class="container">

		<ol class="breadcrumb">
			<li><a href="accueil">Home</a></li>
			<li class="active">About</li>
		</ol>

		<div class="row">
			
			<!-- Article main content -->
			<article class="col-sm-8 maincontent">
				<header class="page-header">
					<h1 class="page-title">About us</h1>
				</header>
				<h3>Le club</h3>
				<p><img src="assets/images/tarot.png" alt="" class="img-rounded pull-right" width="300" > 
				Le PolyBoard Games (anciennement F'ESSTIN) est le club Jeux de Société de l'école d'ingénieur de Polytech Nancy, étudiants et personnel 
				peuvent venir passer un moment détente autour d'un jeux de société ou d'un jeu de cartes lors des séances du
				mercredi soir.
				</p>
				<p>
				Les séances du PolyBoard Games se déroulent en temps normal tout les Mercredi soir de 18h jusqu'à la fermeture 
				de l'école, dans la  salle C130. Suite à la crise sanitaire, ces séances se font en ligne sur le 
				<a href="https://discord.gg/Nct9SKW">serveur discord</a> du club.
				</p>
				
				<h3>Les Pivistes</h3>
				<p>
				Les étudiants de Polytech Nancy doivent valider un Projet d'Implication à la Vie de l'Ecole (PIVE) et la gestion
				d'un club en fait partie.
				</p>
				<p>
				Cette année, les Pivistes du PolyBoard Games sont Brice Heilmann, Berviller Thomas et Sam Audiffret. Tout 3 en 
				4e année du parcours IA2R spécialisé SIR. Ils font tout ce qui est en leur possible pour offrir des moment détente
				aux étudiants et au personnel via le PolyBoard Games, et la crise sanitaire ne leur fait pas peur.
				</p>
				
				<h3>Les locaux</h3>
				<p>
				En temps normal, le PolyBoard Games occupe la salle C130 de Polytech Nancy. Dû à la crise sanitaire, les séance
				ne peuvent plus se dérouler en présentiel.
				</p>
					
			</article>
			<!-- /Article -->
			
			
			<!-- Sidebar -->
			
			<!--Nous n'avons pas besoin de Sidebar pour le moment
			<aside class="col-sm-4 sidebar sidebar-right">

				<div class="widget">
					<h4>Vacancies</h4>
					<ul class="list-unstyled list-spaces">
						<li><a href="">Lorem ipsum dolor</a><br><span class="small text-muted">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Animi, laborum.</span></li>
						<li><a href="">Totam, libero, quis</a><br><span class="small text-muted">Suscipit veniam debitis sed ipsam quia magnam eveniet perferendis nisi.</span></li>
						<li><a href="">Enim, sequi dignissimos</a><br><span class="small text-muted">Reprehenderit illum quod unde quo vero ab inventore alias veritatis.</span></li>
						<li><a href="">Suscipit, consequatur, aut</a><br><span class="small text-muted">Sed, mollitia earum debitis est itaque esse reiciendis amet cupiditate.</span></li>
						<li><a href="">Nam, illo, veritatis</a><br><span class="small text-muted">Delectus, sapiente illo provident quo aliquam nihil beatae dignissimos itaque.</span></li>
					</ul>
				</div>

			</aside>
			-->
			
			<!-- /Sidebar -->

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