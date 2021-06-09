<%-- 
    Document   : manage-quiz
    Created on : Jun 1, 2021, 11:36:51 PM
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
                    <a href="logout">Log out</a>
                </div>
                <div id="bodyManageQuiz">
                    <div id="numberOfQuestion">
                        <span>Number of question: </span><span>${requestScope.totalQuestion}</span>
                    </div>
                    <div id="detailQuestion">
                        <table>
                            <tr>
                                <th>Question</th>
                                <th>Date Created</th>
                            </tr>
                            <c:forEach var="l" items="${list}">
                                <tr>
                                    <td style="color: #5584a1;">${l.content}</td>
                                    <td style="color: #5584a1;">${l.dateCreated}</td>
                                </tr>
                            </c:forEach>
                        </table>
                        <div id="pager"></div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
<script>
    RenderPager()
    function RenderPager() {
    var divRender = document.getElementById('pager');
    var gap = 2
    var totalPage = ${requestScope.totalPage}
    var pageIndex = ${requestScope.pageIndex}
    if (pageIndex > gap + 1) {
        divRender.innerHTML += "<a href='manage-page?page=1'>First</a>"
    }
    for (var i = pageIndex - gap; i < pageIndex + gap + 1; i++) {
        if (i > 0 && i <= totalPage) {
            if (i == pageIndex) {
                divRender.innerHTML += "<span>" + pageIndex + "</span>"
            } else {
                divRender.innerHTML += "<a href='manage-quiz?page=" + i + "'>" + i + "</a>"
            }
        }
    }
    if (pageIndex + gap < totalPage) {
        divRender.innerHTML += "<a href='manage-page?page=" + totalPage + "'>Last</a>"
    }
}
</script>