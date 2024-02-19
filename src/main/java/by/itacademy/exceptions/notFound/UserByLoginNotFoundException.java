package by.itacademy.exceptions.notFound;

public class UserByLoginNotFoundException extends UserNotFoundException {

    public UserByLoginNotFoundException(final String message) {
        super(message);
    }
}
