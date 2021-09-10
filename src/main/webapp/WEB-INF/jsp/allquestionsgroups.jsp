<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Welcome Page</title>
    <link rel="stylesheet" type="text/css" href="<spring:url value='/resources/css/studyquestion.css'/>"/>
</head>

<ul class="tilesWrap">
    <c:forEach items="${questionsGroups}" var="questionsGroups" varStatus="loop">
        <li>
            <h2>${loop.index}</h2>
            <div class="d-flex justify-content-center links">
                Don't have an account?<a href="/question/getAllQuestionsByQuestionGroup/${questionsGroups}">${questionsGroups}</a>
            </div>

        </li>
    </c:forEach>
</ul>
</html>