<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${empty sessionScope.language}"><fmt:setLocale value="ru_RU"/></c:if>
<c:if test="${not empty sessionScope.language}"><fmt:setLocale value="${sessionScope.language}"/></c:if>
<fmt:setBundle basename="lang.text" scope="session" var="bundle"/>
<html>
<head>
    <title>Bookkeeping Home Page</title>
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
<div class="container-fluid col-md-3">
    <br>
    <form role="form" action="<c:out value="${urlServlet}"/>" method="post">
        <input type="hidden" name="command" value="ADD_ARCHIVE_ENTRY">
        <label for="close"><fmt:message key="bookkeeping.general.close" bundle="${bundle}"/></label>
        <div class="input-group">
            <input class="form-control row-cols-sm-2" type="date" name="date">
        </div>
        <div class="input-group">
            <input class="form-control row-cols-sm-2 btn-primary" type="submit"
                   value="<fmt:message key="bookkeeping.close" bundle="${bundle}"/>" id="close">
        </div>
    </form>
    <br>
    <hr>
    <form role="form" action="<c:out value="${urlServlet}"/>" method="post">
        <input type="hidden" name="command" value="FIND_ARCHIVE_ENTRY_BY_MONTH">
        <label for="archive"><fmt:message key="bookkeeping.general.close" bundle="${bundle}"/></label>
        <div class="input-group">
            <input class="form-control row-cols-sm-2" type="date" name="date">
        </div>
        <div class="input-group">
            <input class="form-control row-cols-sm-2 btn-primary" type="submit"
                   value="<fmt:message key="bookkeeping.archive.view" bundle="${bundle}"/>" id="archive">
        </div>
    </form>

    <%-- message success--%>
    <c:if test="${not empty requestScope.message}">
        <div class="alert alert-success" role="alert">
            <fmt:message key="${requestScope.message}" bundle="${bundle}"/>
        </div>
    </c:if>
    <%-- message--%>
    <%-- message error--%>
    <c:if test="${not empty requestScope.error}">
        <div class="alert alert-danger" role="alert">
            <fmt:message key="${requestScope.error}" bundle="${bundle}"/>
        </div>
    </c:if>
    <%-- message--%>

</div>
</body>
</html>
