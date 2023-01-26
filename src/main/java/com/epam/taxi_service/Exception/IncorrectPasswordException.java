package com.epam.taxi_service.Exception;

import static com.epam.taxi_service.Exception.message_for_exceptions.MessageForExceptions.WRONG_PASSWORD;

public class IncorrectPasswordException extends ServiceException{
    public IncorrectPasswordException() {
        super(WRONG_PASSWORD);
    }
}
