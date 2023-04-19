/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhatquang.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nhatquang.User.UserDAO;
import nhatquang.User.UserDTO;
import nhatquang.blog.BlogDAO;
import nhatquang.blog.BlogDTO;

/**
 *
 * @author Admin
 */
public class LoginController extends HttpServlet {

    private static final String LOGIN_PAGE = "login.jsp";
    private static final String HOME = "home.jsp";
    private static final String ADMIN_PAGE = "adminPage.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String email = request.getParameter("txtEmail");
        String password = request.getParameter("txtPassword");

        String url = LOGIN_PAGE;
        try {
            UserDAO userDao = new UserDAO();
            String hashPass = userDao.sha256(password);
            UserDTO user = userDao.checkLogin(email, hashPass);
            if (user != null) {
                if (!user.getStatus().equals("False")) {
                    HttpSession session = request.getSession();
                    session.setAttribute("USER", user);
                    BlogDAO blogDAO = new BlogDAO();
                    List<BlogDTO> listBlog = blogDAO.getAllBlog();
                    session.setAttribute("LIST_BLOG", listBlog);
                    if (user.getRole().trim().equals("admin")) {
                        url = ADMIN_PAGE;
                    } else {
                        url = HOME;
                    }
                } else {
                    request.setAttribute("MESS", "This email is banned");
                }
            }
        } catch (Exception e) {
            log("Error at LoginController: " + e.getMessage());
        } finally {
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
