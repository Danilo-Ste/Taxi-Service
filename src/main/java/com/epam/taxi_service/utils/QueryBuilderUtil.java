package com.epam.taxi_service.utils;

public class QueryBuilderUtil {
    public static QueryBuilder userQueryBuilder() {
        return new UserQueryBuilder();
    }


    public static QueryBuilder orderQueryBuilder() {
        return new OrderQueryBuilder();
    }


    public static QueryBuilder visitorEventQueryBuilder() {
        return new VisitorEventQueryBuilder();
    }

    private QueryBuilderUtil() {}
}
