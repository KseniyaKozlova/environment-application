package services.exceptions;

import services.exceptions.ServiceException;

public class UserServiceException extends ServiceException {

    public UserServiceException(final String message) {
        super(message);
    }
}
