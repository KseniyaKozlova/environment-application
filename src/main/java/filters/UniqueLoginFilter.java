package filters;

import services.user.UserService;
import services.user.UserServiceImpl;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import static util.Constants.*;

@WebFilter(urlPatterns = {REGISTRATION_URL, USERS_CREATE_URL})
public class UniqueLoginFilter extends HttpFilter {

    private final UserService userService = UserServiceImpl.getInstance();
    private final Predicate<String> loginValidation = Pattern.compile(EMAIL_REGEX).asPredicate();

    @Override
    protected void doFilter(final HttpServletRequest req, final HttpServletResponse res, final FilterChain chain) throws IOException, ServletException {
        final String login = req.getParameter(LOGIN);
        final boolean isLoginValid = loginValidation.test(login);
        if (userService.isLoginExist(login) || !isLoginValid) {
            req.getRequestDispatcher(LOGIN_ERROR_PAGE).forward(req, res);
        } else {
            chain.doFilter(req, res);
        }
    }
}
