package service.user;

import service.ServiceException;

public class UserServiceException extends ServiceException {

    public UserServiceException(final String message) {
        super(message);
    }
}
