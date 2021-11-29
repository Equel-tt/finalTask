<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${empty sessionScope.language}"><fmt:setLocale value="ru_RU"/></c:if>
<c:if test="${not empty sessionScope.language}"><fmt:setLocale value="${sessionScope.language}"/></c:if>
<fmt:setBundle basename="lang.text" scope="session" var="bundle"/>
<html>
<head>
    <title>ArchiveOverview</title>
    <jsp:useBean id="result" scope="request" type="java.util.List"/>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<br>
<div class="container-fluid col-md-3">
    <table class="table table-bordered table-hover container-sm">
        <thead>
        <tr class="bg-success">
            <th class="col-sm-3"><fmt:message key="general.date" bundle="${bundle}"/></th>
            <th class="col-sm-3"><fmt:message key="product.id" bundle="${bundle}"/></th>
            <th class="col-sm-3"><fmt:message key="product.count" bundle="${bundle}"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${result}" var="archive">
            <tr>
                <td><c:out value="${archive.date}"/></td>
                <td><c:out value="${archive.product.id}"/></td>
                <td><c:out value="${archive.count}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
