<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Warehouse</title>
</head>

<body>
<h2><%="Find product by ID"%>
</h2>

<form action="${pageContext.request.contextPath}/control" method="post">
    <input type="hidden" name="command" value="FIND_PRODUCT_BY_ID"/>
    <input type="hidden" name="destination" value="/ProductOverview.jsp"/>
    <input required="required" type="text" name="productId" placeholder="type ID of product"/>
    <input type="submit" value="ok"/>
    <br>
    <br>
</form>
<br>
<br>
<h2><%="Warehouse overview"%>
</h2>
<form action="${pageContext.request.contextPath}/control" method="post">
    <input type="date" name="end" id="endDate">
    <input type="hidden" name="destination" value="/Warehouse.jsp"/>
    <input type="hidden" name="command" value="FIND_ALL_PRODUCTS_IN_CURRENT_DATE">
    <input type="submit" value="ok">
</form>
</body>
</html>