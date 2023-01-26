package com.epam.taxi_service.Exception;

import static com.epam.taxi_service.Exception.message_for_exceptions.MessageForExceptions.PASSWORD_MATCHING;

public class PasswordMatchingException extends ServiceException{
    public PasswordMatchingException() {
        super(PASSWORD_MATCHING);
    }
}
