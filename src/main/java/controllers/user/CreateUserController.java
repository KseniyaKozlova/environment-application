package controllers.user;

import entities.User;
import mappers.UserMapper;
import services.user.UserService;
import services.user.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static util.Constants.USERS_CREATE_URL;
import static util.Constants.USERS_READ_URL;

@WebServlet(urlPatterns = USERS_CREATE_URL)
public class CreateUserController extends HttpServlet {

    private final UserService userService = UserServiceImpl.getInstance();
    private final UserMapper userMapper = UserMapper.getInstance();

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        User user = userMapper.buildUserWithAnyRole(req);
        userService.create(user);
        req.getRequestDispatcher(USERS_READ_URL).forward(req, resp);
    }
}
