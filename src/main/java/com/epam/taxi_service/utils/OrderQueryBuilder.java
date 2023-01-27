package com.epam.taxi_service.utils;

import java.util.HashSet;
import java.util.Set;

import static com.epam.taxi_service.controller.actions.implementation.Parameters.*;

public class OrderQueryBuilder extends QueryBuilder{
    private static final String ORDER_DOT_ID = "order.id";
    /** Contains set of allowed sort fields */
    private static final Set<String> ORDER_SORT_FIELDS_SET = new HashSet<>();

    static {
        ORDER_SORT_FIELDS_SET.add(ORDER_DOT_ID);
        ORDER_SORT_FIELDS_SET.add(ADDRESS_OF_DEPARTURE);
        ORDER_SORT_FIELDS_SET.add(ADDRESS_OF_DESTINATION);
    }

    /**
     * set id as default sort field
     */
    public OrderQueryBuilder() {
        super(ORDER_DOT_ID);
    }

    /**
     * @return concrete order by
     */
    @Override
    protected String getGroupByQuery() {
        return " GROUP BY " + ORDER_DOT_ID + " ";
    }

    @Override
    protected String checkSortField(String sortField) {
        if (ORDER_SORT_FIELDS_SET.contains(sortField.toLowerCase())) {
            return sortField;
        }
        return ORDER_DOT_ID;
    }

    @Override
    public OrderQueryBuilder setRoleFilter(String roleFilter) {
        throw new UnsupportedOperationException();
    }
}
