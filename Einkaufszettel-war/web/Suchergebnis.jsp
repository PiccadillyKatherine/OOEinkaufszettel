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
            <c:set var="counter" value="0"/>
            <c:forEach var="item" items="${result}">
       
                <article class="includecontent hintergrund">
                    
                    <img class="icon2" src="Bilder/${item.getBezeichnung()}.png">
                    <p>
                        Bezeichnung: ${item.getBezeichnung()}
                        <br>Kategorie: ${item.getKategorie()}
                    </p>
                   
                    <form method="post" id="mengenfeld" action="/Einkaufszettel-war/index?step=addItem&produktid=${item.getId()}">
                        <input placeholder="Menge" type="text" name="menge" />
                        <input id="loginbutton" type="submit" value="Zum Warenkorb hinzufügen" />
                    </form>
                        <table class="anbietertabe">
                      <tr>
                       <td id="anbieterspalte"><img class="icon" src="Bilder/rewe.png"></td>
                        <td id="anbieterspalte"><img class="icon" src="Bilder/Aldi.png"></td> 
                        <td id="anbieterspalte"><img class="icon" src="Bilder/lidl.png"></td>
                        <td id="anbieterspalte"><img class="icon" src="Bilder/Real.png"></td>
                        <td id="anbieterspalte"><img class="icon" src="Bilder/Edeka.png"></td>
                      </tr>
                      <tr>
                        <td id="anbieterspalte">${item.getPreisrewe()}€</td>
                        <td id="anbieterspalte">${item.getPreisaldi()}€</td> 
                        <td id="anbieterspalte">${item.getPreislidl()}€</td>
                        <td id="anbieterspalte">${item.getPreisreal()}€</td>
                        <td id="anbieterspalte">${item.getPreisedeka()}€</td>
                      </tr>
                    </table>
                    
                </article>   
            </c:forEach> 
      </body>
</html>
