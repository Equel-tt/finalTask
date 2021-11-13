<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Warehouse</title>
</head>

<body>
<h1><%="Find product by ID"%>
</h1>

<form action="${pageContext.request.contextPath}/control" method="post">
    <input type="hidden" name="command" value="FIND_PRODUCT_BY_ID"/>
    <input type="hidden" name="destination" value="/result.jsp"/>
    <input required="required" type="text" name="productId" placeholder="type ID of product"/>
    <input type="submit" value="ok"/>
    <br>
    <br>

</form>
<br>
<form action="${pageContext.request.contextPath}/control" method="post">
    <input type="submit" value="Show all products" name="showAllProducts">
</form>
</body>
</html>