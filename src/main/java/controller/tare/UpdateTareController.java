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
import java.util.UUID;

@WebServlet(urlPatterns = "/tares/update")
public class UpdateTareController extends HttpServlet {

    private final TareMapper tareMapper = new TareMapper();
    private final TareService tareService = TareServiceImpl.getInstance();

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        final Tare tare = tareMapper.build(req);
        tareService.update(UUID.fromString(req.getParameter("id")), tare);
        req.setAttribute("tare", tare);
        req.getRequestDispatcher("/tares/read").forward(req, resp);
    }
}
