package services.coupon;

import entities.Coupon;
import services.Service;

import java.util.UUID;

/**
 * some additional methods will be here
 */
public interface CouponService extends Service<Coupon> {

    Coupon getById(UUID id);
}
