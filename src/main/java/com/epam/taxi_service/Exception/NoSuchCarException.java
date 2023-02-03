package com.epam.taxi_service.Exception;

import static com.epam.taxi_service.Exception.message_for_exceptions.MessageForExceptions.NO_CAR;

public class NoSuchCarException extends ServiceException{
    public NoSuchCarException() {
        super(NO_CAR);
    }
}
