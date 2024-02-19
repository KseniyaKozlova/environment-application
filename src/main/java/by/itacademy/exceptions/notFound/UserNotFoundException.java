package by.itacademy.exceptions.notFound;

import jakarta.persistence.EntityNotFoundException;

public class UserNotFoundException extends EntityNotFoundException {

    public UserNotFoundException(final String message) {
        super(message);
    }
}
