<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${empty sessionScope.language}"><fmt:setLocale value="ru_RU"/></c:if>
<c:if test="${not empty sessionScope.language}"><fmt:setLocale value="${sessionScope.language}"/></c:if>
<fmt:setBundle basename="lang.text" scope="session" var="bundle"/>
<html>
<head>
    <title>Need overview</title>

    <!-- URL -->
    <c:url value="/js/sort.js" var="urlSortScript"/>

    <!-- Bootstrap core CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>

    <jsp:useBean id="result" scope="request" type="java.util.List"/>
    <script src="<c:out value="${urlSortScript}"/>"></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<table class="table table-bordered table-hover container-sm" id="tableForSort">

    <thead>
    <tr>
        <th onclick="sortTable(0)"><fmt:message key="need.month" bundle="${bundle}"/></th>
        <th onclick="sortTable(1)"><fmt:message key="product.name" bundle="${bundle}"/></th>
        <th onclick="sortTable(2)"><fmt:message key="product.count" bundle="${bundle}"/></th>
        <th onclick="sortTable(3)"><fmt:message key="need.dep" bundle="${bundle}"/></th>
    </tr>
    </thead>

    <tbody>
    <c:forEach items="${result}" var="need">
        <tr>
            <td><c:out value="${need.month}"/></td>
            <td><c:out value="${need.product.name}"/></td>
            <td><c:out value="${need.count}"/></td>
            <td><c:out value="${need.department.id}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
