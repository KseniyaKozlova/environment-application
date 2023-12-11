package services.user;

import entities.User;
import services.Service;

/**
 * some additional methods will be here
 */
public interface UserService extends Service<User> {

    User getUserByLogin(String login);

    boolean isUserPresent(String login, String password);

    boolean isLoginExist(String login);
}
