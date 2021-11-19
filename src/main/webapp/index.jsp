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
<br>
<br>
<h2><%="Need overview"%>
</h2>
<form action="${pageContext.request.contextPath}/control" method="post">
    <input type="hidden" name="destination" value="/NeedOverview.jsp"/>
    <input type="hidden" name="command" value="FIND_ALL_NEED">
    <input type="submit" value="Show all need">
</form>
<br>
<form action="${pageContext.request.contextPath}/control" method="post">
    <input type="hidden" name="destination" value="/NeedOverview.jsp"/>
    <input type="hidden" name="command" value="FIND_NEED_FOR_MONTH">
    <input type="date" name="date">
    <input type="submit" value="Show need">
</form>
<br>
<br>
<h2><%="Bookkeeping overview"%>
</h2>
<form action="${pageContext.request.contextPath}/control" method="post" name="createEntry">
    <input type="hidden" name="destination" value="/Answer.jsp"/>
    <input type="hidden" name="command" value="ADD_ARCHIVE_ENTRY">
    <input type="date" name="date">
    <input type="submit" value="'Close' month">
</form>
<br>
<br>
<form action="${pageContext.request.contextPath}/control" method="post" name="findInMonth">
    <input type="hidden" name="destination" value="/Archive.jsp"/>
    <input type="hidden" name="command" value="FIND_ARCHIVE_ENTRY_BY_MONTH">
    <input type="date" name="date">
    <input type="submit" value="View archive entry">
</form>
<br>
<br>
<form action="${pageContext.request.contextPath}/control" method="post" name="findAll">
    <input type="hidden" name="destination" value="/Archive.jsp"/>
    <input type="hidden" name="command" value="FIND_ALL_ARCHIVE">
    <input type="submit" value="Show all archive">
</form>

</body>
</html>