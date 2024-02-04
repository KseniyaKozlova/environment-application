package by.itacademy.services.company;

import by.itacademy.dto.request.CreateCompanyRequestDto;
import by.itacademy.dto.request.UpdateCompanyRequestDto;
import by.itacademy.dto.response.CompanyResponseDto;
import by.itacademy.entities.Company;
import by.itacademy.mappers.CompanyMapper;
import by.itacademy.repositories.company.CompanyRepository;
import by.itacademy.services.exceptions.CompanyServiceException;
import by.itacademy.services.exceptions.CouponServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    @Override
    public CompanyResponseDto saveCompany(final CreateCompanyRequestDto companyRequestDto) {
        final Company company = companyMapper.mapToCompany(companyRequestDto);
        final Company savedCompany = companyRepository.save(company);
        return companyMapper.mapToCompanyResponse(savedCompany);
    }

    @Override
    public CompanyResponseDto getCompanyById(final UUID id) {
        final Company company = companyRepository.findById(id)
                .orElseThrow(() -> new CouponServiceException("This coupon doesn't exist"));
        return companyMapper.mapToCompanyResponse(company);
    }

    @Override
    public List<CompanyResponseDto> readCompanies() {
        return companyRepository.findAll().stream()
                .map(companyMapper::mapToCompanyResponse)
                .collect(toList());
    }

    @Override
    public CompanyResponseDto updateCompany(final UUID id, final UpdateCompanyRequestDto companyRequestDto) {
        final Company companyToUpdate = companyRepository.findById(id)
                .orElseThrow(() -> new CompanyServiceException("This company can't be updated"));

        companyMapper.updateCompany(companyRequestDto, companyToUpdate);
        final Company updatedCompany = companyRepository.save(companyToUpdate);
        return companyMapper.mapToCompanyResponse(updatedCompany);
    }

    @Override
    public void deleteCompany(final UUID id) {
        companyRepository.deleteById(id);
    }
}
