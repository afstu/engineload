<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       <title>HPC Metrics Portal</title>
        
        <link rel="stylesheet" href="<c:url value="/portal/scripts/css/bootstrap-3.0.2-min.css" />" >
        <link rel="stylesheet" href="<c:url value="/portal/scripts/css/bootstrap-3.0.2-theme.min.css" />" >
        <link rel="stylesheet" href="<c:url value="/portal/scripts/css/bootstrap-3.0.2-starter-template.css" />" >
		
		<script type="text/javascript" src="<c:url value="/portal/scripts/js/jquery.1.10.2.min.js" />"></script>
		<script type="text/javascript" src="<c:url value="/portal/scripts/js/bootstrap.3.0.2.min.js" />"></script>

</head>
<body>
    <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href=""><span class="glyphicon glyphicon-home"></span> Metrics Portal</a>
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li class="dropdown">
                        	<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><span class="glyphicon glyphicon-tags"> Rollen</span></a>
                         <ul class="dropdown-menu" role="menu">
            				<li><a href="/rollen/create"/>Rol Aanmaken</a></li>
            				<li><a href="/rollen">List Rollen</a></li>
            			</ul>
            			</li>
                        <li class="dropdown">
                        	<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><span class="glyphicon glyphicon-th-large"> Clusters</span></a>
                        <ul class="dropdown-menu" role="menu">
            				<li><a href="/clusters/create">Cluster Aanmaken</a></li>
            				<li><a href="/clusters">List Clusters</a></li>
            			</ul>
            			</li>
                        <li class="dropdown">
                        	<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><span class="glyphicon glyphicon-user"> Gebruikers</span></a>
                        <ul class="dropdown-menu" role="menu">
            				<li><a href="/gebruikers/create">Gebruiker Aanmaken</a></li>
            				<li><a href="/gebruikers">List Gebruikers</a></li>
            			</ul>
            			</li>
            			<li class="dropdown">
                        	<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><span class="glyphicon glyphicon-stats"> Rapporten</span></a>
                        <ul class="dropdown-menu" role="menu">
            				<li><a href="/rapporten/create">Rapport Aanmaken</a></li>
            				<li><a href="/rapporten">Standaard Rapport</a></li>
            			</ul>
            			</li>
            			
                        <li ><a href="/graphite"><span class="glyphicon glyphicon-cog"> Graphite</span></a></li>
                        
                    </ul>
                </div>
            </div>
        </div>
<div class="container">   