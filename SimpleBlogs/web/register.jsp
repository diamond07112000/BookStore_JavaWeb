<%-- 
    Document   : register
    Created on : Jan 31, 2023, 6:08:40 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
    </head>
    <body>
        <h1>Register</h1>
        <a style="color: green">${MESS}<a>
        <form action="MainController" method="POST">
            Email: <input type="text" value="${param.txtEmail}" name="txtEmail"/> ${ERR.emailErr} <br/> 
            Fullname: <input type="text" value="${param.txtFullname}" name="txtFullname"/> ${ERR.fullNameErr} <br/>
            Password: <input type="password" value="${param.txtPassword}" name="txtPassword"/> ${ERR.passwordErr} <br/>
            Confirm Password: <input type="password" value="${param.txtConfirmPassword}" name="txtConfirmPassword"/> ${ERR.confirmPasswordErr} <br/>
            <input type="submit" value="Register" name="action"/>
            <input type="reset" value="Reset"/>
        </form>
            <a href="login.jsp">Back to Login page</a>
    </body>
</html>
