package controller;

import beans.SessionBeanLocal;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Produkt;
import javax.ejb.EJB;
import javax.servlet.http.HttpSession;
import model.Menge;
import model.Person;

// Authoren:  Katherine Feil, Marco Preuss und Manuel Speck
public class ControllerServlet extends HttpServlet {

    @EJB
    private SessionBeanLocal sessionBean;

    private Person user;
    Person ausgeloggt = new Person("notloggedin");
    String keyword = "";
    String suchmodus = "";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(20 * 60);
        if (session.isNew()) {
            // neue Session
            session.setAttribute("user", ausgeloggt);
            session.setAttribute("keyword", keyword);
            session.setAttribute("suchmodus", suchmodus);
        }

        response.setContentType("text/html;charset=UTF-8");
        // Erster Step nach aufruf des Servlets, da noch kein step ausgewählt wurde
        if (request.getParameter("step") == null) {

            request.setAttribute("loginstatus", ((Person) session.getAttribute("user")).getName());
            request.setAttribute("includepage", "/start.jsp");
            request.getRequestDispatcher("menu.jsp").forward(request, response);
        }

        //Variable Step aus Request-Objekt auslesen
        String currentStep = request.getParameter("step");

        // Startseite über Navigationsmenü aufrufen
        if (currentStep.equals("start")) {
            request.setAttribute("loginstatus", ((Person) session.getAttribute("user")).getName());
            request.setAttribute("includepage", "/start.jsp");
            request.getRequestDispatcher("menu.jsp").forward(request, response);
        }

        // Sucheseite aufrufen
        if (currentStep.equals("search")) {
            request.setAttribute("loginstatus", ((Person) session.getAttribute("user")).getName());
            request.setAttribute("includepage", "/search.jsp");
            request.getRequestDispatcher("menu.jsp").forward(request, response);
        }

        // Warenkorb aufrufen
        if (currentStep.equals("cart")) {

            request.setAttribute("loginstatus", ((Person) session.getAttribute("user")).getName());

            if (((Person) session.getAttribute("user")).equals(ausgeloggt)) {

                request.setAttribute("includepage", "/bitteeinloggen.jsp");
                request.getRequestDispatcher("menu.jsp").forward(request, response);

            } else {

                System.out.println("test4");

                List<Menge> productlist = sessionBean.warenkorbAnzeigen(((Person) session.getAttribute("user")));
                request.setAttribute("preise", sessionBean.calculatePrices(((Person) session.getAttribute("user"))));
                request.setAttribute("result", productlist);
                request.setAttribute("includepage", "/warenkorb.jsp");
                request.getRequestDispatcher("menu.jsp").forward(request, response);
            }
        }

        // Loginseite aufrufen
        if (currentStep.equals("login")) {
            request.setAttribute("loginstatus", ((Person) session.getAttribute("user")).getName());
            request.setAttribute("includepage", "/login.jsp");
            request.getRequestDispatcher("menu.jsp").forward(request, response);
        }

        // Login Button gedrückt
        if (currentStep.equals("loginstart")) {
            String name = request.getParameter("name");
            String password = request.getParameter("passwort");
            session.setAttribute("user", sessionBean.login(name, password));
            request.setAttribute("loginstatus", ((Person) session.getAttribute("user")).getName());
            if (((Person) session.getAttribute("user")).equals(ausgeloggt)) {
                request.setAttribute("includepage", "/fehler.jsp");
                request.getRequestDispatcher("menu.jsp").forward(request, response);
            } else {
                request.setAttribute("includepage", "/willkommen.jsp");
                request.getRequestDispatcher("menu.jsp").forward(request, response);
            }
        }

        // Logout Button gedrückt => im Anschluss Startseite öffnen 
        if (currentStep.equals("logout")) {
            session.setAttribute("user", ausgeloggt);
            request.setAttribute("loginstatus", ((Person) session.getAttribute("user")).getName());
            request.setAttribute("includepage", "/start.jsp");
            request.getRequestDispatcher("menu.jsp").forward(request, response);
        }

        // Produktsuche ausführen
        if (currentStep.equals("startsearchproduct")) {
            
            session.setAttribute("suchmodus", "produktsuche");
            request.setAttribute("loginstatus", ((Person) session.getAttribute("user")).getName());
            session.setAttribute("keyword", request.getParameter("txtkeyword"));

            List<Produkt> productlist = sessionBean.sucheProdukt(((String) session.getAttribute("keyword")));
            if (!productlist.isEmpty()) {
                request.setAttribute("result", productlist);
                request.setAttribute("includepage", "/Suchergebnis.jsp");
                request.setAttribute("keyword", ((String) session.getAttribute("keyword")));
                request.getRequestDispatcher("menu.jsp").forward(request, response);
            } else {
                request.setAttribute("keyword", ((String) session.getAttribute("keyword")));
                request.setAttribute("includepage", "/keineergebnisse.jsp");
                request.getRequestDispatcher("menu.jsp").forward(request, response);
            }
        }

        //Kategoriesuche ausführen
        if (currentStep.equals("startsearchcategory")) {

            session.setAttribute("suchmodus", "kategoriesuche");
            request.setAttribute("loginstatus", ((Person) session.getAttribute("user")).getName());
            session.setAttribute("keyword", request.getParameter("txtkeyword"));

            List<Produkt> productlist = sessionBean.sucheKategorie(((String) session.getAttribute("keyword")));
            if (!productlist.isEmpty()) {
                request.setAttribute("result", productlist);
                request.setAttribute("includepage", "/Suchergebnis.jsp");
                request.setAttribute("keyword", ((String) session.getAttribute("keyword")));
                request.getRequestDispatcher("menu.jsp").forward(request, response);
            } else {
                request.setAttribute("keyword", ((String) session.getAttribute("keyword")));
                request.setAttribute("includepage", "/keineergebnisse.jsp");
                request.getRequestDispatcher("menu.jsp").forward(request, response);
            }
        }

        // Registrierseite  aufrufen 
        if (currentStep.equals("registerstart")) {
            request.setAttribute("loginstatus", ((Person) session.getAttribute("user")).getName());
            request.setAttribute("includepage", "/registrieren.jsp");
            request.getRequestDispatcher("menu.jsp").forward(request, response);
        }

        // Registrierbutton gedrückt
        if (currentStep.equals("registrierversuch")) {
            String passwort1 = request.getParameter("passwort");
            String passwort2 = request.getParameter("passwort2");
            String name = request.getParameter("name");

            Boolean registerStatus = sessionBean.registerCheck(name, passwort1, passwort2);
            request.setAttribute("loginstatus", ((Person) session.getAttribute("user")).getName());

            if (registerStatus) {

                session.setAttribute("user", sessionBean.createPerson(name, passwort1));
                request.setAttribute("loginstatus", ((Person) session.getAttribute("user")).getName());

                if (((Person) session.getAttribute("user")).getName().equals("notloggedin")) {

                    request.setAttribute("status", "User schon vorhanden!");
                    request.setAttribute("includepage", "/registrieren.jsp");
                    request.getRequestDispatcher("menu.jsp").forward(request, response);

                } else {

                    request.setAttribute("includepage", "/registrierenerfolg.jsp");
                    request.getRequestDispatcher("menu.jsp").forward(request, response);

                }
            } else {

                request.setAttribute("status", "Name und Passwort dürfen nicht leer sein / Passwort muss Wiederholung sein!");
                request.setAttribute("includepage", "/registrieren.jsp");
                request.getRequestDispatcher("menu.jsp").forward(request, response);

            }
        }

        // Artikel dem Warenkorb hinzufügen
        if (currentStep.equals("addItem")) {

            request.setAttribute("loginstatus", ((Person) session.getAttribute("user")).getName());

            System.out.println("test");

            if (((Person) session.getAttribute("user")).getName().equals("notloggedin")) {

                System.out.println("test2");

                request.setAttribute("includepage", "/bitteeinloggen.jsp");
                request.getRequestDispatcher("menu.jsp").forward(request, response);

            } else {
                int menge = Integer.parseInt(request.getParameter("menge"));
                long produktid = Long.parseLong(request.getParameter("produktid"));
                sessionBean.addItem(menge, produktid, ((Person) session.getAttribute("user")));
                List<Produkt> productlist;
                if (session.getAttribute("suchmodus").equals("produktsuche")) {
                    productlist = sessionBean.sucheProdukt(((String) session.getAttribute("keyword")));
                    System.out.println("Produktsuche wurde getriggert");
                } else {
                    productlist = sessionBean.sucheKategorie(((String) session.getAttribute("keyword")));
                    System.out.println("Kategoriesuche wurde getriggert");
                }
                request.setAttribute("result", productlist);
                request.setAttribute("includepage", "/Suchergebnis.jsp");
                request.setAttribute("keyword", ((String) session.getAttribute("keyword")));
                request.getRequestDispatcher("menu.jsp").forward(request, response);
            }
        }

        // Menge im Warenkorb ändern
        if (currentStep.equals("changeAmount")) {
            request.setAttribute("loginstatus", ((Person) session.getAttribute("user")).getName());
            int menge = Integer.parseInt(request.getParameter("menge"));
            long produktid = Long.parseLong(request.getParameter("produktid"));
            sessionBean.addItem(menge, produktid, ((Person) session.getAttribute("user")));
            List<Menge> productlist = sessionBean.warenkorbAnzeigen(((Person) session.getAttribute("user")));
            request.setAttribute("preise", sessionBean.calculatePrices(((Person) session.getAttribute("user"))));
            request.setAttribute("result", productlist);
            request.setAttribute("includepage", "/warenkorb.jsp");
            request.getRequestDispatcher("menu.jsp").forward(request, response);
        }

        // Artikel aus Warenkorb löschen
        if (currentStep.equals("deleteitem")) {
            // Username auslesen für menu.jsp
            request.setAttribute("loginstatus", ((Person) session.getAttribute("user")).getName());
            Long mengenid = Long.parseLong(request.getParameter("mengenid"));
            System.out.println("MengenID vor delete: " + mengenid);
            sessionBean.deleteItem(mengenid, ((Person) session.getAttribute("user")));
            System.out.println("MengenID nach delete: " + mengenid);
            List<Menge> productlist = sessionBean.warenkorbAnzeigen(((Person) session.getAttribute("user")));
            request.setAttribute("preise", sessionBean.calculatePrices(((Person) session.getAttribute("user"))));
            request.setAttribute("result", productlist);
            request.setAttribute("includepage", "/warenkorb.jsp");
            request.getRequestDispatcher("menu.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
