package controllers.user.enter;

import entities.User;
import mappers.UserMapper;
import services.user.UserService;
import services.user.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static util.Constants.*;

@WebServlet(urlPatterns = REGISTRATION_URL)
public class RegistrationUserController extends HttpServlet {

    private final UserService userService = UserServiceImpl.getInstance();
    private final UserMapper userMapper = UserMapper.getInstance();

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        User user = userMapper.buildUserWithConsumerRole(req);
        userService.create(user);

        HttpSession userSession = req.getSession();
        userSession.setAttribute(ROLE, user.getRole());

        req.getRequestDispatcher(ADMIN_MENU_PAGE).forward(req, resp);
    }
}
