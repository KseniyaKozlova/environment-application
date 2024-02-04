package by.itacademy.services.company;

import by.itacademy.dto.request.CreateCompanyRequestDto;
import by.itacademy.dto.request.UpdateCompanyRequestDto;
import by.itacademy.dto.response.CompanyResponseDto;

import java.util.List;
import java.util.UUID;

/**
 * some additional methods will be here
 */
public interface CompanyService {

    CompanyResponseDto saveCompany(CreateCompanyRequestDto companyRequestDto);

    CompanyResponseDto getCompanyById(UUID id);

    List<CompanyResponseDto> readCompanies();

    CompanyResponseDto updateCompany(UUID id, UpdateCompanyRequestDto companyRequestDto);

    void deleteCompany(UUID id);
}
