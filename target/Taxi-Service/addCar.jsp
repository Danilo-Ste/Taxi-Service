<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="resources"/>

<html>
<head>
  <title>Taxi service <fmt:message key="create.order"/></title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <script src="JavaScript/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="fragments/mainMenu.jsp"/>

<jsp:include page="fragments/menuChoice.jsp"/>

<div class="col-lg-5 mx-auto p-4 py-md-5">
  <tags:header value="add.car"/>
  <form method="POST" action="controller">
    <input type="hidden" name="action" value="add-car">

    <div>
      <label class="form-label fs-5" for="capacity"><fmt:message key="capacity"/>*: </label>
      <input class="form-control" name="capacity" id="capacity"
             required value="${requestScope.car.capacity}">
      <tags:contains error="${requestScope.error}" value="capacity"/><br>
    </div>
    <div>
      <label class="form-label fs-5" for="address"><fmt:message key="address"/>*: </label>
      <input class="form-control" name="address" id="address"
             required value="${requestScope.car.address}">
      <tags:contains error="${requestScope.error}" value="address"/><br>
    </div>

    <div>
      <label class="form-label fs-5" for="category_id"><fmt:message key="category"/>*:</label>
      <select class="form-control" name="category_id" id="category_id" required value="${requestScope.car.category}">
        <option value="2">COMMON</option>
        <option value="1">LUX</option>

      </select>
      <tags:contains error="${requestScope.error}" value="category"/><br>
    </div>
    <div>
      <label class="form-label fs-5" for="state_id"><fmt:message key="state"/>*:</label>
      <select class="form-control" name="state_id" id="state_id" required value="${requestScope.car.state}">
        <option value="1">AVAILABLE</option>
        <option value="3">INACTIVE</option>

      </select>
      <tags:contains error="${requestScope.error}" value="category"/><br>
    </div>
    <button type="submit" class="btn btn-dark mt-4 mb-4"><fmt:message key="submit"/></button>

  </form>
</div>

</body>
</html>
