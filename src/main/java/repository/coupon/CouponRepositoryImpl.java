package repository.coupon;

import entity.Company;
import entity.Coupon;

import java.util.*;

public class CouponRepositoryImpl implements CouponRepository {

    private static CouponRepository couponRepository;
    private final List<Coupon> coupons;

    {
        final Company sosedi = new Company(UUID.randomUUID(), "Product shop Sosedi", "BIK: AKBB");

        coupons = new ArrayList<>();
        coupons.add(new Coupon(UUID.randomUUID(), "Coupon gives discount 10% in our shop", 100, sosedi));
        coupons.add(new Coupon(UUID.randomUUID(), "You can exchange this coupon for 10 bel rubles", 200, sosedi));
    }

    private CouponRepositoryImpl() {
    }

    public static CouponRepository getInstance() {
        return Objects.requireNonNullElseGet(couponRepository, () -> couponRepository = new CouponRepositoryImpl());
    }

    @Override
    public Coupon create(final Coupon coupon) {
        coupon.setId(UUID.randomUUID());
        coupons.add(coupon);
        return coupon;
    }

    @Override
    public List<Coupon> read() {
        return coupons;
    }

    @Override
    public Optional<Coupon> update(final UUID id, final Coupon coupon) {
        Optional<Coupon> couponOptional = getById(id);
        couponOptional.ifPresent(couponToUpdate -> couponToUpdate
                .setCost(coupon.getCost())
                .setCompany(coupon.getCompany())
                .setDescription(coupon.getDescription()));

        return couponOptional;
    }

    @Override
    public boolean delete(final UUID id) {
        Optional<Coupon> couponOptional = getById(id);
        return couponOptional.map(coupons::remove)
                .orElse(false);
    }

    private Optional<Coupon> getById(UUID id) {
        return coupons.stream()
                .filter(coupon -> coupon.getId().equals(id))
                .findFirst();
    }
}
