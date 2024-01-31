package repositories.user;

import entities.User;
import repositories.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * some additional methods will be here
 */
public interface UserRepository extends Repository<User> {

    Optional<User> getUserByLogin(String login);

    Optional<User> getById(UUID id);

//    void depositTare(UUID userId, UUID tareId);
}
