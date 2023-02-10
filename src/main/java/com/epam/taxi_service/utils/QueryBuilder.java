package com.epam.taxi_service.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import static com.epam.taxi_service.controller.actions.implementation.ParameterValues.*;

public abstract class QueryBuilder {
    private final List<String> filters = new ArrayList<>();
    private String sortField;
    private String order = ASCENDING_ORDER;
    private int offset = 0;
    private int records = 5;

    /**
     * @param sortField by default.
     */
    protected QueryBuilder(String sortField) {
        this.sortField = sortField;
    }


    public QueryBuilder setUserIdFilter(long userIdFilter) {
        filters.add("user_id=" + userIdFilter);
        return this;
    }


    public QueryBuilder setDateFilter(String dateFilter) {
        if (dateFilter != null && dateFilter.equals(PASSED)) {
            filters.add("date < now()");
        } else if (dateFilter != null && dateFilter.equals(UPCOMING)) {
            filters.add("date > now()");
        }
        return this;
    }


    public QueryBuilder setRoleFilter(String roleFilter) {
        if (roleFilter != null && isPositiveInt(roleFilter)) {
            filters.add("role_id=" + roleFilter);
        }
        return this;
    }
    public QueryBuilder setCapacityFilter(String capacityFilter) {
        if (capacityFilter != null && isPositiveInt(capacityFilter)) {
            filters.add("capacity>=" + capacityFilter);
        }
        return this;
    }

    public QueryBuilder setCategory_IdFilter(String Category_IdFilter) {
        if (Category_IdFilter != null && isPositiveInt(Category_IdFilter)) {
            filters.add("category_id=" + Category_IdFilter);
        }
        return this;
    }

    public QueryBuilder setStateFilter(String StateFilter) {
        if (StateFilter != null && isPositiveInt(StateFilter)) {
            filters.add("State_id=" + StateFilter);
        }
        return this;
    }


    public QueryBuilder setSortField(String sortField) {
        if (sortField != null) {
            this.sortField = checkSortField(sortField);
        }
        return this;
    }


    public QueryBuilder setOrder(String order) {
        if (order != null && order.equalsIgnoreCase(DESCENDING_ORDER)) {
            this.order = DESCENDING_ORDER;
        }
        return this;
    }


    public QueryBuilder setLimits(String offset, String records) {
        if (offset != null && isPositiveInt(offset)) {
            this.offset = Integer.parseInt(offset);
        }
        if (records != null && isPositiveInt(records)) {
            this.records = Integer.parseInt(records);
        }
        return this;
    }


    public String getQuery() {
        return getFilterQuery() + getGroupByQuery() + getSortQuery() + getLimitQuery();
    }


    public String getRecordQuery() {
        return getFilterQuery();
    }

    private String getFilterQuery() {
        StringJoiner stringJoiner = new StringJoiner(" AND ", " WHERE ", " ");
        stringJoiner.setEmptyValue("");
        filters.forEach(stringJoiner::add);
        return stringJoiner.toString();
    }


    protected abstract String getGroupByQuery();

    private String getSortQuery() {
        return " ORDER BY " + sortField + " " + order;
    }

    private String getLimitQuery() {
        return " LIMIT " + offset + ", " + records;
    }


    protected abstract String checkSortField(String sortField);

    private boolean isPositiveInt(String intString) {
        try {
            int i = Integer.parseInt(intString);
            if (i < 0) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
