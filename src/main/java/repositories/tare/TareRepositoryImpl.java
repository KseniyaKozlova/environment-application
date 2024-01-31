package repositories.tare;

import entities.Tare;
import entities.User;
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

public class TareRepositoryImpl implements TareRepository {

    private static TareRepository tareRepository;
    private final EntityManager entityManager = JPAUtil.getEntityManager();
    private final EntityTransaction transaction = entityManager.getTransaction();

    private TareRepositoryImpl() {
    }

    public static TareRepository getInstance() {
        return Objects.requireNonNullElseGet(tareRepository, () -> tareRepository = new TareRepositoryImpl());
    }

    @Override
    public Tare create(final Tare tare) {
        transaction.begin();

        entityManager.persist(tare);

        transaction.commit();

        return tare;
    }

    @Override
    public List<Tare> read() {
        transaction.begin();

        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Tare> tareCriteriaQuery = criteriaBuilder.createQuery(Tare.class);
        final Root<Tare> tareRoot = tareCriteriaQuery.from(Tare.class);
        tareCriteriaQuery.select(tareRoot);
        final List<Tare> tares = entityManager.createQuery(tareCriteriaQuery).getResultList();

        transaction.commit();

        return tares;
    }

    @Override
    public Optional<Tare> update(final UUID id, final Tare tare) {
        return getById(id);
    }

    @Override
    public boolean delete(final UUID id) {
        transaction.begin();

        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaDelete<Tare> tareCriteriaDelete = criteriaBuilder.createCriteriaDelete(Tare.class);
        final Root<Tare> tareRoot = tareCriteriaDelete.from(Tare.class);
        tareCriteriaDelete.where(criteriaBuilder.equal(tareRoot.get("id"), id));
        final int executeUpdate = entityManager.createQuery(tareCriteriaDelete).executeUpdate();

        transaction.commit();

        return executeUpdate > 0;
    }

    @Override
    public List<Tare> getTaresByUserId(final UUID userId) {
        transaction.begin();

        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Tare> tareCriteriaQuery = criteriaBuilder.createQuery(Tare.class);
        final Root<User> userRoot = tareCriteriaQuery.from(User.class);
        tareCriteriaQuery.select(userRoot.get("tares"))
                .where(criteriaBuilder.equal(userRoot.get("id"), userId));
        final List<Tare> tares = entityManager.createQuery(tareCriteriaQuery).getResultList();

        transaction.commit();

        return tares;
    }

    @Override
    public Optional<Tare> getById(final UUID id) {
        final Tare tare = entityManager.find(Tare.class, id);
        return Optional.ofNullable(tare);
    }
}
