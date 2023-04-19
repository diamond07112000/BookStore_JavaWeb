/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhatquang.User;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import nhatquang.utils.DBUtils;

/**
 *
 * @author Admin
 */
public class UserDAO {

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

    public UserDTO checkLogin(String email, String password) throws ClassNotFoundException, SQLException {
        UserDTO user = null;
        try {
            String sql = "SELECT fullName, roleName, status FROM tblUser JOIN roles ON tblUser.roleId = roles.roleId WHERE email = ? and password = ?";
            conn = DBUtils.getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setString(1, email);
                ps.setString(2, password);
                rs = ps.executeQuery();
                if (rs.next()) {
                    
                    user = new UserDTO(email, rs.getString("fullName"), password, rs.getString("roleName"), rs.getString("status"));
                }
            }
        } finally {
            closeConnection();
        }
        return user;
    }

    public String sha256(String base) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkDuplicate(String email) throws SQLException, ClassNotFoundException {
        boolean check = false;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "Select email\n"
                        + "From tblUser \n"
                        + "WHERE email = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, email);

                rs = ps.executeQuery();
                if (rs.next()) {
                    check = true;
                }
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return check;
    }
    
    public boolean insertUser(UserDTO user) throws SQLException{
        boolean check = false;
        
        try {
            conn = DBUtils.getConnection();
            if(conn != null){
                String sql = "INSERT INTO tblUser(email, fullName, password, roleId, status)\n"+
                            "VALUES(?,?,?,?,?)";
                ps = conn.prepareStatement(sql);
                ps.setString(1, user.getEmail());
                ps.setString(2, user.getFullName());
                ps.setString(3, user.getPassword());
                ps.setString(4, "US");
                ps.setString(5, user.getStatus());
                check = ps.executeUpdate() > 0 ? true:check;
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return check;
    }
}
