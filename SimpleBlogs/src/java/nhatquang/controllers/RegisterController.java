/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhatquang.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nhatquang.User.UserDAO;
import nhatquang.User.UserDTO;
import nhatquang.User.UserErrorDTO;

/**
 *
 * @author Admin
 */
public class RegisterController extends HttpServlet {

    private static final String REGISTER_FORM = "register.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = REGISTER_FORM;
        String email = request.getParameter("txtEmail");
        String fullname = request.getParameter("txtFullname");
        String password = request.getParameter("txtPassword");
        String confirmPassword = request.getParameter("txtConfirmPassword");
        boolean check = true;
        try {
            UserErrorDTO userErr = new UserErrorDTO();
            if (!email.matches("(.+)@(\\S+)")) {
                check = false;
                userErr.setEmailErr("Invalid Email!");
            }
            if (!fullname.matches("[a-zA-Z ]+")) {
                check = false;
                userErr.setFullNameErr("Invalid Fullname");
            }
            if (password.length() < 6) {
                check = false;
                userErr.setPasswordErr("Password must be greater than 5");
            }
            if (!confirmPassword.equals(password)) {
                check = false;
                userErr.setConfirmPasswordErr("Not same Password");
            }
            if (!check) {
                request.setAttribute("ERR", userErr);
            } else {
                UserDAO dao = new UserDAO();
                boolean checkDup = dao.checkDuplicate(email);
                if (checkDup) {
                    userErr.setEmailErr("Email is existed!");
                    request.setAttribute("ERR", userErr);
                } else {
                    String hashPass = dao.sha256(password);
                    UserDTO user = new UserDTO(email, fullname, hashPass, "user", "New");
                    if (dao.insertUser(user)) {
                        
                        request.setAttribute("USER", user);
                        request.setAttribute("MESS", "Register Success!!");
                    }
                }
            }
        } catch(SQLException e){
            log("SQLException at RegisterController: " + e.getMessage());
        } catch(ClassNotFoundException e){
            log("ClassNotFoundException at RegisterController: " + e.getMessage());
        }finally {
            request.getRequestDispatcher(url).forward(request, response);
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
