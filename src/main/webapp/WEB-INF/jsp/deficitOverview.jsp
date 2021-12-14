<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${empty sessionScope.language}"><fmt:setLocale value="ru_RU"/></c:if>
<c:if test="${not empty sessionScope.language}"><fmt:setLocale value="${sessionScope.language}"/></c:if>
<fmt:setBundle basename="lang.text" scope="session" var="bundle"/>
<html>
<head>
    <title>Deficit Overview</title>

    <!-- URL -->
    <c:url value="/js/sort.js" var="urlSortScript"/>

    <!-- Bootstrap core CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>

    <jsp:useBean id="result" scope="request" type="java.util.Map"/>
    <script src="<c:out value="${urlSortScript}"/>"></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<br>
<table class="table table-bordered table-hover container-sm" id="tableForSort">
    <thead>
    <tr class="bg-success">
        <th onclick="sortTable(0)" class="col-sm-4"><fmt:message key="product.name" bundle="${bundle}"/></th>
        <th onclick="sortTable(1)" class="col-sm-2"><fmt:message key="deficit.need" bundle="${bundle}"/></th>
        <th onclick="sortTable(2)" class="col-sm-2"><fmt:message key="deficit.remainder" bundle="${bundle}"/></th>
        <th onclick="sortTable(3)" class="col-sm-2"><fmt:message key="deficit.receipt" bundle="${bundle}"/></th>
        <th onclick="sortTable(4)" class="col-sm-2"><fmt:message key="deficit.result" bundle="${bundle}"/></th>

    </tr>
    </thead>
    <tbody>
    <c:forEach items="${result.entrySet()}" var="product">
        <tr>
            <td><c:out value="${product.getKey().name}"/></td>
            <td><c:out value="${product.getValue().get(0)}"/></td>
            <td><c:out value="${product.getValue().get(1)}"/></td>
            <td><c:out value="${product.getValue().get(2)}"/></td>
            <td><c:out value="${product.getValue().get(3)}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
