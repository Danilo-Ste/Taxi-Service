<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="resources"/>


<!DOCTYPE html>
<html lang="${sessionScope.locale}">
<head>
    <title>Taxi service <fmt:message key="create.order"/></title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/my.css">
    <script src="JavaScript/bootstrap.min.js"></script>
</head>
<body>

<jsp:include page="fragments/mainMenu.jsp"/>

<jsp:include page="fragments/menuChoice.jsp"/>

<div class="col-lg-5 mx-auto p-4 py-md-5">
    <tags:header value="create.order"/>
    <form method="POST" action="controller">
        <div>
            <label class="form-label fs-5" for="capacity"><fmt:message key="capacity"/>*: </label>
            <input class="form-control" name="capacity" id="capacity"
                   required value="${requestScope.event.title}"
            <tags:contains error="${requestScope.error}" value="order"/><br>
        </div>
        <div>
            <label class="form-label fs-5" for="category"><fmt:message key="capacity"/>*:</label>
            <input class="form-control" name="category"id="category">
        </div>

    </form>
</div>

</body>
</html>
