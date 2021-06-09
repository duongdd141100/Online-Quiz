<%-- 
    Document   : register
    Created on : Jun 2, 2021, 10:09:48 AM
    Author     : Do Duc Duong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/home.css" rel="stylesheet" type="text/css"/>
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
                <div id="bodyRegister">
                    <h3>Registration Form</h3>
                    <div id="divFormRegister">
                        <form id='formRegister' action='register' method="POST">
                            <table>
                                <tr>
                                    <th>User Name:</th>
                                    <td>
                                        <input type="text" name="user" id="user" required>
                                        <p id="errorUserSpace" style="color: red; display: none;">*Username cannot has space!</p>
                                        <p id="errorUser" style="color: red; display: none;">*Username is too long!</p>
                                        <p id="errorUserEmpty" style="color: red; display: none;">*Username cannot empty!</p>
                                        <p id="errorUserExist" style="color: red; display: none;">*User is exist!</p>
                                    </td>
                                </tr>
                                <tr>
                                    <th>Password:</th>
                                    <td>
                                        <input type="password" name="pass" id="pass" required>
                                        <p id="errorPassSpace" style="color: red; display: none;">*Password cannot has space!</p>
                                        <p id="errorPass" style="color: red; display: none;">*Password is too long!</p>
                                        <p id="errorPassEmpty" style="color: red; display: none;">*Password cannot empty!</p>

                                    </td>
                                </tr>
                                <tr>
                                    <th>User type:</th>
                                    <td>
                                        <select name="groupId">
                                            <option value="1">Teacher</option>
                                            <option value="2">Student</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <th>Email: </th>
                                    <td>
                                        <input type="text" name="email" id="email" required>
                                        <p id="errorEmailSpace" style="color: red; display: none;">*Email cannot has space!</p>
                                        <p id="errorEmail" style="color: red; display: none;">*Email is too long!</p>
                                        <p id="errorEmailEmpty" style="color: red; display: none;">*Email cannot empty!</p>
                                    </td>
                                </tr>
                            </table>
                            <input type="button" value="Register" onclick="validation()">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
<script>
    function validation() {
        var user = document.getElementById("user").value
        var pass = document.getElementById("pass").value
        var email = document.getElementById("email").value
        var isValid = true
        //check space input
        var isUserHasSpace = false
        var isPassHasSpace = false
        var isEmailHasSpace = false
        for(var i = 0; i < user.length; i++) {
            if(user[i] == ' ') {
                isUserHasSpace = true
                isValid = false
                break
            }
        }
        for(var i = 0; i < pass.length; i++) {
            if(pass[i] == ' ') {
                isPassHasSpace = true
                isValid = false
                break
            }
        }
        for(var i = 0; i < email.length; i++) {
            if(email[i] == ' ') {
                isValid = false
                isEmailHasSpace = true
                break
            }
        }
        if(isUserHasSpace) {
            document.getElementById("errorUserSpace").style.display = "block"
        } else {
            document.getElementById("errorUserSpace").style.display = "none"
        }
        if(isPassHasSpace) {
            document.getElementById("errorPassSpace").style.display = "block"
        } else {
            document.getElementById("errorPassSpace").style.display = "none"
        }  
        if(isEmailHasSpace) {
            document.getElementById("errorEmailSpace").style.display = "block"
        } else {
            document.getElementById("errorEmailSpace").style.display = "none"
        }
        //check length of input follow database
        if (user.trim().length > 20) {
            document.getElementById("errorUser").style.display = "block"
            isValid = false
        } else {
            document.getElementById("errorUser").style.display = "none"
        }
        if (pass.trim().length > 20) {
            document.getElementById("errorPass").style.display = "block"
            isValid = false
        } else {
            document.getElementById("errorPass").style.display = "none"
        }
        if (email.trim().length > 50) {
            document.getElementById("errorEmail").style.display = "block"
            isValid = false
        } else {
            document.getElementById("errorEmail").style.display = "none"
        }
        
        //check input empty
        if (user.trim().length == 0) {
            document.getElementById("errorUserEmpty").style.display = "block"
            isValid = false
        } else {
            document.getElementById("errorUserEmpty").style.display = "none"
        }
        if (pass.trim().length == 0) {
            document.getElementById("errorPassEmpty").style.display = "block"
            isValid = false
        } else {
            document.getElementById("errorPassEmpty").style.display = "none"
        }
        if (email.trim().length == 0) {
            document.getElementById("errorEmailEmpty").style.display = "block"
            isValid = false
        } else {
            document.getElementById("errorEmailEmpty").style.display = "none"
        }
        
        //check user exist
        var isUserExist = true
    <c:forEach items="${list}" var="l">
        var systemUser = '${l.user}'
        if (systemUser == user) {
            document.getElementById("errorUserExist").style.display = "block"
            isValid = false
            isUserExist = false
        }
    </c:forEach>
        if (isUserExist) {
            document.getElementById("errorUserExist").style.display = "none"
        }
        if (isValid) {
            document.getElementById("formRegister").submit()
        }
    }
</script>
