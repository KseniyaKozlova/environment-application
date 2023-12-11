package controllers.user.menu;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static util.Constants.*;

@WebServlet(urlPatterns = "/menu")
public class MenuUserController extends HttpServlet {

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        final String choice = req.getParameter(CHOICE_PARAMETER);
        switch (choice) {
            case SEE_TARES_BUTTON -> req.getRequestDispatcher(TARES_READ_URL).forward(req, resp);
            case DELETE_TARE_BUTTON -> req.getRequestDispatcher(TARE_DELETE_PAGE).forward(req, resp);
            case SEE_USERS_BUTTON -> req.getRequestDispatcher(USERS_READ_URL).forward(req, resp);
            case CREATE_TARE_BUTTON -> req.getRequestDispatcher(TARE_CREATE_PAGE).forward(req, resp);
            case UPDATE_TARE_BUTTON -> req.getRequestDispatcher(TARE_UPDATE_PAGE).forward(req, resp);
            case CREATE_USER_BUTTON -> req.getRequestDispatcher(USER_CREATE_PAGE).forward(req, resp);
            case UPDATE_USER_BUTTON -> req.getRequestDispatcher(USER_UPDATE_PAGE).forward(req, resp);
            case SEE_COMPANIES_BUTTON -> req.getRequestDispatcher(COMPANIES_READ_URL).forward(req, resp);
            case CREATE_COMPANY_BUTTON -> req.getRequestDispatcher(COMPANY_CREATE_PAGE).forward(req, resp);
        }
    }
}
