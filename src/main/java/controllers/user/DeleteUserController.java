package controllers.user;

import services.exceptions.UserServiceException;
import services.user.UserService;
import services.user.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

import static java.util.UUID.fromString;
import static util.Constants.*;

@WebServlet(urlPatterns = USERS_DELETE_URL)
public class DeleteUserController extends HttpServlet {

    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        try {
            UUID id = fromString(req.getParameter(ID));
            userService.delete(id);
            req.getRequestDispatcher(USERS_READ_PAGE).forward(req, resp);
        } catch (UserServiceException | IllegalArgumentException ex) {
            req.getRequestDispatcher(ID_ERROR_PAGE).forward(req, resp);
        }
    }
}
