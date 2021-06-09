<%-- 
    Document   : home
    Created on : May 26, 2021, 9:56:13 AM
    Author     : Do Duc Duong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="css/home.css" rel="stylesheet" type="text/css"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div id="home">
            <div id="content">
                <div id="menu">
                    <a href="home">Home</a>
                    <a href="take-quiz">Take Quiz</a>
                    <a href="make-quiz">Make Quiz</a>
                    <a href="manage-quiz">Manage Quiz</a>
                </div>
                <div id="bodyLogin">
                    <h2>Login</h2>
                    <form id="loginForm" action="home" method="POST">
                        <table>
                            <tr>
                                <th>Username: </th>
                                <td><input type="text" name="username" id="username"></td>
                            </tr>
                            <tr>
                                <th>Password: </th>
                                <td><input type="password" name="password" id="password"></td>
                            </tr>
                        </table>
                        <c:if test="${isValid != null   }">
                            <p style="color: red;" id="errorAccount">*Username or password is incorrect!</p>
                        </c:if>
                        <input type="submit" value="Sign in">
                        <a id="register" href="register">Register</a>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
