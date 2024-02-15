package repositories.user;

import entities.User;
import enums.Role;

import java.util.*;

import static java.util.UUID.randomUUID;

public class UserRepositoryImpl implements UserRepository {

    private static UserRepository userRepository;
    private final List<User> users;

    {
        users = new ArrayList<>();
        users.add(new User(randomUUID(), "post1", "password1", "Igor", 0, Role.ADMINISTRATOR));
        users.add(new User(UUID.fromString("55b77255-0771-4578-8226-3de0d4da69ec"), "post2", "password2", "Ivan", 0, Role.APPLICATION_MODERATOR));
    }

    private UserRepositoryImpl() {
    }

    public static UserRepository getInstance() {
        return Objects.requireNonNullElseGet(userRepository, () -> userRepository = new UserRepositoryImpl());
    }

    @Override
    public User create(final User user) {
        user.setId(randomUUID());
        users.add(user);
        return user;
    }

    public List<User> read() {
        return users;
    }

    @Override
    public Optional<User> update(final UUID id, final User user) {
        return getById(id);
    }

    @Override
    public Optional<User> getUserByLogin(final String login) {
        return users.stream()
                .filter(user -> user.getLogin().equals(login))
                .findAny();
    }

    @Override
    public boolean delete(final UUID id) {
        Optional<User> userOptional = getById(id);
        return userOptional.map(users::remove)
                .orElse(false);
    }

    private Optional<User> getById(UUID id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }
}
