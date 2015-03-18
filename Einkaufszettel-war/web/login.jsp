<%-- 
    Document   : login
    Created on : 25.02.2015, 19:10:33
    Author     : Marco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <article class="includecontent hintergrund">
                    <header>
                        <h2>Das ist die Login-Seite</h2>
                    </header>
                    <footer>
                        <p class="articleinfo">Marco hat das geschrieben</p>
                    </footer>
                        <p>
                            Hier kann man sich einloggen
                        </p>
                         <!-- GET-Button Variante -->
                         <form method="post" action="/Einkaufszettel-war/index?step=loginstart">
                        <p>Login: <input type="text" name="name" /></p>
                        <p>Passwort: <input type="password" name="passwort" /></p>
                        <p><input type="submit" value="Login" /></p>
                        </form>
                         
                         <form method="post" action="/Einkaufszettel-war/index?step=registerstart"
                         <p><input type="submit" value="Registrieren" /></p>        
                         </form>    
        </article>
    </body>
</html>
