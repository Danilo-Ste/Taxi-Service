package com.epam.taxi_service.controller.actions.implementation.admin;

import com.epam.taxi_service.Exception.ServiceException;
import com.epam.taxi_service.controller.actions.Action;
import com.epam.taxi_service.controller.context.AppContext;
import com.epam.taxi_service.models.services.UserService;
import com.epam.taxi_service.utils.QueryBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static com.epam.taxi_service.controller.actions.implementation.Page.VIEW_USERS_PAGE;
import static com.epam.taxi_service.controller.actions.implementation.Parameters.*;
import static com.epam.taxi_service.utils.PaginationUtil.paginate;
import static com.epam.taxi_service.utils.QueryBuilderUtil.userQueryBuilder;

public class ViewUsersAction implements Action {
    private final UserService userService;

    public ViewUsersAction(AppContext appContext) {
        userService = appContext.getUserService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        QueryBuilder queryBuilder = getQueryBuilder(request);
        request.setAttribute(USERS, userService.getSortedUsers(queryBuilder.getQuery()));
        int numberOfRecords = userService.getNumberOfRecords(queryBuilder.getRecordQuery());
        paginate(numberOfRecords, request);
        return VIEW_USERS_PAGE;
    }

    private QueryBuilder getQueryBuilder(HttpServletRequest request) {
        return userQueryBuilder()
                .setRoleFilter(request.getParameter(ROLE))
                .setSortField(request.getParameter(SORT))
                .setOrder(request.getParameter(ORDER))
                .setLimits(request.getParameter(OFFSET), request.getParameter(RECORDS));
    }
}
