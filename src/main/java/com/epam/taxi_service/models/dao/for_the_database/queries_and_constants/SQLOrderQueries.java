package com.epam.taxi_service.models.dao.for_the_database.queries_and_constants;

public class SQLOrderQueries {
    private static final String UTIL_GET_ORDER = "SELECT order.id AS id, addressOfDeparture, addressOfDestination,user_id,car_id";
    private static final String GROUP_BY = " GROUP BY order.id ";

    /** Queries to use in DAO methods */
    public static final String ADD_ORDER = "INSERT INTO order (addressOfDeparture,addressOfDestination,user_id,car_id) VALUES (?,?,?,?)";
    public static final String GET_ORDER_BY_ID =  UTIL_GET_ORDER +"WHERE order.id=?" + GROUP_BY;
    public static final String GET_ORDERS =  UTIL_GET_ORDER + GROUP_BY;
    public static final String GET_SORTED = UTIL_GET_ORDER + " %s";

    public static final String EDIT_ORDER = "UPDATE order SET addressOfDeparture=?, addressOfDestination=?, car_id=? WHERE id=?";

    /** Will delete records in 3 tables: event, all reports from this event and user's registrations*/
    public static final String DELETE_ORDER = "DELETE order WHERE order.id=?";
}
