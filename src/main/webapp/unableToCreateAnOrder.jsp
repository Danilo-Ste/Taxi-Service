<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="resources"/>
<html>
<head>
  <title>Taxi service <fmt:message key="unable.to.create.an.order"/></title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <script src="JavaScript/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="fragments/mainMenu.jsp"/>

<jsp:include page="fragments/menuChoice.jsp"/>
<div class="col-lg-7 mx-auto p-4 py-md-5">
    <tags:header value="unable.to.create.an.order"/>

    <main>
        <p class="fs-5 col-md-12"></p><br>
    </main>
</div>
<div class="col-lg-5 mx-auto p-4 py-md-5">
    <form class="col-11" method="GET" action="controller">
        <input type="hidden" name="action" value="unable-to-create-an-order">
        <div>
            <label class="form-label fs-5" for="selectAnOrderOption"><fmt:message key="selectAnOrderOption"/>*:</label>
            <select class="form-control" name="selectAnOrderOption" id="selectAnOrderOption" required value="${requestScope.selectAnOrderOption}">
                <option value="correctCapacity" ><fmt:message key="correct.capacity"/></option>
                <option value="correctCategory" ><fmt:message key="correct.category"/></option>
                <option value="cancel" ><fmt:message key="cancel"/></option>

            </select>
            <tags:contains error="${requestScope.error}" value="selectAnOrderOption"/><br>
        </div>
        <button type="submit" class="btn btn-dark mt-4 mb-4"><fmt:message key="submit"/></button>
    </form>
</div>

</body>
</html>
