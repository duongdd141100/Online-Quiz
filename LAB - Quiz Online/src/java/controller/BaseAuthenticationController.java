/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author Do Duc Duong
 */
@WebServlet(name = "BaseAuthenticationController", urlPatterns = {"/BaseAuthenticationController"})
public abstract class BaseAuthenticationController extends HttpServlet {


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
        if(isValidAuthentication(request)) {
            processGet(request, response);
        } else {
            response.sendRedirect("home");
        }
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
        if(isValidAuthentication(request)) {
            processPost(request, response);
        } else {
            response.sendRedirect("home");
        }
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

    private boolean isValidAuthentication(HttpServletRequest request) {
        Account account = (Account) request.getSession().getAttribute("account");
        String url = request.getServletPath();
        if(account == null) {
            return false;
        }
        
        AccountDAO dbAccount = new AccountDAO();
        if(dbAccount.isAccessSite(account, url)) {
            return true;
        } else {
            return false;
        }
    }
    abstract void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;
    abstract void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;
    

}
