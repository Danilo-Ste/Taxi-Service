package com.epam.taxi_service.Exception;

import static com.epam.taxi_service.Exception.message_for_exceptions.MessageForExceptions.DUPLICATE_EMAIL;

public class DuplicateEmailException extends ServiceException {
    public DuplicateEmailException() {
        super(DUPLICATE_EMAIL);
    }
}
