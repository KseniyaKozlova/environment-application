package by.itacademy.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class CompanyNotFoundException extends EntityNotFoundException {

    public CompanyNotFoundException(final String message) {
        super(message);
    }
}
