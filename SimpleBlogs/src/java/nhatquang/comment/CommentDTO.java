/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhatquang.comment;

import java.io.Serializable;
import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class CommentDTO implements Serializable, Comparable<CommentDTO>{
    private int commentId;
    private String fullName;
    private String text;
    private int blogId;
    private String dateOfCreate;
    private boolean isDelete;

    public CommentDTO() {
    }

    public CommentDTO(int commentId, String fullName, String text, int blogId, String dateOfCreate, boolean isDelete) {
        this.commentId = commentId;
        this.fullName = fullName;
        this.text = text;
        this.blogId = blogId;
        this.dateOfCreate = dateOfCreate;
        this.isDelete = isDelete;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public String getDateOfCreate() {
        return dateOfCreate;
    }

    public void setDateOfCreate(String dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
    }

    public boolean isIsDelete() {
        return isDelete;
    }

    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public int compareTo(CommentDTO o){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date1 = new Date();
        Date date2 = new Date();
        try {
            date1 = formatter.parse(o.dateOfCreate);
            date2 = formatter.parse(this.dateOfCreate);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return date2.compareTo(date1);
    }
    
    
}
