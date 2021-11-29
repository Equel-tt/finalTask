<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${empty sessionScope.language}"><fmt:setLocale value="ru_RU"/></c:if>
<c:if test="${not empty sessionScope.language}"><fmt:setLocale value="${sessionScope.language}"/></c:if>
<fmt:setBundle basename="lang.text" scope="session" var="bundle"/>
<html>
<head>
    <!-- Bootstrap core CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>

    <title>Warehouse Overview</title>
    <jsp:useBean id="result" scope="request" type="java.util.Map"/>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<br>
<table class="table table-bordered table-hover container-sm">
    <thead>
    <tr class="bg-success">
        <th class="col-sm-1"><fmt:message key="product.id" bundle="${bundle}"/></th>
        <th class="col-sm-3"><fmt:message key="product.name" bundle="${bundle}"/></th>
        <th class="col-sm-1"><fmt:message key="product.count" bundle="${bundle}"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${result.entrySet()}" var="product">
        <tr>
            <td><c:out value="${product.getKey().id}"/></td>
            <td><c:out value="${product.getKey().name}"/></td>
            <td><c:out value="${product.getValue()}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
