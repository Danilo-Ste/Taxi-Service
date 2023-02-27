<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="resources"/>

<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-warning">
    <div class="container-fluid">
        <a class="navbar-brand" href="index.jsp"><span class="mb-0 h4 text-dark">Taxi service</span></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse"
                aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse " id="navbarCollapse">
            <ul class="navbar-nav me-auto mx-4 mb-4 mb-md-0">
                <li class="nav-item">
                    <a class="nav-link text-dark" aria-current="page" href="index.jsp"><fmt:message key="main"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-dark" href="about.jsp"><fmt:message key="about"/></a>
                </li>
                <c:if test="${not empty sessionScope.loggedUser}">
                    <li class="nav-item">
                        <a class="nav-link text-dark"  href="profile.jsp"><fmt:message key="profile"/></a>
                    </li>
                </c:if>
                <c:if test="${not empty sessionScope.loggedUser}">
                    <li class="nav-item">
                        <a class="nav-link text-dark"  href="createOrder.jsp"><fmt:message key="create.order"/></a>
                    </li>
                </c:if>
            </ul>
            <ul class="navbar-nav ms-auto mx-4 mb-4 mb-md-0">
                <c:choose>
                    <c:when test="${sessionScope.loggedUser eq null}">
                        <li class="nav-item">
                            <a class="nav-link text-dark"  href="signIn.jsp"><fmt:message key="sign.in"/></a>
                        </li>
                        <li class="nav-item">
                                <a class="nav-link text-dark"  href="signUp.jsp"><fmt:message key="sign.up"/></a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item">
                            <a class="nav-link text-dark"  href="controller?action=sign-out"><fmt:message key="sign.out"/></a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
            <form method="POST" class="d-flex mt-1" >
                <label>
                    <select name="locale" onchange='submit();'>
                        <option value="en" ${sessionScope.locale eq 'en' ? 'selected' : ''}>
                            <fmt:message key="en"/>
                        </option>
                        <option value="uk_UA" ${sessionScope.locale eq 'uk_UA' ? 'selected' : ''}>
                            <fmt:message key="ua"/>
                        </option>
                    </select>
                </label>
            </form>
        </div>
    </div>
</nav>
<br><br>

<script src="js/activation.js"></script>