<%-- 
    Document   : Suchergebnis
    Created on : 02.03.2015, 14:08:43
    Author     : Marco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Suchergebnisse</title>
    </head>
    <body>
        <article class="includecontent hintergrund">
            <h1>Suchergebnisse für "${keyword}"</h1>
        </article>

        <jsp:include page="${'/search.jsp'}"></jsp:include> 
        
            <c:forEach var="result" items="${result}">
                <article class="includecontent hintergrund">
                    <p>
                        Bezeichnung: ${result.getBezeichnung()}
                        <br>Kategorie: ${result.getKategorie()}
                        <br>Preis: ${result.getPreis()}
                        <br>Anbieter: ${result.getAnbieter()}
                    </p>
                   
                    <label class="switch">
                        <input type="checkbox" class="switch-input" />
                        <span class="switch-label"></span>
                        <span class="switch-handle"></span>
                    </label>

                    <c:if test="${result.getAnbieter() eq 'Rewe'}">
                        <img class="icon" src="Bilder/rewe.png">
                    </c:if>  

                    <c:if test="${result.getAnbieter() eq 'Apple'}">
                        <img class="icon" src="Bilder/apple.png">
                    </c:if> 

                </article>   
            </c:forEach> 
      </body>
</html>
