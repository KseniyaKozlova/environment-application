package service.user;

import entity.User;
import service.Service;

import javax.servlet.ServletRequest;

/**
 * some additional methods will be here
 */
public interface UserService extends Service<User> {

    User getUserByLogin(String login);

    boolean checkUser(String login, String password);

    boolean checkUser(String login);
}
