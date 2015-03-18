

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
                        <h2>Das ist die Registrieren-Seite</h2>
                    </header>
                    <footer>
                        <p class="articleinfo">Manuel hat das geschrieben</p>
                    </footer>
            
            <p>${status}</p>
                        <p>
                            Hier kann man sich registrieren
                        </p>
                         <!-- GET-Button Variante -->
                         <form method="post" action="/Einkaufszettel-war/index?step=registrierversuch">
                            <p>Name: <input type="text" name="name" /></p>   
                            <p>Login: <input type="text" name="login" /></p>
                            <p>Passwort: <input type="password" name="passwort" /></p>
                            <p>Wiederholung: <input type="password" name="passwort2" /></p>
                            <p><input type="submit" value="Registrieren" /></p>
                        </form>
                         
                        
        </article>
    </body>
</html>
