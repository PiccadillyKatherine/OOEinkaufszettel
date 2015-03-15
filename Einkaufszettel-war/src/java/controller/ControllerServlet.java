/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.SessionBeanLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Produkt;
import javax.ejb.EJB;
import model.Menge;
import model.Person;
import model.Warenkorb;

/**
 *
 * @author Marco
 */
public class ControllerServlet extends HttpServlet {
   @EJB
    private SessionBeanLocal SessionBean;
    private Person user;
    DecimalFormat f = new DecimalFormat("#0.00"); 
    Warenkorb keinwarenkorb = new Warenkorb();
    Person ausgeloggt = new Person("notloggedin", "lol", keinwarenkorb);
    String keyword;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            
            
            // Startseite am Anfang anzeigen
           if (request.getParameter("step")==null) {
                // Login auf leer setzen
                
                user = ausgeloggt;
                request.setAttribute("loginstatus", user.getName());
                
                request.setAttribute("includepage", "/start.jsp");
                request.getRequestDispatcher("menu.jsp").forward(request, response);
            }
            String currentStep = request.getParameter("step");
            
            // Sucheseite
            if (currentStep.equals("search")) {
                // Username auslesen für menu.jsp
                request.setAttribute("loginstatus", user.getName());
                
                request.setAttribute("includepage", "/search.jsp");
                request.getRequestDispatcher("menu.jsp").forward(request, response);
            }
            
            // Startseite
            if (currentStep.equals("start")) {
                // Username auslesen für menu.jsp
                request.setAttribute("loginstatus", user.getName());
                
                request.setAttribute("includepage", "/start.jsp");
                request.getRequestDispatcher("menu.jsp").forward(request, response);
            }
            
            // Warenkorb 
            if (currentStep.equals("cart")) {
                // Username auslesen für menu.jsp
                request.setAttribute("loginstatus", user.getName());
                List<Menge> productlist = SessionBean.warenkorbAnzeigen(user);
                request.setAttribute("preise", SessionBean.calculatePrices(user));
                request.setAttribute("result", productlist); 
                request.setAttribute("includepage", "/warenkorb.jsp");
                request.getRequestDispatcher("menu.jsp").forward(request, response);
            }
            
            // Login
            if (currentStep.equals("login")) {
                // Username auslesen für menu.jsp
                request.setAttribute("loginstatus", user.getName());
                
                request.setAttribute("includepage", "/login.jsp");
                request.getRequestDispatcher("menu.jsp").forward(request, response);
            }
            
            // Logout
            if (currentStep.equals("logout")) {
                
                user = ausgeloggt;
                // Username auslesen für menu.jsp
                request.setAttribute("loginstatus", user.getName());
                
                request.setAttribute("includepage", "/start.jsp");
                request.getRequestDispatcher("menu.jsp").forward(request, response);
            }
            
            // Login Button gedrückt
            if (currentStep.equals("loginstart")) {
                
                String name = request.getParameter("name");
                String password = request.getParameter("passwort");
                user = SessionBean.login(name, password);
                request.setAttribute("loginstatus", user.getName());
                if (user.equals(ausgeloggt)){
                request.setAttribute("includepage", "/wartung.jsp");
                request.getRequestDispatcher("menu.jsp").forward(request, response);
                }else{
                request.setAttribute("includepage", "/willkommen.jsp");
                request.getRequestDispatcher("menu.jsp").forward(request, response);
                }
            }
            // Produktsuche
            if (currentStep.equals("startsearchproduct")) {
                // Username auslesen für menu.jsp
                request.setAttribute("loginstatus", user.getName());
                
                keyword = request.getParameter("txtkeyword");
                List<Produkt> productlist = SessionBean.sucheProdukt(keyword);
                if(productlist.size()!=0){
                    request.setAttribute("result", productlist); 
                    request.setAttribute("includepage", "/Suchergebnis.jsp");
                    request.setAttribute("keyword", keyword);
                    request.getRequestDispatcher("menu.jsp").forward(request, response);
                }
                else{
                    request.setAttribute("keyword", keyword);
                    request.setAttribute("includepage", "/keineergebnisse.jsp");
                    request.getRequestDispatcher("menu.jsp").forward(request, response);
                }
            }     
            
            
            
            //Kategoriesuche
            if (currentStep.equals("startsearchcategory")) {
                // Username auslesen für menu.jsp
                request.setAttribute("loginstatus", user.getName());
                
                String keyword = request.getParameter("txtkeyword");
                List<Produkt> productlist = SessionBean.sucheKategorie(keyword);
                if(productlist.size()!=0){
                    request.setAttribute("result", productlist); 
                    request.setAttribute("includepage", "/Suchergebnis.jsp");
                    request.setAttribute("keyword", keyword);
                    request.getRequestDispatcher("menu.jsp").forward(request, response);
                }
                else{
                    request.setAttribute("includepage", "/keineergebnisse.jsp");
                    request.getRequestDispatcher("menu.jsp").forward(request, response);
                }
            } 
            
            
            // Registrierseite  aufrufen (von Loginseite aus)
            if (currentStep.equals("registerstart")) {
                // Username auslesen für menu.jsp
                request.setAttribute("loginstatus", user.getName());
                
                request.setAttribute("includepage", "/registrieren.jsp");
                request.getRequestDispatcher("menu.jsp").forward(request, response);
            }
            
            // Registrierbutton gedrückt
            if (currentStep.equals("registrierversuch")) {
               
                
                if (!"".equals(request.getParameter("passwort")) && 
                        request.getParameter("passwort").equals(request.getParameter("passwort2")))
                {
                    
                    user = SessionBean.createPerson(request.getParameter("name"),
                                                      request.getParameter("passwort"));    
                    
                    if (user.getName().equals("ausgeloggt")){
                        request.setAttribute("status","User schon vorhanden!");
                        request.setAttribute("includepage","/registrieren.jsp");
                        request.getRequestDispatcher("menu.jsp").forward(request, response);       
                    }
                    else {
                        request.setAttribute("loginstatus", user.getName());
                        request.setAttribute("includepage", "/registrierenerfolg.jsp");
                        request.getRequestDispatcher("menu.jsp").forward(request, response);
                    }
                    
                   }else{
                   request.setAttribute("status","Passwort darf nicht leer sein / Passwort muss = Wiederholung sein!");
                    request.setAttribute("includepage","/registrieren.jsp");
                    request.getRequestDispatcher("menu.jsp").forward(request, response);      
                   }
            }
            
             // Item hinzufügen
            if (currentStep.equals("addItem")) {
                // Username auslesen für menu.jsp
                request.setAttribute("loginstatus", user.getName());
                
              
                int menge = Integer.parseInt(request.getParameter("menge"));
                long produktid = Long.parseLong(request.getParameter("produktid"));
                SessionBean.addItem(menge, produktid, user);
                
                
               
                List<Produkt> productlist = SessionBean.sucheProdukt(keyword);
                
                request.setAttribute("result", productlist); 
                request.setAttribute("includepage", "/Suchergebnis.jsp");
                request.setAttribute("keyword", keyword);
                request.getRequestDispatcher("menu.jsp").forward(request, response);
                
            }
            // Menge ändern
             if (currentStep.equals("changeAmount")) {
                // Username auslesen für menu.jsp
                request.setAttribute("loginstatus", user.getName());
                
              
                int menge = Integer.parseInt(request.getParameter("menge"));
                long produktid = Long.parseLong(request.getParameter("produktid"));
                SessionBean.addItem(menge, produktid, user);
                
                
                List<Menge> productlist = SessionBean.warenkorbAnzeigen(user);
                request.setAttribute("preise", SessionBean.calculatePrices(user));
                request.setAttribute("result", productlist); 
                request.setAttribute("includepage", "/warenkorb.jsp");
                request.getRequestDispatcher("menu.jsp").forward(request, response);
                
            }
            
            // Item löschen
            if (currentStep.equals("deleteitem")) {
                // Username auslesen für menu.jsp
                request.setAttribute("loginstatus", user.getName());
                
                Long mengenid = Long.parseLong(request.getParameter("mengenid"));
                
                SessionBean.deleteItem(mengenid, user);
                List<Menge> productlist = SessionBean.warenkorbAnzeigen(user);
                
                request.setAttribute("preise", SessionBean.calculatePrices(user));
                request.setAttribute("result", productlist); 
                request.setAttribute("includepage", "/warenkorb.jsp");
                request.getRequestDispatcher("menu.jsp").forward(request, response);
            }
        }
    
        
             
        finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
