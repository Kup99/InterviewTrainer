<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Welcome Page</title>
    <link rel="stylesheet" type="text/css" href="<spring:url value='/resources/css/button.css'/>" />
    <link rel="stylesheet" type="text/css" href="<spring:url value='/resources/css/studyquestion.css'/>" />
</head>

<ul class="tilesWrap">
    <c:forEach items="${studiedQuestions}" var="questions" varStatus="loop">
        <li>
            <h2>${loop.index}</h2>
            <h3>${questions.questionName}</h3>
            <p>
                    ${questions.questionDescription}
            </p>
            <button>Read more</button>
        </li>
    </c:forEach>
</ul>
<div class="wrap">
    <button href="/question/getAllQuestionsGroups" class="button">Добавить новые вопросы</button>
</div>
</html>