<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="resources"/>

<!DOCTYPE html>
<html lang="${sessionScope.locale}">

<head>
    <title>Taxi service <fmt:message key="view.user"/></title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="JavaScript/bootstrap.min.js"></script>
</head>

<body>

<jsp:include page="fragments/mainMenu.jsp"/>

<jsp:include page="fragments/menuChoice.jsp"/>

<div class="col-lg-5 mx-auto p-4 py-md-5">
    <tags:header value="view.user"/>


    <main>
        <p class="fs-5"><fmt:message key="email"/>: ${requestScope.get('email')}</p>
        <p class="fs-5"><fmt:message key="name"/>: ${requestScope.get('name')}</p>
        <p class="fs-5"><fmt:message key="surname"/>: ${requestScope.get('surname')}</p>
        <p class="fs-5"><fmt:message key="role"/>:${requestScope.get('role')}</p>
    </main>

    <form method="POST" action="controller">
        <input type="hidden" name="action" value="set-role">
        <input type="hidden" name="email" value=${requestScope.get('email')}>
        <label>
            <select name="role" class="form-select mt-2">
                <option value="CLIENT" ${requestScope.get('role') eq 'CLIENT' ? 'selected' : ''}>
                    <fmt:message key="CLIENT"/>
                </option>
                <option value="ADMIN" ${requestScope.get('role') eq 'ADMIN' ? 'selected' : ''}>
                    <fmt:message key="ADMIN"/>
                </option>
            </select>
        </label>
        <button type="submit" class="btn btn-dark mt-3 mb-4"><fmt:message key="set.role"/></button>
    </form>

    <button class="btn btn-dark mt-4 mb-4" data-bs-toggle="modal" data-bs-target="#exampleModalDefault">
        <fmt:message key="delete"/>
    </button>
</div>

<jsp:include page="fragments/footer.jsp"/>

<jsp:include page="fragments/deleteUserModal.jsp"/>

</body>
</html>