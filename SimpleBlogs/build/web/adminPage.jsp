<%-- 
    Document   : adminPage
    Created on : Feb 2, 2023, 4:45:43 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
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
            <input type="hidden" name="checkSearch" value="searchTittle"/>
            Search By Tittle: <input type="text" value="${param.txtSearchTittle}" name="txtSearchTittle"/> <br/>
            <b>Status:</b> <br/>
            Status:
            <select name="status">
                <option value="waiting">waiting</option>
                <option value="approved">approved</option>
                <option value="deleted">deleted</option>
            </select> <br/>
            <input type="submit" value="Search" name="action"/>
        </form> <br/> <br/>
        <form action="MainController" method="POST">
            <input type="hidden" name="checkSearch" value="searchContent"/>
            Search By Content: <input type="text" value="${param.txtSearchContent}" name="txtSearchContent"/>
            <input type="submit" value="Search" name="action"/>
        </form> <br/> <br/> <br/>
        <table border="1">
            <thead>
                <tr>
                    <th>Tittle</th>
                    <th>Short Description</th>
                    <th>Author</th>
                    <th>Date</th>
                    <th>Content</th>
                    <th>Status</th>


                </tr>
            </thead>
            <tbody>
                <c:forEach var="blogDTO" items="${sessionScope.LIST_BLOG}">
                <form action="MainController" method="POST">
                    <input type="hidden" name="blogId" value="${blogDTO.blogId}"/>
                    <tr>
                        <td><a href="MainController?action=DetailBlog&blogId=${blogDTO.blogId}">${blogDTO.tittle}</a></td>
                        <td>${blogDTO.shortDescription}</td>
                        <td>${blogDTO.author}</td>
                        <td>${blogDTO.dateOfCreate}</td>
                        <td>${blogDTO.content}</td>
                        <td>
                            <select name="status">
                                <option value="${blogDTO.status}">${blogDTO.status}</option>
                                <option value="waiting">waiting</option>
                                <option value="approved">approved</option>
                                <option value="delete">delete</option>
                            </select>
                        </td>
                        <td><input type="submit" name="action" value="Confirm"/></td>
                    </tr>
                </form>
                </c:forEach>
            </tbody>
        </table>

    </body>
</html>
