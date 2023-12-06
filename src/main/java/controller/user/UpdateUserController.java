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
import java.util.UUID;

@WebServlet(urlPatterns = "/users/update")
public class UpdateUserController extends HttpServlet {

    private final UserMapper userMapper = new UserMapper();
    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        User user = userMapper.buildUserWithAnyRole(req);
        userService.update(UUID.fromString(req.getParameter("id")), user);
        req.setAttribute("user", user);
        req.getRequestDispatcher("/users/read").forward(req, resp);
    }
}
