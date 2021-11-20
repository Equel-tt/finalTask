<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Result</title>
    <%--    <link rel="stylesheet" href="stylesheet.css">--%>
</head>
<body>
<jsp:useBean id="result" scope="request" type="by.allahverdiev.finaltask.entity.Product"/>
<table border="1px" cellpadding="4" cellspacing="0">
    <caption>Results</caption>
    <tr>
        <td rowspan="2">Name</td>
        <th colspan="3">Manager</th>
        <th rowspan="2">Type</th>
        <th rowspan="2">Provider</th>
    </tr>
    <tr>
        <th>Surname</th>
        <th>Name</th>
        <th>Patronymic</th>
    </tr>
    <tr>
        <td><c:out value="${result.name}"/></td>
        <td><c:out value="${result.manager.surname}"/></td>
        <td><c:out value="${result.manager.name}"/></td>
        <td><c:out value="${result.manager.patronymic}"/></td>
        <td><c:out value="${result.productType.name}"/></td>
        <td><c:out value="${result.provider.name}"/></td>
    </tr>

</table>

</body>
</html>
