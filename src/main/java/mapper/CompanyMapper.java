package mapper;

import entity.Company;

import javax.servlet.http.HttpServletRequest;

import static util.Constants.COMPANY_DETAILS_PARAMETER;
import static util.Constants.COMPANY_NAME_PARAMETER;

public class CompanyMapper {

    public Company buildCompany(HttpServletRequest request) {
        return Company.builder()
                .companyName(request.getParameter(COMPANY_NAME_PARAMETER))
                .details(request.getParameter(COMPANY_DETAILS_PARAMETER))
                .build();
    }
}
