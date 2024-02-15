package repositories.company;

import entities.Company;

import java.util.*;

import static java.util.UUID.randomUUID;

public class CompanyRepositoryImpl implements CompanyRepository {

    private static CompanyRepository companyRepository;
    private final List<Company> companies;

    {
        companies = new ArrayList<>();
        companies.add(new Company(randomUUID(), "Season", "BIK: AAAA"));
        companies.add(new Company(randomUUID(), "Capital", "BIK: SSD"));
    }

    private CompanyRepositoryImpl() {
    }

    public static CompanyRepository getInstance() {
        return Objects.requireNonNullElseGet(companyRepository, () -> companyRepository = new CompanyRepositoryImpl());
    }

    @Override
    public Company create(final Company company) {
        company.setId(randomUUID());
        companies.add(company);
        return company;
    }

    @Override
    public List<Company> read() {
        return companies;
    }

    @Override
    public Optional<Company> update(final UUID id, final Company company) {
        return getById(id);
    }

    @Override
    public boolean delete(final UUID id) {
        return getById(id).map(companies::remove)
                .orElse(false);
    }

    private Optional<Company> getById(UUID id) {
        return companies.stream()
                .filter(company -> company.getId().equals(id))
                .findFirst();
    }
}
