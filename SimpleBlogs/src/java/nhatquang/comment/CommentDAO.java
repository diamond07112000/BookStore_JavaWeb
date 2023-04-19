/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhatquang.comment;

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
public class CommentDAO implements Serializable{
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
    
    public List<CommentDTO> getAllCommentByBlogId(int blogId) throws ClassNotFoundException, SQLException{
        List<CommentDTO> list = new ArrayList<>();
        try{
            conn = DBUtils.getConnection();
            if(conn != null){
                String sql = "SELECT commentId, fullName, text, blogId, dateOfCreate, isDelete FROM tblUser JOIN comments ON tblUser.email = comments.userId WHERE blogId = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, blogId);
                rs = ps.executeQuery();
                while(rs.next()){
                    list.add(new CommentDTO(rs.getInt("commentId"), 
                            rs.getString("fullName"), 
                            rs.getString("text"), 
                            rs.getInt("blogId"), 
                            rs.getString("dateOfCreate"), 
                            rs.getBoolean("isDelete")));
                }
            }
        }finally{
            closeConnection();
        }
        return list;
    }

    public CommentDTO getCommentByCommentId(int commentId) throws ClassNotFoundException, SQLException {
        CommentDTO dto = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT commentId, fullName, text, blogId, dateOfCreate, isDelete FROM tblUser JOIN comments ON tblUser.email = comments.userId WHERE commentId = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, commentId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    dto = new CommentDTO(rs.getInt("commentId"), 
                            rs.getString("fullName"), 
                            rs.getString("text"), 
                            rs.getInt("blogId"), 
                            rs.getString("dateOfCreate"), 
                            rs.getBoolean("isDelete"));
                }
            }
        } finally {
            closeConnection();
        }
        return dto;
    }
    
    public boolean insertComment(CommentDTO commentDTO, String userId) throws SQLException{
        boolean check = false;
        
        try {
            conn = DBUtils.getConnection();
            if(conn != null){
                String sql = "INSERT INTO comments(commentId, userId, text, blogId, dateOfCreate, isDelete)\n"+
                            "VALUES(?,?,?,?,?,?)";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, commentDTO.getCommentId());
                ps.setString(2, userId);
                ps.setString(3, commentDTO.getText());
                ps.setInt(4, commentDTO.getBlogId());
                ps.setString(5, commentDTO.getDateOfCreate());
                ps.setBoolean(6, commentDTO.isIsDelete());
                check = ps.executeUpdate() > 0 ? true:check;
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return check;
    }
}
