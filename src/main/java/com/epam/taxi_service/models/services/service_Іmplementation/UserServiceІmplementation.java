package com.epam.taxi_service.models.services.service_Іmplementation;

import com.epam.taxi_service.Exception.*;
import com.epam.taxi_service.dto.UserDTO;
import com.epam.taxi_service.models.dao.UserDAO;
import com.epam.taxi_service.models.entities.Role;
import com.epam.taxi_service.models.entities.User;
import com.epam.taxi_service.models.services.UserService;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static com.epam.taxi_service.Exception.message_for_exceptions.MessageForExceptions.ENTER_CORRECT_NAME;
import static com.epam.taxi_service.Exception.message_for_exceptions.MessageForExceptions.ENTER_CORRECT_SURNAME;
import static com.epam.taxi_service.utils.Convertor.convertDTOToUser;
import static com.epam.taxi_service.utils.Convertor.convertUserToDTO;
import static com.epam.taxi_service.utils.PasswordHash.verify;
import static com.epam.taxi_service.utils.Validator.*;
import static jdk.internal.net.http.common.Utils.encode;

@RequiredArgsConstructor
public class UserServiceІmplementation implements UserService {

    private final UserDAO userDAO;

    @Override
    public UserDTO getById(String idString) throws ServiceException {
        UserDTO userDTO;
        long userId = getId(idString);
        try {
            User user = userDAO.getById(userId);
            userDTO = convertUserToDTO(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return userDTO;
    }

    @Override
    public List<UserDTO> getAll() throws ServiceException {
        List<UserDTO> userDTOS = new ArrayList<>();
        try {
            List<User> users = userDAO.getAll();
            users.forEach(user -> userDTOS.add(convertUserToDTO(user)));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return userDTOS;
    }

    @Override
    public void update(UserDTO dto) throws ServiceException {
        validateUser(dto);
        User user = convertDTOToUser(dto);
        try {
            userDAO.update(user);
        } catch (DAOException e) {
            checkExceptionType(e);
        }
    }

    @Override
    public void delete(String idString) throws ServiceException {
        long userId = getId(idString);
        try {
            userDAO.delete(userId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void add(UserDTO userDTO, String password, String confirmPassword) throws ServiceException {
        validateUser(userDTO);
        validatePassword(password);
        checkPasswordMatching(password, confirmPassword);
        User user = convertDTOToUser(userDTO);
        user.setPassword(encode(password));
        try {
            userDAO.add(user);
        } catch (DAOException e) {
            checkExceptionType(e);
        }
    }

    @Override
    public UserDTO signIn(String email, String password) throws ServiceException {
        checkStrings(email, password);
        UserDTO userDTO;
        try {
            User user = userDAO.getByEmail(email).orElseThrow(NoSuchUserException::new);
            verify(user.getPassword(), password);
            userDTO = convertUserToDTO(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return userDTO;
    }
    private void checkExceptionType(DAOException e) throws ServiceException {
        if (e.getMessage().contains("Duplicate")) {
            throw new DuplicateEmailException();
        } else {
            throw new ServiceException(e);
        }
    }

    @Override
    public UserDTO getByEmail(String email) throws ServiceException {
        return null;
    }

    @Override
    public List<UserDTO> getSortedUsers(String query) throws ServiceException {
        List<UserDTO> userDTOS = new ArrayList<>();
        try {
            List<User> users = userDAO.getSorted(query);
            users.forEach(user -> userDTOS.add(convertUserToDTO(user)));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return userDTOS;
    }

    @Override
    public List<UserDTO> getParticipants(String eventId, Role role) throws ServiceException {
        return null;
    }

    @Override
    public int getNumberOfRecords(String filter) throws ServiceException {
        return 0;
    }

    @Override
    public List<UserDTO> getSpeakers() throws ServiceException {
        return null;
    }

    @Override
    public List<UserDTO> getModerators() throws ServiceException {
        return null;
    }

    @Override
    public void changePassword(long userId, String password, String newPass, String confirmPass) throws ServiceException {
        checkStrings(password, newPass, confirmPass);
        try {
            User user = userDAO.getById(userId);
            verify(user.getPassword(), password);
            checkPasswordMatching(newPass, confirmPass);
            validatePassword(newPass);
            User userToUpdate = User.builder().id(userId).password(encode(newPass)).build();
            userDAO.updatePassword(userToUpdate);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }



    @Override
    public void setRole(String email, int roleId) throws ServiceException {
        try {
            Role role = Role.getRole(roleId);
            userDAO.setUserRole(email, role);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void registerForEvent(long userId, String eventIdString) throws ServiceException {

    }

    @Override
    public void cancelRegistration(long userId, String eventIdString) throws ServiceException {

    }
    private void validateUser(UserDTO userDTO) throws IncorrectFormatException {
        validateEmail(userDTO.getEmail());
        validateName(userDTO.getName(), ENTER_CORRECT_NAME);
        validateName(userDTO.getSurname(), ENTER_CORRECT_SURNAME);
    }
    @Override
    public boolean isRegistered(long userId, String eventIdString) throws ServiceException {
        return false;
    }
}
