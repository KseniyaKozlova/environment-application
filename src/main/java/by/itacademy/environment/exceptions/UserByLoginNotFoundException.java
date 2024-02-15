package by.itacademy.environment.exceptions;

public class UserByLoginNotFoundException extends UserNotFoundException {

    public UserByLoginNotFoundException(final String message) {
        super(message);
    }
}
