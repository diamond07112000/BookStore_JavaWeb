/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhatquang.controllers;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nhatquang.User.UserDTO;
import nhatquang.blog.BlogDAO;
import nhatquang.blog.BlogDTO;
import nhatquang.comment.CommentDAO;

/**
 *
 * @author Admin
 */
public class MainController extends HttpServlet {

    private static final String LOGIN_PAGE = "login.jsp";
    private static final String LOGIN = "LoginController";
    private static final String HOME = "home.jsp";
    private static final String ADMIN_PAGE = "adminPage.jsp";
    private static final String REGISTER_FORM = "register.jsp";
    private static final String REGISTER = "RegisterController";
    private static final String LOGOUT = "LogoutController";
    private static final String SEARCH = "SearchController";
    private static final String DETAIL_BLOG = "DetailBlogController";
    private static final String POST_BLOG = "PostBlogController";
    private static final String POST_COMMENT = "PostCommentController";
    private static final String CONFIRM = "ConfirmController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        String url = HOME;

        try {
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("USER");
            if (user != null) {
                if (action == null) {
                    BlogDAO blogDAO = new BlogDAO();
                    List<BlogDTO> listBlog = blogDAO.getAllBlog();
                    Collections.sort(listBlog);
                    session.setAttribute("LIST_BLOG", listBlog);
                    if (user.getRole().trim().equals("admin")) {
                        url = ADMIN_PAGE;
                    } else {
                        url = HOME;
                    }
                    
                } else if (action.equals("Logout")) {
                    url = LOGOUT;
                } else if (action.equals("Search")) {
                    url = SEARCH;
                } else if (action.equals("DetailBlog")) {
                    url = DETAIL_BLOG;
                } else if (action.equals("PostBlog")) {
                    url = POST_BLOG;
                } else if (action.equals("PostComment")) {
                    url = POST_COMMENT;
                } else if (action.equals("Confirm")){
                    url = CONFIRM;
                }

            } else {
                if (action == null) {
                    BlogDAO blogDAO = new BlogDAO();
                    List<BlogDTO> listBlog = blogDAO.getAllBlog();
                    Collections.sort(listBlog);
                    session.setAttribute("LIST_BLOG", listBlog);
                    url = HOME;
                } else if (action.equals("Login")) {
                    url = LOGIN;
                } else if (action.equals("RegisterForm")) {
                    url = REGISTER_FORM;
                } else if (action.equals("Register")) {
                    url = REGISTER;
                } else if (action.equals("Search")) {
                    url = SEARCH;
                } else if (action.equals("DetailBlog")) {
                    url = DETAIL_BLOG;
                } else if (action.equals("PostBlog")) {
                    url = LOGIN_PAGE;
                } else if (action.equals("PostComment")) {
                    url = LOGIN_PAGE;
                }
            }

        } catch (Exception e) {
            log("Error at MainController: " + e.getMessage());
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
