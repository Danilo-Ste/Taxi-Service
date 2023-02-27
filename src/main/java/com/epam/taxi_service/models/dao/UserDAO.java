package com.epam.taxi_service.models.dao;

import com.epam.taxi_service.Exception.DAOException;
import com.epam.taxi_service.models.entities.Role;
import com.epam.taxi_service.models.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO extends EntityDAO<User> {
    Optional<User> getById(long id) throws DAOException;

    Optional<User> getByEmail(String email) throws DAOException;

    List<User> getSorted(String query) throws DAOException;


    void updatePassword(User user) throws DAOException;

    int getNumberOfRecords(String filter) throws DAOException;


    void setUserRole(String userEmail, Role role) throws DAOException;

}
