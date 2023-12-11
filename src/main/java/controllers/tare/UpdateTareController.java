package controllers.tare;

import entities.Tare;
import mappers.TareMapper;
import services.exceptions.TareServiceException;
import services.tare.TareService;
import services.tare.TareServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.UUID.fromString;
import static util.Constants.*;

@WebServlet(urlPatterns = TARES_UPDATE_URL)
public class UpdateTareController extends HttpServlet {

    private final TareService tareService = TareServiceImpl.getInstance();
    private final TareMapper tareMapper = TareMapper.getInstance();

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        try {
            final Tare tare = tareMapper.build(req);
            tareService.update(fromString(req.getParameter(ID)), tare);
            req.setAttribute(TARE_ATTRIBUTE, tare);
            req.getRequestDispatcher(TARES_READ_URL).forward(req, resp);
        } catch (TareServiceException | IllegalArgumentException ex) {
            req.getRequestDispatcher(ID_ERROR_PAGE).forward(req, resp);
        }
    }
}
