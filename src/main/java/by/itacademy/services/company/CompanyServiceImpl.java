package by.itacademy.services.company;

import by.itacademy.dto.request.CreateCompanyRequestDto;
import by.itacademy.dto.request.UpdateCompanyRequestDto;
import by.itacademy.entities.Company;
import lombok.RequiredArgsConstructor;
import by.itacademy.mappers.CompanyMapper;
import org.springframework.stereotype.Service;
import by.itacademy.repositories.company.CompanyRepository;
import by.itacademy.services.exceptions.CompanyServiceException;
import by.itacademy.services.exceptions.CouponServiceException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    @Override
    public Company saveCompany(final CreateCompanyRequestDto companyRequestDto) {
        final Company company = companyMapper.mapToCompany(companyRequestDto);
        return companyRepository.save(company);
    }

    @Override
    public Company getCompanyById(final UUID id) {
        return companyRepository.findById(id).orElseThrow(() -> new CouponServiceException("This coupon doesn't exist"));
    }

    @Override
    public List<Company> readCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company updateCompany(final UUID id, final UpdateCompanyRequestDto companyRequestDto) {
        final Company companyToUpdate = companyRepository.findById(id)
                .orElseThrow(() -> new CompanyServiceException("This company can't be updated"));

        companyMapper.updateCompany(companyRequestDto, companyToUpdate);
        return companyRepository.save(companyToUpdate);
    }

    @Override
    public void deleteCompany(final UUID id) {
        companyRepository.deleteById(id);
    }
}
