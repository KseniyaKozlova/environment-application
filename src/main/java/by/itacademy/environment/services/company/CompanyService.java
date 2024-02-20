package by.itacademy.environment.services.company;

import by.itacademy.environment.dto.request.CreateCompanyRequestDto;
import by.itacademy.environment.dto.request.UpdateCompanyRequestDto;
import by.itacademy.environment.dto.response.CompanyResponseDto;
import by.itacademy.environment.entities.Company;

import java.util.List;
import java.util.UUID;

/**
 * Company processing service
 */
public interface CompanyService {

    /**
     * Save company in DB with its coupons, if they are need
     *
     * @param companyRequestDto request for company creating
     * @return company in response view
     */
    CompanyResponseDto saveCompany(CreateCompanyRequestDto companyRequestDto);

    /**
     * Get company from DB by id
     *
     * @param id company id
     * @return company in response view
     */
    CompanyResponseDto getCompanyResponseById(UUID id);

    /**
     * Get all companies from DB
     *
     * @return list of companies in response view
     */
    List<CompanyResponseDto> getCompanies();

    /**
     * Update company in DB by id
     *
     * @param id                company id
     * @param companyRequestDto request for company with fields for updating
     * @return updated company in response view
     */
    CompanyResponseDto updateCompany(UUID id, UpdateCompanyRequestDto companyRequestDto);

    /**
     * Delete company in DB by id
     *
     * @param id company id
     */
    void deleteCompany(UUID id);

    /**
     * Get entity company from DB by id
     *
     * @param id company id
     * @return entity
     */
    Company getCompanyById(UUID id);
}
