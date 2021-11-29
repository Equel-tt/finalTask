<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${empty sessionScope.language}"><fmt:setLocale value="ru_RU"/></c:if>
<c:if test="${not empty sessionScope.language}"><fmt:setLocale value="${sessionScope.language}"/></c:if>
<fmt:setBundle basename="lang.text" scope="session" var="bundle"/>
<html>
<head>
    <jsp:include page="/WEB-INF/jsp/header.jsp"/>
    <title>Title</title>
    <!-- URL -->
    <c:url value="/control" var="urlServlet"/>
    <jsp:useBean id="result" scope="request" type="java.util.List"/>

    <!-- Custom CSS and JS -->
    <link rel="stylesheet" href="//code.jquery.com/ui/1.13.0/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
    <script>
        $(function () {
            const availableTags = [];
            <c:forEach items="${result}" var="productName">
            availableTags.push("${productName}");
            </c:forEach>
            $("#tags").autocomplete({source: availableTags});
        });
    </script>

    <!-- Bootstrap core CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>

</head>
<body>
<div class="container col-md-3">
    <div class="ui-widget">
        <form role="form" action="<c:out value="${urlServlet}"/>" method="post" id="fillForm">
            <input type="hidden" name="command" value="ADD_ARRIVAL_ENTRY">
            <label for="fillForm"><fmt:message key="warehouse.form.fill" bundle="${bundle}"/></label>
            <div class="input-group">
                <input class="form-control row-cols-sm-2" type="text"
                       placeholder="<fmt:message key="placeholder.arrival.document" bundle="${bundle}"/>" name="doc">
            </div>
            <div class="input-group">
                <input class="form-control row-cols-sm-2" type="number"
                       placeholder="<fmt:message key="product.count" bundle="${bundle}"/>" name="count">
            </div>
            <div class="input-group">
                <input class="form-control row-cols-sm-2" type="date" name="date">
            </div>
            <div class="input-group">
                <input class="form-control row-cols-sm-2" id="tags"
                       placeholder="<fmt:message key="placeholder.product.search" bundle="${bundle}"/>" type="text"
                       name="product">
            </div>
            <div class="input-group">
                <input class="form-control row-cols-sm-2" type="number" step="0.01"
                       placeholder="<fmt:message key="placeholder.arrival.price" bundle="${bundle}"/>" name="price">
            </div>
            <div class="input-group">
                <input class="form-control row-cols-sm-2 btn-primary" type="submit"
                       value="<fmt:message key="warehouse.add.create" bundle="${bundle}"/>" id="arrival">
            </div>
        </form>
    </div>
</div>
</body>
</html>


