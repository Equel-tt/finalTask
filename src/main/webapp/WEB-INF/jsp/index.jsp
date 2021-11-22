<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Warehouse</title>
    <!-- Bootstrap core CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>

</head>

<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
</h2>
<h2><%="Find product by ID"%>
</h2>
<form action="${pageContext.request.contextPath}/control" method="post">
    <input type="hidden" name="command" value="FIND_PRODUCT_BY_ID"/>
    <input required="required" type="text" name="productId" placeholder="type ID of product"/>
    <input type="submit" value="ok"/>
</form>
<br>
<br>
<h2><%="Warehouse overview"%>
</h2>

<br>
<br>
<h2><%="Need overview"%>
</h2>
<form action="${pageContext.request.contextPath}/control" method="post">
    <input type="hidden" name="command" value="FIND_ALL_NEED">
    <input type="submit" value="Show all need">
</form>
<br>
<h2><%="Bookkeeping overview"%>
</h2>
<form action="${pageContext.request.contextPath}/control" method="post" name="createEntry">
    <input type="hidden" name="command" value="ADD_ARCHIVE_ENTRY">
    <input type="date" name="date">
    <input type="submit" value="'Close' month">
</form>
<br>
<br>
<form action="${pageContext.request.contextPath}/control" method="post" name="findInMonth">
    <input type="hidden" name="command" value="FIND_ARCHIVE_ENTRY_BY_MONTH">
    <input type="date" name="date">
    <input type="submit" value="View archive entry">
</form>
<br>
<br>
<form action="${pageContext.request.contextPath}/control" method="post" name="findAll">
    <input type="hidden" name="command" value="FIND_ALL_ARCHIVE">
    <input type="submit" value="Show all archive">
</form>

</body>
</html>