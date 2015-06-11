


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
                    <a class="navbar-brand" href="/"><span class="glyphicon glyphicon-home"></span> Metrics Portal</a>
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li ><a href="/gebruikerrapporten/create"><span class="glyphicon glyphicon-stats"> Custom-Rapporten</a></li>
                    </ul>
                </div>
            </div>
        </div>
 
        <div class="container">
            <#include "${templateName}">
        </div>
 
    </body>
</html>

