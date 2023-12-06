package controller.tare;

import entity.Tare;
import service.tare.TareService;
import service.tare.TareServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static util.Constants.TARES_ATTRIBUTE;
import static util.Constants.TARES_READ_PAGE;

@WebServlet(urlPatterns = "/tares/read")
public class ReadTareController extends HttpServlet {

    private final TareService tareService = TareServiceImpl.getInstance();

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        List<Tare> tares = tareService.read();
        req.setAttribute(TARES_ATTRIBUTE, tares);
        req.getRequestDispatcher(TARES_READ_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
