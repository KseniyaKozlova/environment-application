package controller.company;

import entity.Company;
import mapper.CompanyMapper;
import service.company.CompanyService;
import service.company.CompanyServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static util.Constants.COMPANIES_READ_URL;

@WebServlet(urlPatterns = "/companies/create")
public class CreateCompanyController extends HttpServlet {

    private final CompanyService companyService = CompanyServiceImpl.getInstance();
    private final CompanyMapper companyMapper = new CompanyMapper();

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        final Company company = companyMapper.buildCompany(req);
        companyService.create(company);
        req.getRequestDispatcher(COMPANIES_READ_URL).forward(req, resp);
    }
}
