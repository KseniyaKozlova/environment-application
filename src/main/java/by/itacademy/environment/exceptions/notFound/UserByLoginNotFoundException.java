package by.itacademy.environment.exceptions.notFound;

public class UserByLoginNotFoundException extends UserNotFoundException {

    public UserByLoginNotFoundException(final String message) {
        super(message);
    }
}
