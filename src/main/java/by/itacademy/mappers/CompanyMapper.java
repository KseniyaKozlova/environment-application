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

    Company mapToCompany(CreateCompanyRequestDto companyRequestDto);

    CompanyResponseDto mapToCompanyResponse(Company company);

    @InheritConfiguration
    void updateCompany(UpdateCompanyRequestDto companyRequestDto, @MappingTarget Company company);
}
