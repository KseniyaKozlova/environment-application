package mapper;

import entity.User;
import enums.Role;

import javax.servlet.http.HttpServletRequest;

import static util.Constants.*;

public class UserMapper {

    public User buildUserWithConsumerRole(HttpServletRequest request) {
        return User.builder()
                .login(request.getParameter(LOGIN))
                .password(request.getParameter(PASS))
                .name(request.getParameter(USER_NAME))
                .bonuses(ZERO)
                .role(Role.CONSUMER)
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
