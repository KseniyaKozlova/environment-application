package by.itacademy.environment.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class CouponNotFoundException extends EntityNotFoundException {

    public CouponNotFoundException(final String message) {
        super(message);
    }
}
