<%-- 
    Document   : blogDetail
    Created on : Feb 1, 2023, 6:23:29 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detail Blog Page</title>
    </head>
    <body>
        
        <c:if test="${USER != null}">
            <a href="MainController?action=Logout">Logout</a>
            <h1>Welcome ${USER.fullName}</h1>
        </c:if>
        <c:if test="${USER == null}">
            <a href="login.jsp">Login</a> <br/>
        </c:if>
            <a style="color: green">${MESS}</a>
            <a style="color: red">${ERR_MESS}</a> <br/>
            <a>${sessionScope.BLOG.dateOfCreate}</a> &nbsp;&nbsp;&nbsp;&nbsp;
            <a>author: ${sessionScope.BLOG.author}</a>
            <h2>Tittle: ${sessionScope.BLOG.tittle}</h2>
            <h3>Short Description: ${sessionScope.BLOG.shortDescription}</h3>
            <p>Content: ${sessionScope.BLOG.content}</p><br/><br/>
            <form action="MainController" method="POST">
                <input type="hidden" name="blogId" value="${sessionScope.BLOG.blogId}"/>
                Your Comment: <input type="text" value="" name="txtComment"/>
                <button type="submit" value="PostComment" name="action">Post</button>
            </form> <br/>
            
            List comment: <br/>
            <c:forEach var="commentDTO" items="${sessionScope.BLOG.listComment}">                
            <a>${commentDTO.fullName}: ${commentDTO.text}</a> <br/>           
            </c:forEach>
    </body>
</html>
