package mappers;

import entities.Company;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

import static util.Constants.COMPANY_DETAILS_PARAMETER;
import static util.Constants.COMPANY_NAME_PARAMETER;

public class CompanyMapper {

    private static CompanyMapper companyMapper;

    private CompanyMapper() {
    }

    public static CompanyMapper getInstance() {
        return Objects.requireNonNullElseGet(companyMapper, () -> companyMapper = new CompanyMapper());
    }

    public Company buildCompany(HttpServletRequest request) {
        return Company.builder()
                .companyName(request.getParameter(COMPANY_NAME_PARAMETER))
                .details(request.getParameter(COMPANY_DETAILS_PARAMETER))
                .build();
    }
}
