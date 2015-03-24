<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrieren</title>
    </head>
    <body>
        <article class="includecontent hintergrund">
            <header>
                <h2>Das ist die Registrieren-Seite</h2>
            </header>
            <p>${status}</p>
            <p>Hier kann man sich registrieren</p>
            <form method="post" id="login" action="/Einkaufszettel-war/index?step=registrierversuch">
                <p> <input placeholder="Name" type="text" name="name" /></p>  
                <p> <input placeholder="Passwort" type="password" name="passwort" /></p>
                <p> <input placeholder="Wiederholung" type="password" name="passwort2" /></p>
                <p><input id="loginbutton" type="submit" value="Registrieren" /></p>
            </form>
        </article>
    </body>
</html>
