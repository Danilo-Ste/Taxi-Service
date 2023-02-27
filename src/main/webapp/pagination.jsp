<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<ul class="pagination justify-content-center">
    <c:if test="${requestScope.get('end') > 3}">
        <li class="page-item">
            <a class="page-link link-dark" href="${requestScope.href}offset=0&records=${requestScope.get('records')}">
                1
            </a>
        </li>
        <c:if test="${requestScope.get('end') > 4}">
            <li class="page-item">
                <c:set var="currentOffset" value="${(requestScope.get('start') - 2) * requestScope.get('records')}"/>
                <a class="page-link link-dark"
                   href="${requestScope.href}offset=${currentOffset}&records=${requestScope.get('records')}">
                    ...
                </a>
            </li>
        </c:if>
    </c:if>
    <c:forEach var="page" begin="${requestScope.get('start')}" end="${requestScope.get('end')}">
        <li class="page-item">
            <c:set var="currentOffset" value="${(page - 1) * requestScope.get('records')}"/>
            <a class="page-link ${requestScope.get('currentPage') eq page ? 'dark-active' : 'link-dark'}"
               href="${requestScope.href}offset=${currentOffset}&records=${requestScope.get('records')}">
                    ${page}
            </a>
        </li>
    </c:forEach>
    <c:if test="${requestScope.get('end') < requestScope.get('pages')}">
        <c:if test="${requestScope.get('end') + 1 < requestScope.get('pages')}">
            <li class="page-item">
                <c:set var="currentOffset" value="${(requestScope.get('end')) * requestScope.get('records')}"/>
                <a class="page-link link-dark"
                   href="${requestScope.href}offset=${currentOffset}&records=${requestScope.get('records')}">
                    ...
                </a>
            </li>
        </c:if>
        <li class="page-item">
            <a class="page-link link-dark"
                href="${requestScope.href}offset=${(requestScope.get('pages') - 1) * requestScope.get('records')}&records=${requestScope.get('records')}">
                    ${requestScope.get('pages')}
                </a>
        </li>
    </c:if>
</ul>