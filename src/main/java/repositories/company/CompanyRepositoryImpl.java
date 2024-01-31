package repositories.company;

import entities.Company;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class CompanyRepositoryImpl implements CompanyRepository {

    private static CompanyRepository companyRepository;
    private final EntityManager entityManager = JPAUtil.getEntityManager();
    private final EntityTransaction transaction = entityManager.getTransaction();

    private CompanyRepositoryImpl() {
    }

    public static CompanyRepository getInstance() {
        return Objects.requireNonNullElseGet(companyRepository, () -> companyRepository = new CompanyRepositoryImpl());
    }

    @Override
    public Company create(final Company company) {
        transaction.begin();

        entityManager.persist(company);

        transaction.commit();

        return company;
    }

    @Override
    public List<Company> read() {
        transaction.begin();

        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Company> companyCriteriaQuery = criteriaBuilder.createQuery(Company.class);
        final Root<Company> companyRoot = companyCriteriaQuery.from(Company.class);
        companyCriteriaQuery.select(companyRoot);
        final List<Company> companies = entityManager.createQuery(companyCriteriaQuery).getResultList();

        transaction.commit();

        return companies;
    }

    @Override
    public Optional<Company> update(final UUID id, final Company company) {
        return getById(id);
    }

    @Override
    public boolean delete(final UUID id) {
        transaction.begin();

        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaDelete<Company> companyCriteriaDelete = criteriaBuilder.createCriteriaDelete(Company.class);
        final Root<Company> companyRoot = companyCriteriaDelete.from(Company.class);
        companyCriteriaDelete.where(criteriaBuilder.equal(companyRoot.get("id"), id));
        final int executeUpdate = entityManager.createQuery(companyCriteriaDelete).executeUpdate();

        transaction.commit();

        return executeUpdate > 0;
    }

    private Optional<Company> getById(UUID id) {
        transaction.begin();

        final Company company = entityManager.find(Company.class, id);

        transaction.commit();

        return Optional.ofNullable(company);
    }
}
