<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <jsp:useBean id="result" scope="request" type="java.util.List"/>
</head>
<body>
<table border="1px">
    <tr>
        <th>month</th>
        <th>product</th>
        <th>count</th>
        <th>department</th>
    </tr>

    <c:forEach items="${result}" var="need">
        <tr>
            <td><c:out value="${need.month}"/></td>
            <td><c:out value="${need.product.name}"/></td>
            <td><c:out value="${need.count}"/></td>
            <td><c:out value="${need.department.id}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
