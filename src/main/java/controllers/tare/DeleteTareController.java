package controllers.tare;

import services.exceptions.TareServiceException;
import services.tare.TareService;
import services.tare.TareServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

import static java.util.UUID.fromString;
import static util.Constants.*;

@WebServlet(urlPatterns = TARES_DELETE_URL)
public class DeleteTareController extends HttpServlet {

    private final TareService tareService = TareServiceImpl.getInstance();

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        try {
            UUID id = fromString(req.getParameter(ID));
            tareService.delete(id);
            req.getRequestDispatcher(TARES_READ_URL).forward(req, resp);
        } catch (TareServiceException | IllegalArgumentException ex) {
            req.getRequestDispatcher(ID_ERROR_PAGE).forward(req, resp);
        }
    }
}
