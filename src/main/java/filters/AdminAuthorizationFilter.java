package filters;

import enums.Role;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static enums.Role.ADMINISTRATOR;
import static util.Constants.*;

@WebFilter(urlPatterns = {USERS_CREATE_URL, USERS_UPDATE_URL, USERS_DELETE_URL})
public class AdminAuthorizationFilter extends HttpFilter {

    @Override
    protected void doFilter(final HttpServletRequest req, final HttpServletResponse res, final FilterChain chain) throws IOException, ServletException {
        final HttpSession actualSession = req.getSession(false);
        if (actualSession != null) {
            determineRole(req, res, chain, actualSession);
        } else {
            req.getRequestDispatcher(AUTHENTICATION_PAGE).forward(req, res);
        }
    }

    private void determineRole(final HttpServletRequest req, final HttpServletResponse res, final FilterChain chain, final HttpSession session) throws IOException, ServletException {
        final Role role = (Role) session.getAttribute(ROLE);
        if (role == ADMINISTRATOR) {
            chain.doFilter(req, res);
        } else {
            req.getRequestDispatcher(AUTHORIZATION_ERROR_PAGE).forward(req, res);
        }
    }
}
