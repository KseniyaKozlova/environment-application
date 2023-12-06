package service.user;

import entity.User;
import repository.user.UserRepository;
import repository.user.UserRepositoryImpl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
        return userRepository.update(id, user).orElseThrow(
                () -> new UserServiceException("This user can't be updated")
        );
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
    public boolean checkUser(final String login, final String password) {
        Optional<User> user = userRepository.getUserByLogin(login);
        return user.map(value -> value.getPassword().equals(password)).orElse(false);
    }

    @Override
    public boolean checkUser(final String login) {
        return userRepository.getUserByLogin(login).isPresent();
    }
}
