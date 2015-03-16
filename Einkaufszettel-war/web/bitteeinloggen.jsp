<%-- 
    Document   : bitteeinloggen
    Created on : 15.03.2015, 19:49:05
    Author     : Marco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bitte einloggen</title>
    </head>
    <body>
        <article class="includecontent hintergrund">
                <h2>Bitte loggen Sie sich zuerst ein.</h2>
                <div class="mittig">
                    <form method="post" action="/Einkaufszettel-war/index?step=login">
                        <input id="loginbutton" type="submit" value="Zur Loginseite" />
                    </form>
                </div>
            </article>
    </body>
</html>
