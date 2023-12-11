package services.exceptions;

public class ServiceException extends RuntimeException {

    public ServiceException(final String message) {
        super(message);
    }
}
