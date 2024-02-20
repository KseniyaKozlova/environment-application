package by.itacademy.environment.mappers;

import by.itacademy.environment.dto.request.CreateCompanyRequestDto;
import by.itacademy.environment.dto.request.CreateCouponRequestDto;
import by.itacademy.environment.dto.request.UpdateCompanyRequestDto;
import by.itacademy.environment.dto.response.CompanyResponseDto;
import by.itacademy.environment.entities.Company;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = SPRING, uses = CreateCouponRequestDto.class, nullValuePropertyMappingStrategy = IGNORE)
public interface CompanyMapper {

    /**
     * Mapping company request to entity.
     *
     * @param companyRequestDto request for company creating
     * @return entity
     */
    Company mapToCompany(CreateCompanyRequestDto companyRequestDto);

    /**
     * Mapping company entity to response.
     *
     * @param company entity
     * @return CompanyResponseDto response with company details
     */
    CompanyResponseDto mapToCompanyResponse(Company company);

    /**
     * Mapping company entity to response.
     *
     * @param companyRequestDto request for company with fields for updating
     * @param company           entity for updating
     */
    void updateCompany(UpdateCompanyRequestDto companyRequestDto, @MappingTarget Company company);
}
