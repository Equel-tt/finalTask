<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${empty sessionScope.language}"><fmt:setLocale value="ru_RU"/></c:if>
<c:if test="${not empty sessionScope.language}"><fmt:setLocale value="${sessionScope.language}"/></c:if>
<fmt:setBundle basename="lang.text" scope="session" var="bundle"/>
<!DOCTYPE html>
<html>
<head>
    <title>Arrival overview</title>

    <!-- URL -->
    <c:url value="/control" var="urlServlet"/>
    <c:url value="/js/sort.js" var="urlSortScript"/>

    <!-- Bootstrap core CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>

    <script src="<c:out value="${urlSortScript}"/>"></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<jsp:useBean id="result" scope="request" type="java.util.List"/>
<br>
<div class="container-fluid col-md-8">
    <table class="table table-bordered table-hover" id="tableForSort">
        <thead>
        <tr class="bg-success">
            <th onclick="sortTable(0)"><fmt:message key="arrival.table.doc" bundle="${bundle}"/></th>
            <th onclick="sortTable(1)"><fmt:message key="product.count" bundle="${bundle}"/></th>
            <th onclick="sortTable(2)"><fmt:message key="arrival.table.date" bundle="${bundle}"/></th>
            <th onclick="sortTable(3)"><fmt:message key="arrival.table.product" bundle="${bundle}"/></th>
            <th onclick="sortTable(4)"><fmt:message key="arrival.table.price" bundle="${bundle}"/></th>
            <th onclick="sortTable(5)"><fmt:message key="general.user" bundle="${bundle}"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${result}" var="arrival">
            <tr>
                <td><c:out value="${arrival.document}"/></td>
                <td><c:out value="${arrival.count}"/></td>
                <td><c:out value="${arrival.date}"/></td>
                <td><c:out value="${arrival.product.name}"/></td>
                <td><c:out value="${arrival.price}"/></td>
                <td><c:out value="${arrival.user.surname}"/></td>
                <c:if test="${sessionScope.user.role == 1}">
                    <td>
                        <form role="form" action="<c:out value="${urlServlet}"/>" method="post">
                            <input type="hidden" name="command" value="DELETE_ARRIVAL">
                            <input type="hidden" name="doc" value="${arrival.document}">
                            <input type="hidden" name="product" value="${arrival.product.id}">
                            <div class="input-group">
                                <input class="form-control row-cols-sm-2 btn-danger" type="submit"
                                       value="<fmt:message key="general.delete" bundle="${bundle}"/>">
                            </div>
                        </form>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<br>
</body>
</html>
