package repository.company;

import entity.Company;

import java.util.*;

public class CompanyRepositoryImpl implements CompanyRepository {

    private static CompanyRepository companyRepository;
    private final List<Company> companies;

    {
        companies = new ArrayList<>();
        companies.add(new Company(UUID.randomUUID(), "Season", "BIK: AAAA"));
        companies.add(new Company(UUID.randomUUID(), "Capital", "BIK: SSD"));
    }

    private CompanyRepositoryImpl() {
    }

    public static CompanyRepository getInstance() {
        return Objects.requireNonNullElseGet(companyRepository, () -> companyRepository = new CompanyRepositoryImpl());
    }

    @Override
    public Company create(final Company company) {
        company.setId(UUID.randomUUID());
        companies.add(company);
        return company;
    }

    @Override
    public List<Company> read() {
        return companies;
    }

    @Override
    public Optional<Company> update(final UUID id, final Company company) {
        Optional<Company> companyOptional = getById(id);
        companyOptional.ifPresent(companyToUpdate -> companyToUpdate
                .setCompanyName(company.getCompanyName()));

        return companyOptional;
    }

    @Override
    public boolean delete(final UUID id) {
        Optional<Company> userOptional = getById(id);
        return userOptional.map(companies::remove)
                .orElse(false);
    }

    private Optional<Company> getById(UUID id) {
        return companies.stream()
                .filter(company -> company.getId().equals(id))
                .findFirst();
    }
}
