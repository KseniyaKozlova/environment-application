package controllers.company;

import entities.Company;
import mappers.CompanyMapper;
import services.company.CompanyService;
import services.company.CompanyServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static util.Constants.COMPANIES_CREATE_URL;
import static util.Constants.COMPANIES_READ_URL;

@WebServlet(urlPatterns = COMPANIES_CREATE_URL)
public class CreateCompanyController extends HttpServlet {

    private final CompanyService companyService = CompanyServiceImpl.getInstance();
    private final CompanyMapper companyMapper = CompanyMapper.getInstance();

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        final Company company = companyMapper.buildCompany(req);
        companyService.create(company);
        req.getRequestDispatcher(COMPANIES_READ_URL).forward(req, resp);
    }
}
