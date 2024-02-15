package controllers.user;

import entities.User;
import mappers.UserMapper;
import services.exceptions.UserServiceException;
import services.user.UserService;
import services.user.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.UUID.fromString;
import static util.Constants.*;

@WebServlet(urlPatterns = USERS_UPDATE_URL)
public class UpdateUserController extends HttpServlet {

    private final UserService userService = UserServiceImpl.getInstance();
    private final UserMapper userMapper = UserMapper.getInstance();

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        try {
            User user = userMapper.buildUserWithAnyRole(req);
            userService.update(fromString(req.getParameter(ID)), user);
            req.setAttribute(USER_ATTRIBUTE, user);
            req.getRequestDispatcher(USERS_READ_URL).forward(req, resp);
        } catch (UserServiceException | IllegalArgumentException ex) {
            req.getRequestDispatcher(ID_ERROR_PAGE).forward(req, resp);
        }
    }
}
