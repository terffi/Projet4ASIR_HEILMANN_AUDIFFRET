<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    
<ul>

<li> <a href="/demo5octobre/info">Informatique </a> </li>
<li> <a href="/demo5octobre/maths">Mathématique </a></li>
<li> <a href="/demo5octobre/reseau">Réseau </a></li>

</ul>

Bonjour 
<%
String nom =(String) request.getAttribute("nom");
String prenom =(String) request.getAttribute("prenom");
out.println(nom+" "+prenom);
%> et bienvenue dans le