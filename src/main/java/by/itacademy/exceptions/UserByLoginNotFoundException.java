package by.itacademy.exceptions;

public class UserByLoginNotFoundException extends UserNotFoundException {

    public UserByLoginNotFoundException(final String message) {
        super(message);
    }
}
