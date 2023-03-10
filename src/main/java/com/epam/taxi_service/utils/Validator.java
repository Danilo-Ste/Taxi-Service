package com.epam.taxi_service.utils;

import com.epam.taxi_service.Exception.IncorrectFormatException;
import com.epam.taxi_service.Exception.NoSuchUserException;
import com.epam.taxi_service.Exception.PasswordMatchingException;
import com.epam.taxi_service.Exception.ServiceException;
import com.epam.taxi_service.utils.regex.Regex;

import java.time.LocalDate;

import static com.epam.taxi_service.Exception.message_for_exceptions.MessageForExceptions.*;

public class Validator {
    public static void validateAddressForTaxi(String address) throws IncorrectFormatException {
        validateFormat(address, Regex.CAR_ADDRESS_REGEX, ENTER_CORRECT_ADDRESS);
    }
    public static void validateEmail(String email) throws IncorrectFormatException {
        validateFormat(email, Regex.EMAIL_REGEX, ENTER_CORRECT_EMAIL);
    }

    public static void validatePassword(String password) throws IncorrectFormatException {
        validateFormat(password, Regex.PASSWORD_REGEX, ENTER_CORRECT_PASSWORD);
    }


    public static void validateName(String name, String message) throws IncorrectFormatException {
        validateFormat(name, Regex.NAME_REGEX, message);
    }

    public static void validateComplexName(String name, String message) throws IncorrectFormatException {
        validateFormat(name, Regex.COMPLEX_NAME_REGEX, message);
    }

    public static void validateAddresses(String name, String message) throws IncorrectFormatException {
        validateFormat(name, Regex.COMPLEX_NAME_REGEX, message);
    }

    public static void validateDescription(String name) throws IncorrectFormatException {
        validateFormat(name, Regex.DESCRIPTION_REGEX, ENTER_CORRECT_DESCRIPTION);
    }

    private static void validateFormat(String name, String regex,String message) throws IncorrectFormatException {
        //if (name == null || !name.matches(regex))
            //throw new IncorrectFormatException(message);
    }

    public static void validateDate(LocalDate date) throws IncorrectFormatException {
        if (date == null || !date.isAfter(LocalDate.now())) {
            throw new IncorrectFormatException(ENTER_VALID_DATE);
        }
    }

    public static void checkPasswordMatching(String password, String confirmPassword) throws PasswordMatchingException {
        if (!password.equals(confirmPassword)) {
            throw new PasswordMatchingException();
        }
    }


    public static long getId(String idString) throws ServiceException {
        return checkId(idString, new NoSuchUserException());
    }


    private static long checkId(String idString, ServiceException exception) throws ServiceException {
        long id;
        try {
            id = Long.parseLong(idString);
        } catch (NumberFormatException e) {
            throw exception;
        }
        return id;
    }

    public static long getLong(String longString) throws ServiceException {
        long result;
        try {
            result = Long.parseLong(longString);
        } catch (NumberFormatException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    public static int getInt(String intString) throws ServiceException {
        int result;
        try {
            result = Integer.parseInt(intString);
        } catch (NumberFormatException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    public static void checkStrings(String... strings) throws ServiceException {
        for (String string : strings) {
            if (string == null) {
                throw new ServiceException(new NullPointerException());
            }
        }
    }

    private Validator() {}
}
