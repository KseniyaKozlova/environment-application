package by.itacademy.exceptions.notFound;

import jakarta.persistence.EntityNotFoundException;

public class TareNotFoundException extends EntityNotFoundException {

    public TareNotFoundException(final String message) {
        super(message);
    }
}
