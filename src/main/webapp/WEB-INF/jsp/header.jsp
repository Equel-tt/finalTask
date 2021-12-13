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

        <ul class="nav col-md-auto mb-2 justify-content-center mb-md-0">
            <li>
                <div>
                    <form action="<c:out value="${urlServlet}"/>" method="post">
                        <input type="hidden" name="command" value="HOME">
                        <input class="form-control row-cols-sm-2 btn-primary" type="submit"
                               value="<fmt:message key="general.home" bundle="${bundle}"/>" id="search">
                    </form>
                </div>
            </li>
        </ul>

        <ul class="nav col-md-auto mb-2 justify-content-center mb-md-0">
            <li>
                <div>
                    <form class="text-body text-md-end">
                        <p class="form-control row-cols-sm-2"><c:out
                                value="${sessionScope.user.name} ${sessionScope.user.surname}"/></p>
                    </form>
                </div>
            </li>
            <%--            <li>--%>
            <%--                <div>--%>
            <%--                    <form class="text-body text-md-end">--%>
            <%--                        <p class="form-control row-cols-sm-2"><c:out value="${sessionScope.user.description}"/></p>--%>
            <%--                    </form>--%>
            <%--                </div>--%>
            <%--            </li>--%>
        </ul>

        <div class="btn-group btn-group-sm col-sm-1">
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
                    <input type="hidden" name="language" value="be_BY">
                    <input type="submit" class="btn" value="BY">
                </label>
            </form>
        </div>

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
