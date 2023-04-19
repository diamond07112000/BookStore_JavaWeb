/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhatquang.blog;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import nhatquang.utils.DBUtils;

/**
 *
 * @author Admin
 */
public class BlogDAO implements Serializable {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    private void closeConnection() throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (ps != null) {
            ps.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public List<BlogDTO> getAllBlog() throws SQLException, ClassNotFoundException {
        List<BlogDTO> list = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT blogId, fullName, tittle, shortDescription, content, dateOfCreate, blogs.status FROM blogs JOIN tblUser ON blogs.userId = tblUser.email";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    list.add(new BlogDTO(rs.getInt("blogId"),
                            rs.getString("fullName"),
                            rs.getString("tittle"),
                            rs.getString("shortDescription"),
                            rs.getString("content"),
                            rs.getString("dateOfCreate"),
                            rs.getString("status").trim()));
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public List<BlogDTO> SearchBlogByContent(String search) throws SQLException, ClassNotFoundException {
        List<BlogDTO> list = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT blogId, fullName, tittle, shortDescription, content, dateOfCreate, blogs.status FROM blogs JOIN tblUser ON blogs.userId = tblUser.email WHERE content LIKE ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, "%" + search + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    list.add(new BlogDTO(rs.getInt("blogId"),
                            rs.getString("fullName"),
                            rs.getString("tittle"),
                            rs.getString("shortDescription"),
                            rs.getString("content"),
                            rs.getString("dateOfCreate"),
                            rs.getString("status").trim()));
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public BlogDTO getBlogByBlogId(int blogId) throws ClassNotFoundException, SQLException {
        BlogDTO dto = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT blogId, fullName, tittle, shortDescription, content, dateOfCreate, blogs.status FROM blogs JOIN tblUser ON blogs.userId = tblUser.email WHERE blogs.blogId = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, blogId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    dto = new BlogDTO(rs.getInt("blogId"),
                            rs.getString("fullName"),
                            rs.getString("tittle"),
                            rs.getString("shortDescription"),
                            rs.getString("content"),
                            rs.getString("dateOfCreate"),
                            rs.getString("status").trim());
                }
            }
        } finally {
            closeConnection();
        }
        return dto;
    }
    
    public boolean insertBlog(BlogDTO blogDTO, String userId) throws SQLException{
        boolean check = false;
        
        try {
            conn = DBUtils.getConnection();
            if(conn != null){
                String sql = "INSERT INTO blogs(blogId, userId, tittle, shortDescription, content, dateOfCreate, blogs.status)\n"+
                            "VALUES(?,?,?,?,?,?,?)";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, blogDTO.getBlogId()  );
                ps.setString(2, userId);
                ps.setString(3, blogDTO.getTittle());
                ps.setString(4, blogDTO.getShortDescription());
                ps.setString(5, blogDTO.getContent());           
                ps.setString(6, blogDTO.getDateOfCreate());
                ps.setString(7, blogDTO.getStatus());
                check = ps.executeUpdate() > 0 ? true:check;
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return check;
    }
    
    public List<BlogDTO> SearchBlogByAdmin(String search, String status) throws SQLException, ClassNotFoundException {
        List<BlogDTO> list = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT blogId, fullName, tittle, shortDescription, content, dateOfCreate, blogs.status FROM blogs JOIN tblUser ON blogs.userId = tblUser.email WHERE tittle LIKE ? "
                        + "AND blogs.status = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, "%" + search + "%");
                ps.setString(2, status);
                rs = ps.executeQuery();
                while (rs.next()) {
                    list.add(new BlogDTO(rs.getInt("blogId"),
                            rs.getString("fullName"),
                            rs.getString("tittle"),
                            rs.getString("shortDescription"),
                            rs.getString("content"),
                            rs.getString("dateOfCreate"),
                            rs.getString("status").trim()));
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }
    
    public boolean confirm(int blogId, String status) throws ClassNotFoundException, SQLException{
        boolean check = false;
        try{
            conn = DBUtils.getConnection();
            if(conn != null){
                String sql = "Update blogs SET blogs.status = ? WHERE blogId = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, status);
                ps.setInt(2, blogId);
                check = ps.executeUpdate() > 0 ? true : false;
            }
        }finally{
            closeConnection();
        }
        return check;
    }
}
