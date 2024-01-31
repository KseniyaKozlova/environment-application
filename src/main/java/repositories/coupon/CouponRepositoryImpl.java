package repositories.coupon;

import entities.Company;
import entities.Coupon;
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

public class CouponRepositoryImpl implements CouponRepository {

    private static CouponRepository couponRepository;
    private final EntityManager entityManager = JPAUtil.getEntityManager();
    private final EntityTransaction transaction = entityManager.getTransaction();


    private CouponRepositoryImpl() {
    }

    public static CouponRepository getInstance() {
        return Objects.requireNonNullElseGet(couponRepository, () -> couponRepository = new CouponRepositoryImpl());
    }

    @Override
    public Coupon create(final Coupon coupon) {
        transaction.begin();

        entityManager.persist(coupon);

        transaction.commit();

        return coupon;
    }

    @Override
    public List<Coupon> read() {
        transaction.commit();

        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Coupon> couponCriteriaQuery = criteriaBuilder.createQuery(Coupon.class);
        final Root<Coupon> couponRoot = couponCriteriaQuery.from(Coupon.class);
        couponCriteriaQuery.select(couponRoot);
        final List<Coupon> coupons = entityManager.createQuery(couponCriteriaQuery).getResultList();

        transaction.commit();

        return coupons;
    }

    @Override
    public Optional<Coupon> update(final UUID id, final Coupon coupon) {
        return getById(id);
    }

    @Override
    public boolean delete(final UUID id) {
        transaction.begin();

        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaDelete<Coupon> couponCriteriaDelete = criteriaBuilder.createCriteriaDelete(Coupon.class);
        final Root<Coupon> couponRoot = couponCriteriaDelete.from(Coupon.class);
        couponCriteriaDelete.where(criteriaBuilder.equal(couponRoot.get("id"), id));
        final int executeUpdate = entityManager.createQuery(couponCriteriaDelete).executeUpdate();

        transaction.commit();

        return executeUpdate > 0;
    }

    @Override
    public List<Coupon> getCouponsByUserId(final UUID id) {
        transaction.begin();

        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Coupon> couponCriteriaQuery = criteriaBuilder.createQuery(Coupon.class);
        final Root<User> userRoot = couponCriteriaQuery.from(User.class);
        couponCriteriaQuery.select(userRoot.get("coupons"))
                .where(criteriaBuilder.equal(userRoot.get("id"), id));
        final List<Coupon> coupons = entityManager.createQuery(couponCriteriaQuery).getResultList();

        transaction.commit();

        return coupons;
    }

    @Override
    public List<Coupon> getCouponsByCompanyId(final UUID companyId) {
        transaction.begin();

        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Coupon> couponCriteriaQuery = criteriaBuilder.createQuery(Coupon.class);
        final Root<Company> companyRoot = couponCriteriaQuery.from(Company.class);
        couponCriteriaQuery.select(companyRoot.get("coupons"))
                .where(criteriaBuilder.equal(companyRoot.get("id"), companyId));
        final List<Coupon> coupons = entityManager.createQuery(couponCriteriaQuery).getResultList();

        transaction.commit();

        return coupons;
    }

    @Override
    public Optional<Coupon> getById(UUID id) {
        final Coupon coupon = entityManager.find(Coupon.class, id);
        return Optional.ofNullable(coupon);
    }
}
