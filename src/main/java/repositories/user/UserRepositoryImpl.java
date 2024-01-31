package repositories.user;

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

public class UserRepositoryImpl implements UserRepository {

    private static UserRepository userRepository;
    private final EntityManager entityManager = JPAUtil.getEntityManager();
    private final EntityTransaction transaction = entityManager.getTransaction();

    private UserRepositoryImpl() {
    }

    public static UserRepository getInstance() {
        return Objects.requireNonNullElseGet(userRepository, () -> userRepository = new UserRepositoryImpl());
    }

    @Override
    public User create(final User user) {
        transaction.begin();

        entityManager.persist(user);

        transaction.commit();

        return user;
    }

    public List<User> read() {
        transaction.begin();

        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<User> userCriteriaQuery = criteriaBuilder.createQuery(User.class);
        final Root<User> userRoot = userCriteriaQuery.from(User.class);
        userCriteriaQuery.select(userRoot);
        final List<User> users = entityManager.createQuery(userCriteriaQuery).getResultList();

        transaction.commit();

        return users;
    }

    @Override
    public Optional<User> update(final UUID id, final User user) {
        return getById(id);
    }

    @Override
    public Optional<User> getUserByLogin(final String login) {
        transaction.begin();

        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<User> userCriteriaQuery = criteriaBuilder.createQuery(User.class);
        final Root<User> userRoot = userCriteriaQuery.from(User.class);
        userCriteriaQuery.select(userRoot).where(criteriaBuilder.equal(userRoot.get("LOGIN"), login));
        final User user = entityManager.createQuery(userCriteriaQuery)
                .getSingleResult();

        transaction.commit();

        return Optional.ofNullable(user);
    }

    @Override
    public boolean delete(final UUID id) {
        transaction.begin();

        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaDelete<User> userCriteriaDelete = criteriaBuilder.createCriteriaDelete(User.class);
        final Root<User> userRoot = userCriteriaDelete.from(User.class);
        userCriteriaDelete.where(criteriaBuilder.equal(userRoot.get("id"), id));
        final int executeUpdate = entityManager.createQuery(userCriteriaDelete).executeUpdate();

        transaction.commit();

        return executeUpdate > 0;
    }

    @Override
    public Optional<User> getById(UUID id) {
        final User user = entityManager.find(User.class, id);
        return Optional.ofNullable(user);
    }
}
