<%-- 
    Document   : search
    Created on : 25.02.2015, 18:48:35
    Author     : Marco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
        <link rel="stylesheet" href="css/style.css" type="text/css" />
        <link rel="stylesheet" href="css/menu.css" type="text/css" />
    </head>
    <body>
        <article class="includecontent hintergrund">
            <h2>Neue Suche starten</h2>
            <div class="searchrow">
                <div class="searchfield">
                    <form  class="searchfield" method="post" id="search" action="/Einkaufszettel-war/index?step=startsearchproduct">
                        <input type="text" name="txtkeyword" placeholder="Produktname"/>
                       
                    </form>
                </div>
                <div class="searchfield" id="search">
                    <form method="post" action="/Einkaufszettel-war/index?step=startsearchprovider">
                        <input type="text" name="txtkeyword" placeholder="Anbieter"/>
                       
                    </form>
                </div>
                <div class="searchfield" id="search">
                    <form method="post" action="/Einkaufszettel-war/index?step=startsearchcategory">
                        <input type="text" name="txtkeyword" placeholder="Kategorie"/>
                    </form>
                </div>
            </div>
        </article>
    </body>
</html>

