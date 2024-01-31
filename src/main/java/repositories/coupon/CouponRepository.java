package repositories.coupon;

import entities.Coupon;
import repositories.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * some additional methods will be here
 */
public interface CouponRepository extends Repository<Coupon> {

    List<Coupon> getCouponsByUserId(final UUID id);

    List<Coupon> getCouponsByCompanyId(UUID id);

    Optional<Coupon> getById(UUID id);
}
