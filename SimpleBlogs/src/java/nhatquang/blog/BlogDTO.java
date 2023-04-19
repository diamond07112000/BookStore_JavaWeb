/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhatquang.blog;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import nhatquang.comment.CommentDTO;

/**
 *
 * @author Admin
 */
public class BlogDTO implements Serializable, Comparable<BlogDTO>{
    private int blogId;
    private String author;
    private String tittle;
    private String shortDescription;
    private String content;
    private String dateOfCreate;
    private String status;
    
    private List<CommentDTO> listComment;

    public List<CommentDTO> getListComment() {
        return listComment;
    }

    public void setListComment(List<CommentDTO> listComment) {
        this.listComment = listComment;
    }

    public BlogDTO() {
    }

    public BlogDTO(int blogId, String author, String tittle, String shortDescription, String content, String dateOfCreate, String status) {
        this.blogId = blogId;
        this.author = author;
        this.tittle = tittle;
        this.shortDescription = shortDescription;
        this.content = content;
        this.dateOfCreate = dateOfCreate;
        this.status = status;
    }

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDateOfCreate() {
        return dateOfCreate;
    }

    public void setDateOfCreate(String dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    

    
    @Override
    public int compareTo(BlogDTO o) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date1 = new Date();
        Date date2 = new Date();
        try {
            date1 = formatter.parse(o.dateOfCreate);
            date2 = formatter.parse(this.dateOfCreate);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return date1.compareTo(date2);
    }
    
    
}
