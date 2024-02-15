package repositories.coupon;

import entities.Company;
import entities.Coupon;

import java.util.*;

import static java.util.UUID.randomUUID;

public class CouponRepositoryImpl implements CouponRepository {

    private static CouponRepository couponRepository;
    private final List<Coupon> coupons;

    {
        final Company sosedi = new Company(randomUUID(), "Product shop Sosedi", "BIK: AKBB");

        coupons = new ArrayList<>();
        coupons.add(new Coupon(randomUUID(), "Coupon gives discount 10% in our shop", 100, sosedi));
        coupons.add(new Coupon(randomUUID(), "You can exchange this coupon for 10 bel rubles", 200, sosedi));
    }

    private CouponRepositoryImpl() {
    }

    public static CouponRepository getInstance() {
        return Objects.requireNonNullElseGet(couponRepository, () -> couponRepository = new CouponRepositoryImpl());
    }

    @Override
    public Coupon create(final Coupon coupon) {
        coupon.setId(randomUUID());
        coupons.add(coupon);
        return coupon;
    }

    @Override
    public List<Coupon> read() {
        return coupons;
    }

    @Override
    public Optional<Coupon> update(final UUID id, final Coupon coupon) {
        return getById(id);
    }

    @Override
    public boolean delete(final UUID id) {
        return getById(id).map(coupons::remove)
                .orElse(false);
    }

    private Optional<Coupon> getById(UUID id) {
        return coupons.stream()
                .filter(coupon -> coupon.getId().equals(id))
                .findFirst();
    }
}
