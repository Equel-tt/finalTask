<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${empty sessionScope.language}"><fmt:setLocale value="ru_RU"/></c:if>
<c:if test="${not empty sessionScope.language}"><fmt:setLocale value="${sessionScope.language}"/></c:if>
<fmt:setBundle basename="lang.text" scope="session" var="bundle"/>
<html>
<head>
    <title>Supply Home Page</title>
    <!-- URL -->
    <c:url value="/control" var="urlServlet"/>

    <!-- Bootstrap core CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<br><br>
<div class="container-fluid col-md-2">
    <form role="form" action="<c:out value="${urlServlet}"/>" method="post">
        <input type="hidden" name="command" value="SEARCH">
        <label for="search"><fmt:message key="supply.find.product" bundle="${bundle}"/></label>
        <div class="input-group">
            <input class="form-control row-cols-sm-2 btn-primary" type="submit"
                   value="<fmt:message key="all.search" bundle="${bundle}"/>" id="search">
        </div>
    </form>
    <br><br>
    <form role="form" action="<c:out value="${urlServlet}"/>" method="post">
        <input type="hidden" name="command" value="FIND_NEED_FOR_MONTH">
        <label for="need"><fmt:message key="supply.find.need" bundle="${bundle}"/></label>
        <div class="input-group">
            <input class="form-control row-cols-sm-2" type="date" name="date">
        </div>
        <div class="input-group">
            <input class="form-control row-cols-sm-2 btn-primary" type="submit"
                   value="<fmt:message key="show.need" bundle="${bundle}"/>" id="need">
        </div>
    </form>
    <br><br>
    <form role="form" action="<c:out value="${urlServlet}"/>" method="post">
        <input type="hidden" name="command" value="FIND_ALL_PRODUCTS_IN_CURRENT_DATE">
        <label for="warehouse"><fmt:message key="supply.find.wh" bundle="${bundle}"/></label>
        <div class="input-group">
            <input class="form-control row-cols-sm-2" type="date" name="date">
        </div>
        <div class="input-group">
            <input class="form-control row-cols-sm-2 btn-primary" type="submit"
                   value="<fmt:message key="all.show" bundle="${bundle}"/>" id="warehouse">
        </div>
    </form>
    <br><br>
    <form role="form" action="<c:out value="${urlServlet}"/>" method="post">
        <input type="hidden" name="command" value="FIND_DEFICIT">
        <label for="deficit"><fmt:message key="supply.find.deficit" bundle="${bundle}"/></label>
        <div class="input-group">
            <input class="form-control row-cols-sm-2" type="date" name="date">
        </div>
        <div class="input-group">
            <input class="form-control row-cols-sm-2 btn-primary" type="submit"
                   value="<fmt:message key="all.show" bundle="${bundle}"/>" id="deficit">
        </div>
    </form>
    <br>
    <hr>
    <br>
</div>
</body>
</html>
