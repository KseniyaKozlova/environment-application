package services.coupon;

import entities.Coupon;
import repositories.coupon.CouponRepository;
import repositories.coupon.CouponRepositoryImpl;
import services.exceptions.CouponServiceException;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class CouponServiceImpl implements CouponService {

    private static CouponService couponService;
    private final CouponRepository couponRepository = CouponRepositoryImpl.getInstance();

    private CouponServiceImpl() {
    }

    public static CouponService getInstance() {
        return Objects.requireNonNullElseGet(couponService, () -> couponService = new CouponServiceImpl());
    }

    @Override
    public Coupon create(final Coupon coupon) {
        return couponRepository.create(coupon);
    }

    @Override
    public List<Coupon> read() {
        return couponRepository.read();
    }

    @Override
    public Coupon update(final UUID id, final Coupon coupon) {
        return couponRepository.update(id, coupon).orElseThrow(() -> new CouponServiceException("This coupon can't be updated"))
                .setCost(coupon.getCost())
                .setCompany(coupon.getCompany())
                .setDescription(coupon.getDescription());
    }

    @Override
    public boolean delete(final UUID id) {
        return couponRepository.delete(id);
    }
}
