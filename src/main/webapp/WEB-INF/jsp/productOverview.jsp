<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${empty sessionScope.language}"><fmt:setLocale value="ru_RU"/></c:if>
<c:if test="${not empty sessionScope.language}"><fmt:setLocale value="${sessionScope.language}"/></c:if>
<fmt:setBundle basename="lang.text" scope="session" var="bundle"/>
<!DOCTYPE html>
<html>
<head>
    <title>Product overview</title>
    <!-- Bootstrap core CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>

</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<jsp:useBean id="result" scope="request" type="by.allahverdiev.finaltask.entity.Product"/>

<table class="table table-bordered table-hover container-sm">
    <thead>
    <tr class="bg-success">
        <td rowspan="2" class="text-dark"><fmt:message key="product.name" bundle="${bundle}"/></td>
        <td colspan="3"><fmt:message key="general.user" bundle="${bundle}"/></td>
        <td rowspan="2"><fmt:message key="general.type" bundle="${bundle}"/></td>
        <td rowspan="2"><fmt:message key="general.provider" bundle="${bundle}"/></td>
    </tr>
    </thead>
    <tbody>
    <tr>
        <th class="col-sm-3"></th>
        <th class="col-sm-1"><fmt:message key="general.user.surname" bundle="${bundle}"/></th>
        <th class="col-sm-1"><fmt:message key="general.user.name" bundle="${bundle}"/></th>
        <th class="col-sm-1"><fmt:message key="general.user.patronymic" bundle="${bundle}"/></th>
    </tr>
    <tr>
        <td><c:out value="${result.name}"/></td>
        <td><c:out value="${result.manager.surname}"/></td>
        <td><c:out value="${result.manager.name}"/></td>
        <td><c:out value="${result.manager.patronymic}"/></td>
        <td><c:out value="${result.productType.name}"/></td>
        <td><c:out value="${result.provider.name}"/></td>
    </tr>
    </tbody>

</table>

</body>
</html>
