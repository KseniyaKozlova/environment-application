package by.itacademy.mappers;

import by.itacademy.dto.request.CreateCompanyRequestDto;
import by.itacademy.dto.request.CreateCouponRequestDto;
import by.itacademy.dto.request.UpdateCompanyRequestDto;
import by.itacademy.dto.response.CompanyResponseDto;
import by.itacademy.entities.Company;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = SPRING, uses = CreateCouponRequestDto.class, nullValuePropertyMappingStrategy = IGNORE)
public interface CompanyMapper {

    /** Mapping company request to entity.
     *
     * @param companyRequestDto request for company creating
     * @return entity
     */
    Company mapToCompany(CreateCompanyRequestDto companyRequestDto);

    /** Mapping company entity to response.
     *
     * @param company entity
     * @return CompanyResponseDto response with company details
     */
    CompanyResponseDto mapToCompanyResponse(Company company);

    /** Mapping company entity to response.
     *
     * @param companyRequestDto request for company with fields for updating
     * @param company entity for updating
     */
    @InheritConfiguration
    void updateCompany(UpdateCompanyRequestDto companyRequestDto, @MappingTarget Company company);
}
