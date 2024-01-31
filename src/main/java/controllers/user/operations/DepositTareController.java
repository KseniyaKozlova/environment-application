package controllers.user.operations;

import services.exceptions.TareServiceException;
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

@WebServlet(urlPatterns = USERS_DEPOSIT_TARE_URL)
public class DepositTareController extends HttpServlet {

    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        try {
            final UUID userId = (UUID) req.getSession().getAttribute(USER_ID_ATTRIBUTE);
            final UUID tareId = fromString(req.getParameter(ID));
            userService.depositTare(userId, tareId);
            req.getRequestDispatcher(USER_DEPOSIT_TARE_PAGE).forward(req, resp);
        } catch (UserServiceException | TareServiceException ex) {
            req.getRequestDispatcher(ID_ERROR_PAGE).forward(req, resp);
        }
    }
}
