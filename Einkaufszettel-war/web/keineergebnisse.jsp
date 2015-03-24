<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>keine Ergebnisse</title>
    </head>
    <body>
        <article class="includecontent hintergrund">
            <h1>Keine Ergebnisse gefunden für "${keyword}"</h1>
        </article>
        <jsp:include page="${'/search.jsp'}"></jsp:include> 
    </body>
</html>
