package com.epam.taxi_service.Exception;

import static com.epam.taxi_service.Exception.message_for_exceptions.MessageForExceptions.NO_USER;

public class NoSuchUserException extends ServiceException{
    public NoSuchUserException() {
        super(NO_USER);
    }
}
