<%-- 
    Document   : login
    Created on : Jan 31, 2023, 6:02:28 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login Page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h1>Login</h1>
        <a style="color: red">${MESS}</a>
        <form action="MainController" method="POST">
            UserName: <input type="text" name="txtEmail" value="" placeholder="Input email here" required=""> <br/>
            Password: <input type="password" name="txtPassword" value="" placeholder="Input password here" required=""><br/>
            <input type="submit" name="action" value="Login">
            <input type="reset" name="reset" value="Reset">
            <a href="MainController?action=RegisterForm">Register</a>
        </form>
    </body>
</html>
