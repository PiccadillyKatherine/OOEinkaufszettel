<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <header class="mainheader hintergrund">

            <nav class=" headerlogo" id="dropdown">
                <ul>
                    <li><a href="/Einkaufszettel-war/index?step=start">Willkommen</a></li>
                    <li><a href="/Einkaufszettel-war/index?step=search">Suche</a></li>
                    <li><a href="/Einkaufszettel-war/index?step=cart">Mein Einkaufszettel</a></li>
                    <c:if test="${loginstatus eq 'notloggedin'}">
                        <li><a href="/Einkaufszettel-war/index?step=login">Login</a></li>
                    </c:if> 
                    <c:if test="${loginstatus ne 'notloggedin'}">
                        <li><a href="/Einkaufszettel-war/index?step=logout">Logout</a></li>
                    </c:if>
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
                    <c:if test="${loginstatus eq 'notloggedin'}">
                        <header>
                            <h2>Noch nicht eingeloggt?</h2>
                            <div class="mittig">
                                <form method="post" action="/Einkaufszettel-war/index?step=login">
                                    <input id="loginbutton" type="submit" value="Zur Loginseite" />
                                </form>
                            </div>
                        </header>
                    </c:if>  
                    <c:if test="${loginstatus ne 'notloggedin'}">
                        <header>
                            <h2>Hallo, ${loginstatus}!</h2>
                            <div class="mittig">
                                <form method="post" action="/Einkaufszettel-war/index?step=cart">
                                    <input id="loginbutton" type="submit" value="Mein Einkaufszettel" />
                                </form>
                            </div>
                        </header>
                    </c:if>  
                </article>
            </aside>
            <aside class="bottom-sidebar">
                <article>
                    <header></header>
                </article>
            </aside>
        </div>
        <footer class="mainfooter hintergrund">
            <p>Copyright Einkaufszettel.de 2015</p>
        </footer>
    </body>
</html>
