<%-- 
    Document   : take-quiz
    Created on : May 28, 2021, 8:51:41 PM
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
                    <c:if test="${requestScope.gid == 1}">
                        <a href="make-quiz">Make Quiz</a>
                        <a href="manage-quiz">Manage Quiz</a>
                    </c:if>
                    <a href="logout">Log out</a>
                </div>
                <div id="bodyTakeQuiz">
                    <c:if test="${list.size() == 0}">
                        <div id="welcomeUser">
                            <h3 id="h3Welcome">Welcome </h3> <h3 id="accountName"> ${requestScope.name}</h3>
                        </div>
                        <p>Enter number of Question</p>
                        <form id="formGetNumOfQuestion" method="GET" action="take-quiz">
                            <input type="number" name="numOfQ" id="inputNumOfQ">
                            <p id="errorMaxNumOfQ" style="color: red; display: none;">Number of question is too many! Enter number of question less than!</p>
                            <input type="button" value="Start" onclick="checkNumberOfQuestion()">
                        </form>
                    </c:if>
                    <c:if test="${list.size() != 0}">
                        <div id="welcomeUser">
                            <h3 id="h3Welcome">Welcome </h3> <h3 id="accountName"> ${requestScope.name}</h3>
                        </div>
                        <c:if test="${requestScope.mark != null}">
                            Your Score: <p style="color: blue;">${requestScope.mark} (${requestScope.mark * 10}%) - 
                                <c:if test="${requestScope.mark >= 5}">
                                    Passed
                                </c:if>
                                <c:if test="${requestScope.mark < 5}">
                                    Not Passed
                                </c:if>

                            </p>
                            Take Another test <a href="take-quiz" id="startAgain">Start</a>
                        </c:if>
                        <c:if test="${requestScope.mark == null}">
                            <p id="timer"></p>
                            <p>${q.content}</p>
                            <c:forEach items="${q.listAnswer}" var="l">
                                <input id="a${l.id}" type="radio" value="${l.content}" onclick="saveToCookie('${q.id}', '${l.id}')" name="answer${q.id}"
                                       <c:forEach items="${listAnswerFromCookie}" var="la">
                                           <c:if test="${q.id == la.id && l.id == la.listAnswer[0].id}">
                                               checked
                                           </c:if>
                                       </c:forEach>
                                       >
                                <label for="a${l.id}">${l.content}</label>
                                <br>
                            </c:forEach>
                            <div id="changeQuestion">
                                <c:if test="${pageQ != 0}">
                                    <a href="take-quiz?pageQ=${pageQ - 1}">Previous</a>
                                </c:if>
                                <c:if test="${pageQ != list.size() - 1}">
                                    <a style="float: right" href="take-quiz?pageQ=${pageQ + 1}">Next</a>
                                </c:if>
                                <form action="take-quiz" method="POST" id="formSubmit">
                                    <input type="hidden" value="${list.size()}" name="numberOfQuestion">
                                    <input style="margin-top: 20px;" type="submit" value="Submit">
                                </form>
                            </div>
                        </c:if>

                    </c:if>

                </div>
            </div>
        </div>
    </body>
</html>
<script>
    function checkNumberOfQuestion() {
        var numOfQ = document.getElementById("inputNumOfQ").value
        var totalQ = ${requestScope.totalQuestion}
        if (numOfQ > totalQ) {
            document.getElementById("errorMaxNumOfQ").style.display = "block"
        } else {
            document.getElementById("formGetNumOfQuestion").submit()
        }

    }
    function saveToCookie(qid, aid) {
        document.cookie = qid + "=" + aid
    }
    function countplus() {
        var time = 60 * ${requestScope.numOfQ}
        var temp_value
        var cookieName = "temp_value"
        var timer = document.getElementById("timer")
        temp_value = getCookie(cookieName) ? getCookie(cookieName) : time
        timer.innerHTML = "Time remaining: " + convertTimer(temp_value--)
        document.cookie = "temp_value=" + (temp_value)
        if (temp_value < 1) {
            document.getElementById("formSubmit").submit();
            document.cookie = "temp_value=" + time
        }
    }
    setInterval(countplus, 1000)

    function convertTimer(s) {
        hou = Math.floor(s / 3600)
        min = Math.floor((s / 3600 - hou) * 60)
        sec = s - hou * 3600 - min * 60
        return ('0' + hou).slice(-2) + ':' + ('0' + min).slice(-2) + ':' + ('0' + sec).slice(-2)
    }

    function getCookie(cname) {
        var name = cname + "="
        var decodedCookie = decodeURIComponent(document.cookie)
        var ca = decodedCookie.split(';')
        for (var i = 0; i < ca.length; i++) {
            var c = ca[i]
            while (c.charAt(0) == ' ') {
                c = c.substring(1)
            }
            if (c.indexOf(name) == 0) {
                return c.substring(name.length, c.length)
            }
        }
        return "";
    }

</script>

