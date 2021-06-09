<%-- 
    Document   : make-quiz
    Created on : May 31, 2021, 9:21:50 AM
    Author     : Do Duc Duong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
                    <a href="logout">Log out</a>
                </div>
                <div id="bodyMakeQuiz">
                    <form action="make-quiz" method="POST" id="formAddQuestion">
                        <div>
                            <span style="color: #5584a1;">Question:</span>
                            <textarea id="question" name="question"></textarea>
                            <span style="display: none; color: red;" id="errorEmptyQ">Question must has value!</span><br>
                            <span style="display: none; color: red;" id="errorLongQ">Question is too long!</span><br>
                        </div>
                        <div>
                            <span style="color: #5584a1;">Option 1:</span>
                            <textarea class="answer" name="${listAnswer.size() + 1}"></textarea>
                            <span  style="display: none; color: red;" class="errorEmpty">Answer must has value!</span><br>
                            <span style="display: none; color: red;" class="errorLong">Answer is too long!</span><br>
                        </div>
                        <div>
                            <span style="color: #5584a1;">Option 2:</span>
                            <textarea class="answer" name="${listAnswer.size() + 2}"></textarea>
                            <span style="display: none; color: red;" class="errorEmpty">Answer must has value!</span><br>
                            <span style="display: none; color: red;" class="errorLong">Answer is too long!</span><br>
                        </div>
                        <div>
                            <span style="color: #5584a1;">Option 3:</span>
                            <textarea class="answer" name="${listAnswer.size() + 3}"></textarea>
                            <span style="display: none; color: red;" class="errorEmpty">Answer must has value!</span><br>
                            <span style="display: none; color: red;" class="errorLong">Answer is too long!</span><br>
                        </div>
                        <div>
                            <span style="color: #5584a1;">Option 4:</span>
                            <textarea class="answer" name="${listAnswer.size() + 4}"></textarea>
                            <span style="display: none; color: red;" class="errorEmpty">Answer must has value!</span><br>
                            <span style="display: none; color: red;" class="errorLong">Answer is too long!</span><br>
                        </div>
                        <div id="divAnswerChoice">
                            <span>Answer:</span>
                            <input id="option1" type="radio" name="answerId" value="${listAnswer.size() + 1}">
                            <label style="color: #5584a1;" for="option1">Option 1</label>
                            <input id="option2" type="radio" name="answerId" value="${listAnswer.size() + 2}">
                            <label style="color: #5584a1;" for="option2">Option 2</label>
                            <input id="option3" type="radio" name="answerId" value="${listAnswer.size() + 3}">
                            <label style="color: #5584a1;" for="option3">Option 3</label>
                            <input id="option4" type="radio" name="answerId" value="${listAnswer.size() + 4}">
                            <label style="color: #5584a1;" for="option4">Option 4</label>
                        </div>
                            <span style="display: none; color: red;" id="errorAnswerRadio">Please choose the correct answer</span>
                        <input type="button" onclick="isValidValue()" value="Save">
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>

<script>
    function isValidValue() {
        var isValid = true
        var question = document.getElementById("question")
        if(question.value.trim().length == 0) {
            document.getElementById("errorEmptyQ").style.display = "block"
            isValid = false
        } else {
            document.getElementById("errorEmptyQ").style.display = "none"
        }
        if(question.value.length > 100) {
            isValid = false
            document.getElementById("errorLongQ").style.display = "block"
        } else {
            document.getElementById("errorLongQ").style.display = "none"            
        }
        
        var answer = document.getElementsByClassName("answer")
        var errorEmpty = document.getElementsByClassName("errorEmpty")
        var errorLong = document.getElementsByClassName("errorLong")
        for(var i = 0; i < answer.length; i++) {
            if(answer[i].value.trim().length == 0) {
                errorEmpty[i].style.display = "block"
                isValid = false
            } else {
                errorEmpty[i].style.display = "none"
            }
            if(answer[i].value.trim().length > 100) {
                errorLong[i].style.display = "block"
                isValid = false
            } else {
                errorLong[i].style.display = "none"
            }
        }
        var radio = document.getElementsByName("answerId")
        var isChecked = false
        for(var i = 0; i < radio.length; i++) {
            if(radio[i].checked) {
                isChecked = true
            }
        }
        if(!isChecked) {
            document.getElementById("errorAnswerRadio").style.display = "block"
            isValid = false;
        } else {
            document.getElementById("errorAnswerRadio").style.display = "none"
        }
        if(isValid) {
            document.getElementById("formAddQuestion").submit()
        }
    }
</script>
