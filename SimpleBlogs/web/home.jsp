<%-- 
    Document   : home
    Created on : Jan 13, 2023, 8:33:45 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
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
            <a style="color: red">${ERR_MESS}</a>
            <form action="MainController" method="POST">
                <input type="hidden" name="author" value="${sessionScope.USER.fullName}"/>
                Your post: <br/>
                Tittle: <input type="text" value="${param.txtTittle}" name="txtTittle"/> <br/>
                Short Description: <input type="text" value="${param.txtShortDescription}" name="txtShortDescription"/> <br/>
                Content: <br/> <textarea name="txtContent"></textarea> <br/>
                <button type="submit" name="action" value="PostBlog">Post</button>
            </form>
            <br/> <br/> <br/>
        <form action="MainController" method="POST">
            Search: <input type="text" value="${param.txtSearchContent}" name="txtSearchContent"/>
            <input type="submit" value="Search" name="action"/>
        </form>
        <table border="1">
            <thead>
                <tr>
                    <th>Tittle</th>
                    <th>Short Description</th>
                    <th>Author</th>
                    <th>Date</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="blogDTO" items="${sessionScope.LIST_BLOG}">
                    <c:if test="${blogDTO.status == 'approved'}">
                        <tr>

                            <td><a href="MainController?action=DetailBlog&blogId=${blogDTO.blogId}">${blogDTO.tittle}</a></td>
                            <td>${blogDTO.shortDescription}</td>
                            <td>${blogDTO.author}</td>
                            <td>${blogDTO.dateOfCreate}</td>


                        </tr>
                    </c:if>
                    
                </c:forEach>
            </tbody>
        </table>

    </body>
</html>
