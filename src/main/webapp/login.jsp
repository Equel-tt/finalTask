<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="en-US"/>
<fmt:setBundle basename="lang.text" scope="session" var="bundle"/>
<!doctype html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=0">
    <title>SignIn</title>

    <!-- URL -->
    <c:url value="/control" var="urlServlet"/>
    <c:url value="/css/signin.css" var="urlSignIn"/>
    <c:url value="/img/icon01.ico" var="urlIcon"/>

    <!-- Bootstrap core CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>

    <!-- Custom style -->
    <link href="css/signin.css" rel="stylesheet">
</head>

<body class="text-center">

<main class="form-signin">
    <form action="<c:out value="${urlServlet}"/>" method="post">
        <input type="hidden" name="command" value="LOGIN">
        <img class="mb-4" src="<c:out value="${urlIcon}"/>" alt="?..." width="30%" height="30%">
        <h1 class="h3 mb-3 fw-normal"><fmt:message key="login.message" bundle="${bundle}"/></h1>

        <div class="form-floating">
            <input type="text" name="login" class="form-control" id="floatingInput" placeholder="Login">
            <label for="floatingInput"><fmt:message key="login.login" bundle="${bundle}"/></label>
        </div>
        <div class="form-floating">
            <input type="password" name="password" class="form-control" id="floatingPassword" placeholder="Password">
            <label for="floatingPassword"><fmt:message key="login.password" bundle="${bundle}"/></label>
        </div>

        <button class="w-100 btn btn-lg btn-primary" type="submit"><fmt:message key="login.signin"
                                                                                bundle="${bundle}"/></button>
        <p class="mt-5 mb-3 text-muted">&copy; 2021</p>
    </form>
    <br>
    <div class="btn-group">
        <form action="<c:out value="${urlServlet}"/>" method="post">
            <label class="btn btn-outline-success">
                <input type="hidden" name="command" value="CHANGE_LANGUAGE">
                <input type="hidden" name="language" value="ru_RU">
                <input type="submit" class="btn" value="RU">
            </label>
            <label class="btn btn-outline-success">
                <input type="hidden" name="command" value="CHANGE_LANGUAGE">
                <input type="hidden" name="language" value="en_US">
                <input type="submit" class="btn" value="US">
            </label>
            <label class="btn btn-outline-success">
                <input type="hidden" name="command" value="CHANGE_LANGUAGE">
                <input type="hidden" name="language" value="be_BY">
                <input type="submit" class="btn" value="BY">
            </label>
        </form>
    </div>
</main>

</body>

</html>