package by.itacademy.environment.mappers;

import by.itacademy.environment.dto.request.CreateCompanyRequestDto;
import by.itacademy.environment.dto.request.CreateCouponRequestDto;
import by.itacademy.environment.dto.request.UpdateCompanyRequestDto;
import by.itacademy.environment.dto.response.CompanyResponseDto;
import by.itacademy.environment.entities.Company;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = SPRING, uses = CreateCouponRequestDto.class, nullValuePropertyMappingStrategy = IGNORE)
public interface CompanyMapper {

    Company mapToCompany(CreateCompanyRequestDto companyRequestDto);

    CompanyResponseDto mapToCompanyResponse(Company company);

    @InheritConfiguration
    void updateCompany(UpdateCompanyRequestDto companyRequestDto, @MappingTarget Company company);
}
