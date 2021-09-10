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
    <c:forEach items="${questionGroup}" var="questionGroup" varStatus="loop">
        <li>
            <h2>${loop.index}</h2>
            <form:form action="${pageContext.request.contextPath}/aut/logout"
                       method="POST">
                <input type="submit"><h3>${questionGroup}</h3></input>
            </form:form>
        </li>
    </c:forEach>
</ul>
</html>