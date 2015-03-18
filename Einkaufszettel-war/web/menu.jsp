<%-- 
    Document   : menu
    Created on : 25.02.2015, 18:21:41
    Author     : Marco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Einkaufszettel.de</title>    
<meta charset="utf-8">
<link rel="stylesheet" href="css/style.css" type="text/css" />
    <link rel="stylesheet" href="css/menu.css" type="text/css" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
    <body class="body">
        <header class="topheader">
            
        </header>
        <header class="mainheader">
            
            <nav class="hintergrund" id="dropdown">
                <ul>
                <!--<li class="active"><a href="#">Willkommen</a></li>-->
                <li><a href="/Einkaufszettel-war/index?step=start">Willkommen</a></li>
                <li><a href="/Einkaufszettel-war/index?step=search">Suche</a></li>
                <li><a href="/Einkaufszettel-war/index?step=cart">Warenkorb</a></li>
                <li><a href="/Einkaufszettel-war/index?step=login">Login</a></li>
                </ul>
            </nav>
        </header>
        
        <div class="maincontent">
            <div class="content">
                    <jsp:include page="${includepage}"></jsp:include> 
            </div>
        </div>
        <div class="sidecontent hintergrund">
            <aside class="top-sidebar">
                <article>
                    <header><h2>Seitenleiste</h2></header>
                    <p>Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod </p>
                </article>
            </aside>
             <aside class="bottom-sidebar">
                <article>
                    <header><h2>Bottom Sidebar</h2></header>
                    <p>Lorem ipsum dolor sit amet, consetetur sadipscing elitring elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo d eirmod </p>
                </article>
            </aside>
        </div>
        <footer class="mainfooter hintergrund">
        <p>Copyright Einkaufszettel.de 2015</p>
        </footer>
    </body>
</html>
