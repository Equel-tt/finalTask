<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${empty sessionScope.language}"><fmt:setLocale value="ru_RU"/></c:if>
<c:if test="${not empty sessionScope.language}"><fmt:setLocale value="${sessionScope.language}"/></c:if>
<fmt:setBundle basename="lang.text" scope="session" var="bundle"/>
<html>
<head>
    <title>Title</title>
    <!-- Bootstrap core CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <!-- URL -->
    <c:url value="/control" var="urlServlet"/>
    <c:url value="/css/header.css" var="urlHeader"/>
</head>
<body>
<div class="container">
    <header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">

        <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
            <li><a href="#" class="nav-link px-2 link-dark">Home</a></li>
            <%--      <li><a href="#" class="nav-link px-2 link-dark">Pricing</a></li>--%>
            <%--      <li><a href="#" class="nav-link px-2 link-dark">FAQs</a></li>--%>
            <%--      <li><a href="#" class="nav-link px-2 link-dark">About</a></li>--%>
        </ul>
        <div class="btn-group btn-group-sm">
            <form action="<c:out value="${urlServlet}"/>" method="post">
                <label class="btn btn-outline-success">
                    <input type="hidden" name="command" value="CHANGE_LANGUAGE">
                    <input type="hidden" name="language" value="ru_RU">
                    <input type="submit" class="btn" value="RU">
                </label>
            </form>
            <form action="<c:out value="${urlServlet}"/>" method="post">
                <label class="btn btn-outline-success">
                    <input type="hidden" name="command" value="CHANGE_LANGUAGE">
                    <input type="hidden" name="language" value="en_US">
                    <input type="submit" class="btn" value="US">
                </label>
            </form>
            <form action="<c:out value="${urlServlet}"/>" method="post">
                <label class="btn btn-outline-success">
                    <input type="hidden" name="command" value="CHANGE_LANGUAGE">
                    <input type="hidden" name="language" value="be_BE">
                    <input type="submit" class="btn" value="BY">
                </label>
            </form>
        </div>

        <%--  <div class="col-md-8 text-end pull-right">--%>
        <%--    <form action="<c:out value="${urlServlet}"/>" method="post">--%>
        <%--      <input type="hidden" name="command" value="LANG">--%>
        <%--      <input type="submit" class="btn btn-secondary rounded-2" value="Lang">--%>
        <%--    </form>--%>
        <%--  </div>--%>
        <div class="col-md-2 text-end pull-right">
            <form action="<c:out value="${urlServlet}"/>" method="post">
                <input type="hidden" name="command" value="LOGOUT">
                <input type="submit" class="btn btn-primary" value="<fmt:message key="logout" bundle="${bundle}"/>">
            </form>
        </div>
    </header>
</div>
</body>
</html>
