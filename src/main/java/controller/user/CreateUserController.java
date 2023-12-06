package controller.user;

import entity.User;
import mapper.UserMapper;
import service.user.UserService;
import service.user.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static util.Constants.USERS_READ_URL;

@WebServlet(urlPatterns = "/users/create")
public class CreateUserController extends HttpServlet {

    private final UserService userService = UserServiceImpl.getInstance();
    private final UserMapper userMapper = new UserMapper();

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        User user = userMapper.buildUserWithAnyRole(req);
        userService.create(user);
        req.getRequestDispatcher(USERS_READ_URL).forward(req, resp);
    }
}
