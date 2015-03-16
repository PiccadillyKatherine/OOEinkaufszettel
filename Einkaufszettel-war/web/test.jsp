<%-- 
    Document   : test.jsp
    Created on : 13.03.2015, 12:29:43
    Author     : Marco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <article class="includecontent hintergrund">
        <h1>JSP Variablentest:</h1>
        <c:forEach var="item" items="${result}">
                Bezeichnung: ${item.getP().getBezeichnung()}
                <br>Kategorie: ${item.getP().getKategorie()}
                <br>Menge: ${item.getAnzahl()}
                ${item.getP().getId()}
        </c:forEach> 
        </article>
    </body>
</html>
