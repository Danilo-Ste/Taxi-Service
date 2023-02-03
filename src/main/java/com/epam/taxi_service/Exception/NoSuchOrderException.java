package com.epam.taxi_service.Exception;

import static com.epam.taxi_service.Exception.message_for_exceptions.MessageForExceptions.NO_ORDER;

public class NoSuchOrderException extends ServiceException{
    public NoSuchOrderException() {super(NO_ORDER);}
    public NoSuchOrderException(String message) {
        super(message);
    }
}
