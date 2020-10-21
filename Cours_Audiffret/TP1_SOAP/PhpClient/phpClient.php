<?php

$montant = $_GET['mt'];
$clientSoap = new SoapClient("http://localhost:9090/?wsdl");
$param= new stdClass();
$param->mt=$montant;
$resultat = $clientSoap->__soapCall("convertir", array($param));

?>

<html>
<title> php Client </title>

<body>
<form action="phpClient.php" method="get">
<input type="text" name="mt">

<button> Convertir </button>


</form>

<?php 

echo "Le résultat renvoyé par le service est $resultat->return";

?>

</body>







</html>