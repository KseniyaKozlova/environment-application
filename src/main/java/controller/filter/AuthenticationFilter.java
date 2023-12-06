package controller.filter;

import service.user.UserService;
import service.user.UserServiceImpl;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static util.Constants.*;

@WebFilter(urlPatterns = "/authentication")
public class AuthenticationFilter extends HttpFilter {

    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doFilter(final HttpServletRequest req, final HttpServletResponse res, final FilterChain chain) throws IOException, ServletException {
        boolean isUserPresent = userService.checkUser(req.getParameter(LOGIN), req.getParameter(PASS));
        if (isUserPresent) {
            chain.doFilter(req, res);
        } else {
            req.getRequestDispatcher(PASS_ERROR_PAGE).forward(req, res);
        }
    }
}
