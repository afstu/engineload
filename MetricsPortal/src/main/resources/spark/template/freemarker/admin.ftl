


<html>
    <head>
        <title>HPC Metrics Portal</title>
        
                <link rel="stylesheet" href="/css/bootstrap-3.0.2-min.css">
        <link rel="stylesheet" href="/css/bootstrap-3.0.2-theme.min.css">
        <link rel="stylesheet" href="/css/bootstrap-3.0.2-starter-template.css">
		
<script type="text/javascript" src="/js/jquery.1.10.2.min.js" ></script>
<script type="text/javascript" src="/js/bootstrap.3.0.2.min.js" ></script>

   
        
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
                    <a class="navbar-brand" href="/"><span class="glyphicon glyphicon-home"></span> Metrics Portal Admin</a>
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li class="dropdown">
                        	<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><span class="glyphicon glyphicon-tags"> Rollen</span></a>
                         <ul class="dropdown-menu" role="menu">
            				<li><a href="/rollen/create">Rol Aanmaken</a></li>
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
                        <li ><a href="/graphite"><span class="glyphicon glyphicon-cog"> Graphite</a></li>
                        <li ><a href="/rapporten"><span class="glyphicon glyphicon-stats"> Rapporten</a></li>
                    </ul>
                </div>
            </div>
        </div>
 
        <div class="container">
            <#include "${templateName}">
        </div>

    </body>
</html>

