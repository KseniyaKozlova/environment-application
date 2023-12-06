package controller.tare;

import service.tare.TareService;
import service.tare.TareServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

import static java.util.UUID.fromString;
import static util.Constants.ID;
import static util.Constants.TARES_READ_URL;

@WebServlet(urlPatterns = "/tares/delete")
public class DeleteTareController extends HttpServlet {

    private final TareService tareService = TareServiceImpl.getInstance();

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        UUID id = fromString(req.getParameter(ID));
        tareService.delete(id);
        req.getRequestDispatcher(TARES_READ_URL).forward(req, resp);
    }
}
