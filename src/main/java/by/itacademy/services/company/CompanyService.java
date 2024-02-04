package by.itacademy.services.company;

import by.itacademy.dto.request.CreateCompanyRequestDto;
import by.itacademy.dto.request.UpdateCompanyRequestDto;
import by.itacademy.entities.Company;

import java.util.List;
import java.util.UUID;

/**
 * some additional methods will be here
 */
public interface CompanyService {

    Company saveCompany(CreateCompanyRequestDto companyRequestDto);

    Company getCompanyById(UUID id);

    List<Company> readCompanies();

    Company updateCompany(UUID id, UpdateCompanyRequestDto companyRequestDto);

    void deleteCompany(UUID id);
}
