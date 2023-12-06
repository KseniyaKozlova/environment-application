package controller.user;

import service.user.UserService;
import service.user.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

import static util.Constants.ID;
import static util.Constants.USERS_READ_PAGE;

@WebServlet(urlPatterns = "/users/delete")
public class DeleteUserController extends HttpServlet {

    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        UUID id = UUID.fromString(req.getParameter(ID));
        userService.delete(id);
        req.getRequestDispatcher(USERS_READ_PAGE).forward(req, resp);
    }
}
