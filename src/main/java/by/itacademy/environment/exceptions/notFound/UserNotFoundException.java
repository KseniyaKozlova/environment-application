package by.itacademy.environment.exceptions.notFound;

import jakarta.persistence.EntityNotFoundException;

public class UserNotFoundException extends EntityNotFoundException {

    public UserNotFoundException(final String message) {
        super(message);
    }
}
