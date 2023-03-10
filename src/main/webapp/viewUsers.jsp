<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="resources"/>

<!DOCTYPE html>
<html lang="${sessionScope.locale}">

<head>
    <title>Taxi service <fmt:message key="view.users"/></title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="JavaScript/bootstrap.min.js"></script>
</head>

<body>

<jsp:include page="fragments/mainMenu.jsp"/>

<jsp:include page="fragments/menuChoice.jsp"/>

<div class="col-lg-7 mx-auto p-4 py-md-5">
    <tags:header value="users"/>

    <form method="GET" action="controller">
        <input type="hidden" name="action" value="search-user">

        <div>
            <tags:notEmptyMessage value="${requestScope.message}"/><br>
            <label class="form-label fs-5" for="email"><fmt:message key="search.user"/></label>
            <input class="form-control" type="email" name="email" id="email"
                   pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$" required>
            <tags:notEmptyError value="${requestScope.error}"/><br>
        </div>

        <button type="submit" class="btn btn-dark mt-2 mb-4"><fmt:message key="search"/></button>
    </form>

    <div class="row">
        <form class="col-11" method="GET" action="controller">
            <input type="hidden" name="action" value="view-users">
            <input type="hidden" name="offset" value="0">

            <label>
                <select name="role" class="form-select mt-2" onchange='submit();'>
                    <option><fmt:message key="select.role"/></option>
                    <option value="2" ${param.role eq "2" ? "selected" : ""}><fmt:message key="CLIENT"/></option>
                    <option value="1" ${param.role eq "1" ? "selected" : ""}><fmt:message key="ADMIN"/></option>
                </select>
            </label>&nbsp&nbsp&nbsp&nbsp&nbsp

            <label for="records"></label>
            <input class="col-2" type="number" min="1" name="records" id="records"
                   value="${not empty requestScope.records ? requestScope.records : "6"}">&nbsp&nbsp&nbsp
            <button type="submit" class="btn btn-dark mt-2 mb-3"><fmt:message key="submit"/></button>
        </form>

        <form class="col-1 mt-3" method="GET" action="controller">
            <input type="hidden" name="role" value="${param.role}">
            <input type="hidden" name="sort" value="${param.sort}">
            <input type="hidden" name="order" value="${param.order}">

        </form>
    </div>

    <div class="bd-example-snippet bd-code-snippet">
        <div class="bd-example">
            <table class="table table-bordered" aria-label="user-table">
                <thead>
                <tr>
                    <c:set var="base" value="controller?action=view-users&role=${param.role}&"/>
                    <c:set var="byId" value="sort=id&"/>
                    <c:set var="byEmail" value="sort=email&"/>
                    <c:set var="byName" value="sort=name&"/>
                    <c:set var="bySurname" value="sort=surname&"/>
                    <c:set var="idOrder" value="order=${empty param.sort ? 'DESC' :
                            param.sort ne 'id' || empty param.order || param.order eq 'DESC' ? 'ASC' : 'DESC'}"/>
                    <c:set var="emailOrder"
                           value="order=${param.sort ne 'email' || param.order eq 'DESC' ? 'ASC' : 'DESC'}"/>
                    <c:set var="nameOrder"
                           value="order=${param.sort ne 'name' || param.order eq 'DESC' ? 'ASC' : 'DESC'}"/>
                    <c:set var="surnameOrder"
                           value="order=${param.sort ne 'surname' || param.order eq 'DESC' ? 'ASC' : 'DESC'}"/>
                    <c:set var="limits" value="&offset=0&records=${param.records}"/>

                    <th scope="col">
                        <fmt:message key="id"/>
                        <a href="${base.concat(byId).concat(idOrder).concat(limits)}">
                            <i class="bi bi-arrow-down-up link-dark"></i>
                        </a>
                    </th>
                    <th scope="col">
                        <fmt:message key="email"/>
                        <a href="${base.concat(byEmail).concat(emailOrder).concat(limits)}">
                            <i class="bi bi-arrow-down-up link-dark"></i>
                        </a>
                    </th>
                    <th scope="col">
                        <fmt:message key="name"/>
                        <a href="${base.concat(byName).concat(nameOrder).concat(limits)}">
                            <i class="bi bi-arrow-down-up link-dark"></i>
                        </a>
                    </th>
                    <th scope="col">
                        <fmt:message key="surname"/>
                        <a href="${base.concat(bySurname).concat(surnameOrder).concat(limits)}">
                            <i class="bi bi-arrow-down-up link-dark"></i>
                        </a>
                    </th>
                    <th scope="col"><fmt:message key="role"/></th>
                    <th scope="col"><fmt:message key="action"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="user" items="${requestScope.users}">
                    <tr>
                        <td><c:out value="${user.id}"/></td>
                        <td><c:out value="${user.email}"/></td>
                        <td><c:out value="${user.name}"/></td>
                        <td><c:out value="${user.surname}"/></td>
                        <td><fmt:message key="${user.role}"/></td>
                        <td>
                            <a class="link-dark" href=controller?action=search-user&email=${user.email}>
                                <fmt:message key="edit"/>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <c:set var="href" value="controller?action=view-users&role=${param.role}&sort=${param.sort}&order=${param.order}&"
           scope="request"/>

    <jsp:include page="pagination.jsp"/>
</div>

<jsp:include page="fragments/footer.jsp"/>

</body>
</html>