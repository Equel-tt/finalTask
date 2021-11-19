<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <jsp:useBean id="result" scope="request" type="java.util.Map"/>
</head>
<body>
<table border="1px">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Count</th>
    </tr>

    <c:forEach items="${result.entrySet()}" var="product">
        <tr>
            <td><c:out value="${product.getKey().id}"/></td>
            <td><c:out value="${product.getKey().name}"/></td>
            <td><c:out value="${product.getValue()}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
