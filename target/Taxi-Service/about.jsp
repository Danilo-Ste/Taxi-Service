<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="resources"/>

<!DOCTYPE html>
<html lang="${sessionScope.locale}">

<head>
    <title>Taxi service <fmt:message key="about"/></title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="JavaScript/bootstrap.min.js"></script>
</head>

<body>

<jsp:include page="fragments/mainMenu.jsp"/>

<jsp:include page="fragments/menuChoice.jsp"/>

<div class="col-lg-7 mx-auto p-4 py-md-5">
    <tags:header value="about"/>

    <main>
        <p class="fs-5 col-md-12">Need to get to your destination quickly and cheaply?
            Order our taxi and get a discount depending on the price of the trip.
            We offer a large selection of taxis of various categories.
            We are a young company, but we provide excellent transportation conditions and guarantee comfort.</p><br>

        <p class="fs-5 col-md-8">address: Medovaya Pechery street, 39, Lviv, Lviv region</p>
        <p class="fs-5 col-md-8">phone number: +380688810756</p>
    </main>
</div>

<jsp:include page="fragments/footer.jsp"/>

</body>
</html>