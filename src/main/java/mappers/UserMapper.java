package mappers;

import entities.User;
import enums.Role;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

import static enums.Role.CONSUMER;
import static util.Constants.*;

public class UserMapper {

    private static UserMapper userMapper;

    private UserMapper() {
    }

    public static UserMapper getInstance() {
        return Objects.requireNonNullElseGet(userMapper, () -> userMapper = new UserMapper());
    }

    public User buildUserWithConsumerRole(HttpServletRequest request) {
        return User.builder()
                .login(request.getParameter(LOGIN))
                .password(request.getParameter(PASS))
                .name(request.getParameter(USER_NAME))
                .bonuses(ZERO)
                .role(CONSUMER)
                .build();
    }

    public User buildUserWithAnyRole(HttpServletRequest request) {
        return User.builder()
                .login(request.getParameter(LOGIN))
                .password(request.getParameter(PASS))
                .name(request.getParameter(USER_NAME))
                .bonuses(ZERO)
                .role(Role.valueOf(request.getParameter(ROLE)))
                .build();
    }
}
