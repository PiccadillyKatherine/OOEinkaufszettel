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
        
       
             <c:if test="${result.size() ne 0}">
                <article class="includecontent hintergrund">
                            <header>
                                <h2>Das ist der Warenkorb von ${loginstatus}:</h2>
                            </header>
                </article>
                <c:forEach var="item" items="${result}" varStatus="count">
                    <article class="includecontent hintergrund">
                        <img class="icon2" src="Bilder/${item.getP().getBezeichnung()}.png">
                        <p>
                            Bezeichnung: ${item.getP().getBezeichnung()}
                            <br>Kategorie: ${item.getP().getKategorie()}
                            <br>Menge: ${item.getAnzahl()}
                            
                        </p>
                        <!-- test -->
                        <div class="mengenfeldcontainer">
                            <form method="post" class="addItem2" id="addItem${count.count}"  action="/Einkaufszettel-war/index?step=changeAmount&produktid=${item.getP().getId()}">
                                <input placeholder="Menge" type="text" name="menge" id="amount${count.count}"/>
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
                              <td id="anbieterspalte"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${item.getP().getPreisrewe() * item.getAnzahl()}" />€</td>
                              <td id="anbieterspalte"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${item.getP().getPreisaldi() * item.getAnzahl()}" />€</td> 
                              <td id="anbieterspalte"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${item.getP().getPreislidl() * item.getAnzahl()}" />€</td>
                              <td id="anbieterspalte"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${item.getP().getPreisreal() * item.getAnzahl()}" />€</td>
                              <td id="anbieterspalte"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${item.getP().getPreisedeka() * item.getAnzahl()}" />€</td>
                            </tr>
                        </table>
                        <h2 id="error${count.count}" class="error1"></h2>    
                               
                    </article>
                    <script>
                        var addButton${count.count} = document.getElementById("addItem${count.count}");
                        var re = /^[0-9]+$/;
                        addButton${count.count}.onsubmit = function(){
                           if (document.getElementById("amount${count.count}").value == "" || !re.test(document.getElementById("amount${count.count}").value)){  
                             document.getElementById("error${count.count}").innerHTML="Falsche Eingabe!";
                            return false;
                           }
                        }
                    </script>      
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
                              <td id="anbieterspalte"><h1 id="summeueberschrift"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${preise[0]}"/>€</h1></td>
                              <td id="anbieterspalte"><h1 id="summeueberschrift"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${preise[1]}"/>€</h1></td> 
                              <td id="anbieterspalte"><h1 id="summeueberschrift"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${preise[2]}"/>€</h1></td>
                              <td id="anbieterspalte"><h1 id="summeueberschrift"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${preise[3]}"/>€</h1></td>
                              <td id="anbieterspalte"><h1 id="summeueberschrift"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits  ="2" value="${preise[4]}"/>€</h1></td>
                            </tr>
                        </table>
                    </article>
            </c:if>
            <c:if test="${result.size() eq 0}">
                <article class="includecontent hintergrund">
                    <h2>Ihr Warenkorb ist noch leer!</h2>
                </article>
            </c:if>
        
    </body>
</html>
