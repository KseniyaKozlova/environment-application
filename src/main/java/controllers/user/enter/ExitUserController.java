package controllers.user.enter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static util.Constants.EXIT_URL;
import static util.Constants.START_PAGE;

@WebServlet(urlPatterns = EXIT_URL)
public class ExitUserController extends HttpServlet {

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        req.getRequestDispatcher(START_PAGE).forward(req, resp);
    }
}
