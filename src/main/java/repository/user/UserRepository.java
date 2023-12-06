package repository.user;

import entity.User;
import repository.Repository;

import java.util.Optional;

/**
 * some additional methods will be here
 */
public interface UserRepository extends Repository<User> {

    Optional<User> getUserByLogin(String login);
}
