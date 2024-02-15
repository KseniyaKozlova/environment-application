package by.itacademy.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class TareNotFoundException extends EntityNotFoundException {

    public TareNotFoundException(final String message) {
        super(message);
    }
}
