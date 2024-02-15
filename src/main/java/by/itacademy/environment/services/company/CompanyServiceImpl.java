package by.itacademy.environment.services.company;

import by.itacademy.environment.dto.request.CreateCompanyRequestDto;
import by.itacademy.environment.dto.request.UpdateCompanyRequestDto;
import by.itacademy.environment.dto.response.AddressResponseDto;
import by.itacademy.environment.dto.response.CompanyResponseDto;
import by.itacademy.environment.entities.Company;
import by.itacademy.environment.exceptions.CompanyNotFoundException;
import by.itacademy.environment.feignClients.CompanyAddressClient;
import by.itacademy.environment.mappers.CompanyMapper;
import by.itacademy.environment.repositories.company.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final CompanyAddressClient addressClient;

    @Override
    @Transactional
    public CompanyResponseDto saveCompany(final CreateCompanyRequestDto companyRequestDto) {
        final Company company = companyMapper.mapToCompany(companyRequestDto);
        company.getCoupons().forEach(company::addCoupon);
        final Company savedCompany = companyRepository.save(company);
        return companyMapper.mapToCompanyResponse(savedCompany);
    }

    @Override
    @Transactional(readOnly = true)
    public CompanyResponseDto getCompanyResponseById(final UUID id) {
        final Company company = companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException(id.toString()));

        final CompanyResponseDto companyResponseDto = companyMapper.mapToCompanyResponse(company);
        final List<AddressResponseDto> companyAddresses = addressClient.getCompanyAddress(company.getCompanyName());
        companyResponseDto.setAddresses(companyAddresses);
        return companyResponseDto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CompanyResponseDto> readCompanies() {
        return companyRepository.findAll().stream()
                .map(companyMapper::mapToCompanyResponse)
                .toList();
    }

    @Override
    @Transactional
    public CompanyResponseDto updateCompany(final UUID id, final UpdateCompanyRequestDto companyRequestDto) {
        final Company companyToUpdate = companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException(id.toString()));

        companyMapper.updateCompany(companyRequestDto, companyToUpdate);
        final Company updatedCompany = companyRepository.save(companyToUpdate);
        return companyMapper.mapToCompanyResponse(updatedCompany);
    }

    @Override
    @Transactional
    public void deleteCompany(final UUID id) {
        companyRepository.deleteById(id);
    }

    @Override
    public Company getCompanyById(final UUID id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException(id.toString()));
    }
}
