package controller.tare;

import entity.Tare;
import mapper.TareMapper;
import service.tare.TareService;
import service.tare.TareServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static util.Constants.TARES_READ_URL;

@WebServlet(urlPatterns = "/tares/create")
public class CreateTareController extends HttpServlet {

    private final TareService tareService = TareServiceImpl.getInstance();
    private final TareMapper tareMapper = new TareMapper();

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        Tare tare = tareMapper.build(req);
        tareService.create(tare);
        req.getRequestDispatcher(TARES_READ_URL).forward(req, resp);
    }
}
