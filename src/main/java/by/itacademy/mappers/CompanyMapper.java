package by.itacademy.mappers;

import by.itacademy.dto.request.CreateCompanyRequestDto;
import by.itacademy.dto.request.UpdateCompanyRequestDto;
import by.itacademy.dto.response.CompanyResponseDto;
import by.itacademy.entities.Company;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = CouponMapper.class)
public interface CompanyMapper {

    Company mapToCompany(CreateCompanyRequestDto companyRequestDto);

    CompanyResponseDto mapToCompanyResponse(Company company);

    @InheritConfiguration // TODO is it right?
    void updateCompany(UpdateCompanyRequestDto companyRequestDto, @MappingTarget Company company);
}
