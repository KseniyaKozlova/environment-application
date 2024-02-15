package by.itacademy.environment.services.company;

import by.itacademy.environment.dto.request.CreateCompanyRequestDto;
import by.itacademy.environment.dto.request.UpdateCompanyRequestDto;
import by.itacademy.environment.dto.response.CompanyResponseDto;
import by.itacademy.environment.entities.Company;

import java.util.List;
import java.util.UUID;

/**
 * some additional methods will be here
 */
public interface CompanyService {

    CompanyResponseDto saveCompany(CreateCompanyRequestDto companyRequestDto);

    CompanyResponseDto getCompanyResponseById(UUID id);

    List<CompanyResponseDto> readCompanies();

    CompanyResponseDto updateCompany(UUID id, UpdateCompanyRequestDto companyRequestDto);

    void deleteCompany(UUID id);

    Company getCompanyById(UUID id);
}
