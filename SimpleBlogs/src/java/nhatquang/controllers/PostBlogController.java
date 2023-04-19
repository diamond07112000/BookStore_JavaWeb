/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhatquang.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nhatquang.User.UserDTO;
import nhatquang.blog.BlogDAO;
import nhatquang.blog.BlogDTO;

/**
 *
 * @author Admin
 */
public class PostBlogController extends HttpServlet {

    private static final String HOME = "home.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = HOME;
        String tittle = request.getParameter("txtTittle");
        String shortDescription = request.getParameter("txtShortDescription");
        String content = request.getParameter("txtContent");
        String author = request.getParameter("author");
        try {
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO)session.getAttribute("USER");
            BlogDAO blogDAO = new BlogDAO();
            int blogId = 1;
           
            while (true) {
                BlogDTO dto = blogDAO.getBlogByBlogId(blogId);
                if (dto != null) {
                    blogId += 1;
                } else {
                    break;
                }
            }
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            String dateOfCreate = format.format(date);
            BlogDTO blogDTO = new BlogDTO(blogId, author, tittle, shortDescription, content, dateOfCreate, "Waiting");
            boolean check = blogDAO.insertBlog(blogDTO, user.getEmail());
            if(check){
                request.setAttribute("MESS", "Post success. Wait for admin approve");
            } else {
                request.setAttribute("ERR_MESS", "Your Post fail!");
            }
        } catch (ClassNotFoundException e) {
            log("ClassNotFoundException at PostBlogController: " + e.getMessage());
        } catch (SQLException e) {
            log("SQLException at PostBlogController: " + e.getMessage());
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
