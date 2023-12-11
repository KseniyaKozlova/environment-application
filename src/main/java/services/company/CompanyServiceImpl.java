package services.company;

import entities.Company;
import repositories.company.CompanyRepository;
import repositories.company.CompanyRepositoryImpl;
import services.exceptions.CompanyServiceException;

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
        final Company companyToUpdate = companyRepository.update(id, company)
                .orElseThrow(() -> new CompanyServiceException("This company can't be updated"));
        return updateCompanyFields(company, companyToUpdate);
    }

    private Company updateCompanyFields(final Company company, final Company companyToUpdate) {
        return companyToUpdate.setCompanyName(company.getCompanyName())
                .setDetails(company.getDetails());
    }

    @Override
    public boolean delete(final UUID id) {
        return companyRepository.delete(id);
    }
}
