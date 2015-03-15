<%-- 
    Document   : warenkorb
    Created on : 25.02.2015, 19:07:36
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
        <c:if test="${loginstatus eq 'notloggedin'}">
            <article class="includecontent hintergrund">
                <h2>Bitte loggen Sie sich zuerst ein.</h2>
                <div class="mittig">
                    <form method="post" action="/Einkaufszettel-war/index?step=login">
                        <input id="loginbutton" type="submit" value="Zur Loginseite" />
                    </form>
                </div>
            </article>
        </c:if>
        <c:if test="${loginstatus ne 'notloggedin'}">
             <c:if test="${result.size() ne 0}">
                <article class="includecontent hintergrund">
                            <header>
                                <h2>Das ist der Warenkorb von ${loginstatus}:</h2>
                            </header>
                </article>
                <c:forEach var="item" items="${result}">
                    <article class="includecontent hintergrund">
                        <img class="icon2" src="Bilder/${item.getP().getBezeichnung()}.png">
                        <p>
                            Bezeichnung: ${item.getP().getBezeichnung()}
                            <br>Kategorie: ${item.getP().getKategorie()}
                            <br>Menge: ${item.getAnzahl()}
                            
                        </p>
                        <!-- test -->
                        <div class="mengenfeldcontainer">
                            <form method="post" id="mengenfeld2"  action="/Einkaufszettel-war/index?step=changeAmount&produktid=${item.getP().getId()}">
                                <input placeholder="Menge" type="text" name="menge" />
                                <input id="loginbutton" type="submit" value="Menge ändern" />
                            </form>
                        </div> 
                            <!-- test -->
                        <form method="post"  action="/Einkaufszettel-war/index?step=deleteitem&mengenid=${item.getId()}">
                            <input id="deletebutton" type="submit" value="Löschen" />
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
                              <td id="anbieterspalte"><fmt:formatNumber type="number" maxIntegerDigits="2" value="${item.getP().getPreisrewe() * item.getAnzahl()}" />€</td>
                              <td id="anbieterspalte"><fmt:formatNumber type="number" maxIntegerDigits="2" value="${item.getP().getPreisaldi() * item.getAnzahl()}" />€</td> 
                              <td id="anbieterspalte"><fmt:formatNumber type="number" maxIntegerDigits="2" value="${item.getP().getPreislidl() * item.getAnzahl()}" />€</td>
                              <td id="anbieterspalte"><fmt:formatNumber type="number" maxIntegerDigits="2" value="${item.getP().getPreisreal() * item.getAnzahl()}" />€</td>
                              <td id="anbieterspalte"><fmt:formatNumber type="number" maxIntegerDigits="2" value="${item.getP().getPreisedeka() * item.getAnzahl()}" />€</td>
                            </tr>
                        </table>
                   
                               
                    </article>
                    
                </c:forEach> 
                    <article class="includecontent hintergrund">
                         <h1 id="summeueberschrift">Summe:</h1>
                         <table class="anbietertabe">
                            <tr>
                             <td id="anbieterspalte"><img class="icon" src="Bilder/rewe.png"></td>
                              <td id="anbieterspalte"><img class="icon" src="Bilder/Aldi.png"></td> 
                              <td id="anbieterspalte"><img class="icon" src="Bilder/lidl.png"></td>
                              <td id="anbieterspalte"><img class="icon" src="Bilder/Real.png"></td>
                              <td id="anbieterspalte"><img class="icon" src="Bilder/Edeka.png"></td>
                            </tr>
                            <tr>
                              <td id="anbieterspalte"><h1 id="summeueberschrift"><fmt:formatNumber type="number" maxIntegerDigits="2" value="${preise[0]}"/>€</h1></td>
                              <td id="anbieterspalte"><h1 id="summeueberschrift"><fmt:formatNumber type="number" maxIntegerDigits="2" value="${preise[1]}"/>€</h1></td> 
                              <td id="anbieterspalte"><h1 id="summeueberschrift"><fmt:formatNumber type="number" maxIntegerDigits="2" value="${preise[2]}"/>€</h1></td>
                              <td id="anbieterspalte"><h1 id="summeueberschrift"><fmt:formatNumber type="number" maxIntegerDigits="2" value="${preise[3]}"/>€</h1></td>
                              <td id="anbieterspalte"><h1 id="summeueberschrift"><fmt:formatNumber type="number" maxIntegerDigits="2" value="${preise[4]}"/>€</h1></td>
                            </tr>
                        </table>
                    </article>
            </c:if>
            <c:if test="${result.size() eq 0}">
                <article class="includecontent hintergrund">
                    <h2>Ihr Warenkorb ist noch leer!</h2>
                </article>
            </c:if>
        </c:if>
    </body>
</html>
