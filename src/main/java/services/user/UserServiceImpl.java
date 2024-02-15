package services.user;

import entities.User;
import repositories.user.UserRepository;
import repositories.user.UserRepositoryImpl;
import services.exceptions.UserServiceException;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class UserServiceImpl implements UserService {

    private static UserService userService;

    private final UserRepository userRepository = UserRepositoryImpl.getInstance();

    private UserServiceImpl() {
    }

    public static UserService getInstance() {
        return Objects.requireNonNullElseGet(userService, () -> userService = new UserServiceImpl());
    }

    @Override
    public User create(User user) {
        return userRepository.create(user);
    }

    @Override
    public List<User> read() {
        return userRepository.read();
    }

    @Override
    public User update(final UUID id, final User user) {
        return userRepository.update(id, user).orElseThrow(() -> new UserServiceException("This user can't be updated"))
                .setLogin(user.getLogin())
                .setPassword(user.getPassword())
                .setName(user.getName())
                .setRole(user.getRole());
    }

    @Override
    public boolean delete(final UUID id) {
        return userRepository.delete(id);
    }

    @Override
    public User getUserByLogin(final String login) {
        return userRepository.getUserByLogin(login).orElseThrow(
                () -> new UserServiceException("User doesn't exist")
        );
    }

    @Override
    public boolean isUserPresent(final String login, final String password) {
        return userRepository.getUserByLogin(login).map(value -> value.getPassword().equals(password))
                .orElse(false);
    }

    @Override
    public boolean isLoginExist(final String login) {
        return userRepository.getUserByLogin(login).isPresent();
    }
}
