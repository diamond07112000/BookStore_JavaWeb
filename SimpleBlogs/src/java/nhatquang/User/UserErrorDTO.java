/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhatquang.User;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class UserErrorDTO implements Serializable{
    private String emailErr;
    private String fullNameErr;
    private String passwordErr;
    private String confirmPasswordErr;

    public UserErrorDTO() {
    }

    public UserErrorDTO(String emailErr, String fullNameErr, String passwordErr, String confirmPasswordErr) {
        this.emailErr = emailErr;
        this.fullNameErr = fullNameErr;
        this.passwordErr = passwordErr;
        this.confirmPasswordErr = confirmPasswordErr;
    }

    

    public String getEmailErr() {
        return emailErr;
    }

    public void setEmailErr(String emailErr) {
        this.emailErr = emailErr;
    }

    public String getFullNameErr() {
        return fullNameErr;
    }

    public void setFullNameErr(String fullNameErr) {
        this.fullNameErr = fullNameErr;
    }

    public String getPasswordErr() {
        return passwordErr;
    }

    public void setPasswordErr(String passwordErr) {
        this.passwordErr = passwordErr;
    }

    public String getConfirmPasswordErr() {
        return confirmPasswordErr;
    }

    public void setConfirmPasswordErr(String confirmPasswordErr) {
        this.confirmPasswordErr = confirmPasswordErr;
    }

    
}
