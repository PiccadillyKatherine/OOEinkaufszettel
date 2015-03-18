/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.SessionBeanLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Produkt;
import javax.ejb.EJB;
import model.Person;

/**
 *
 * @author Marco
 */
public class ControllerServlet extends HttpServlet {
   @EJB
    private SessionBeanLocal SessionBean;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
           if (request.getParameter("step")==null) {
                 request.setAttribute("includepage", "/start.jsp");
                 request.getRequestDispatcher("menu.jsp").forward(request, response);
            }
            String currentStep = request.getParameter("step");
            
            if (currentStep.equals("search")) {
                request.setAttribute("includepage", "/search.jsp");
                request.getRequestDispatcher("menu.jsp").forward(request, response);
            }
            if (currentStep.equals("start")) {
                request.setAttribute("includepage", "/start.jsp");
                request.getRequestDispatcher("menu.jsp").forward(request, response);
            }
            if (currentStep.equals("cart")) {
                request.setAttribute("includepage", "/warenkorb.jsp");
                request.getRequestDispatcher("menu.jsp").forward(request, response);
            }
            if (currentStep.equals("login")) {
                request.setAttribute("includepage", "/login.jsp");
                request.getRequestDispatcher("menu.jsp").forward(request, response);
            }
            
            if (currentStep.equals("loginstart")) {
                request.setAttribute("includepage", "/wartung.jsp");
                request.getRequestDispatcher("menu.jsp").forward(request, response);
            }
            
            // Produktsuche
            if (currentStep.equals("startsearchproduct")) {
                String keyword = request.getParameter("txtkeyword");
                List<Produkt> productlist = SessionBean.sucheProdukt(keyword);
                if(productlist.size()!=0){
                    request.setAttribute("anbieter", "rewe");
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
            
            //Anbietersuche
            if (currentStep.equals("startsearchprovider")) {
                String keyword = request.getParameter("txtkeyword");
                List<Produkt> productlist = SessionBean.sucheAnbieter(keyword);
                
                
                if(productlist.size()!=0){
                    request.setAttribute("anbieter", "rewe");
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
                String keyword = request.getParameter("txtkeyword");
                List<Produkt> productlist = SessionBean.sucheKategorie(keyword);
                if(productlist.size()!=0){
                    request.setAttribute("anbieter", "rewe");
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
            
            
            
            if (currentStep.equals("registerstart")) {
                request.setAttribute("includepage", "/registrieren.jsp");
                request.getRequestDispatcher("menu.jsp").forward(request, response);
            }
            
            if (currentStep.equals("registrierversuch")) {
               
                
            if (request.getParameter("passwort") != null &&
                request.getParameter("passwort2") != null &&
                request.getParameter("passwort").
                equals(request.getParameter("passwort2")))
            {
                
                Person person = SessionBean.createPerson(request.getParameter("name"),
                                                  request.getParameter("login"), 
                                                  request.getParameter("passwort"));    
                request.setAttribute("name", request.getParameter("name"));
                request.setAttribute("includepage", "/registrierenerfolg.jsp");
                request.getRequestDispatcher("menu.jsp").forward(request, response);
               }else{
                
                request.setAttribute("status","Das hat leider nicht geklappt!");
                request.setAttribute("includepage","/registrieren.jsp");
                request.getRequestDispatcher("menu.jsp").forward(request, response);
            }
                
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
