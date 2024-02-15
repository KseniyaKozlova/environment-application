package controllers.user.menu;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static util.Constants.*;

@WebServlet(urlPatterns = "/choice")
public class ChoiceUserController extends HttpServlet {

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        String choice = req.getParameter(CHOICE_PARAMETER);
        if (choice.equals(LOGIN_BUTTON)) {
            req.getRequestDispatcher(AUTHENTICATION_PAGE).forward(req, resp);
        } else if (choice.equals(REGISTRATE_BUTTON)) {
            req.getRequestDispatcher(USER_REGISTRATION_PAGE).forward(req, resp);
        }
    }
}
