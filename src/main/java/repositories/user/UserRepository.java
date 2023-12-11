package repositories.user;

import entities.User;
import repositories.Repository;

import java.util.Optional;

/**
 * some additional methods will be here
 */
public interface UserRepository extends Repository<User> {

    Optional<User> getUserByLogin(String login);
}
