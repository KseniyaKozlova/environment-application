package service.company;

import entity.Company;
import repository.company.CompanyRepository;
import repository.company.CompanyRepositoryImpl;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class CompanyServiceImpl implements CompanyService {

    private static CompanyService companyService;
    private final CompanyRepository companyRepository = CompanyRepositoryImpl.getInstance();

    private CompanyServiceImpl() {
    }

    public static CompanyService getInstance() {
        return Objects.requireNonNullElseGet(companyService, () -> companyService = new CompanyServiceImpl());
    }

    @Override
    public Company create(final Company company) {
        return companyRepository.create(company);
    }

    @Override
    public List<Company> read() {
        return companyRepository.read();
    }

    @Override
    public Company update(final UUID id, final Company company) {
        return companyRepository.update(id, company).orElseThrow(
                () -> new CompanyServiceException("This company can't be updated")
        );
    }

    @Override
    public boolean delete(final UUID id) {
        return companyRepository.delete(id);
    }
}
