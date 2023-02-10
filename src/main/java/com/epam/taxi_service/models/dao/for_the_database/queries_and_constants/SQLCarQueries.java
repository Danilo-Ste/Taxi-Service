package com.epam.taxi_service.models.dao.for_the_database.queries_and_constants;

public class SQLCarQueries {
    public static final String ADD_CAR = "INSERT INTO car (address,category_id,capacity,State_id) VALUES (?,?,?,?)";
    public static final String GET_CAR_BY_ID = "SELECT * FROM car WHERE id=?";
    public static final String GET_CARS = "SELECT * FROM car";
    public static final String GET_SORTED_CAR = "SELECT * FROM car %s";
    public static final String UPDATE_CAR = "UPDATE car SET address=? WHERE id=?";
    public static final String SET_STATE = "UPDATE car SET state_id=? WHERE id=?";
    public static final String DELETE_CAR = "DELETE FROM car WHERE id=?";
}
