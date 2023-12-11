package controllers.user;

import entities.User;
import services.user.UserService;
import services.user.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static util.Constants.*;

@WebServlet(urlPatterns = USERS_READ_URL)
public class ReadUserController extends HttpServlet {

    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = userService.read();
        req.setAttribute(USERS_ATTRIBUTE, users);
        req.getRequestDispatcher(USERS_READ_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
