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
            <p>Hier kann man sich einloggen</p>
            <form method="post" id="login" action="/Einkaufszettel-war/index?step=loginstart">
                <input placeholder="Name eingeben" type="text" name="name" />
                <input placeholder="Passwort eingeben" type="password" name="passwort" />
                <input id="loginbutton" type="submit" value="Login" />
            </form>
            <p>Noch nicht registriert?</p>
            <form method="post" action="/Einkaufszettel-war/index?step=registerstart">
                <input id="loginbutton" type="submit" value="Registrieren" />
            </form>
        </article>
    </body>
</html>
