package com.epam.taxi_service.models.services;

import com.epam.taxi_service.Exception.ServiceException;
import com.epam.taxi_service.dto.UserDTO;
import com.epam.taxi_service.models.entities.Role;

import java.util.List;

public interface UserService extends Service<UserDTO>{

    void add(UserDTO userDTO, String password, String confirmPassword) throws ServiceException;


    UserDTO signIn(String email, String password) throws ServiceException;


    UserDTO getByEmail(String email) throws ServiceException;


    List<UserDTO> getSortedUsers(String query) throws ServiceException;


    List<UserDTO> getParticipants(String eventId, Role role) throws ServiceException;


    int getNumberOfRecords(String filter) throws ServiceException;


    List<UserDTO> getSpeakers() throws ServiceException;


    List<UserDTO> getModerators() throws ServiceException;


    void changePassword(long userId, String password, String newPass, String confirmPass) throws ServiceException;


    void setRole(String email, int roleId) throws ServiceException;

}
