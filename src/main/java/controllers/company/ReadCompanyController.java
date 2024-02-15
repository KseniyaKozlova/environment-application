package controllers.company;

import entities.Company;
import services.company.CompanyService;
import services.company.CompanyServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static util.Constants.*;

@WebServlet(urlPatterns = COMPANIES_READ_URL)
public class ReadCompanyController extends HttpServlet {

    private final CompanyService companyService = CompanyServiceImpl.getInstance();

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        List<Company> companies = companyService.read();
        req.setAttribute(COMPANY_ATTRIBUTE, companies);
        req.getRequestDispatcher(COMPANIES_READ_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
