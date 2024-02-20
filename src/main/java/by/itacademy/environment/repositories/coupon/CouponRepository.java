package by.itacademy.environment.repositories.coupon;

import by.itacademy.environment.entities.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CouponRepository extends JpaRepository<Coupon, UUID>, CouponRepositoryCustom {

    /**
     * Get all coupons from DB of determined user
     *
     * @param id user id, whose coupons are need
     * @return list of coupons
     */
    List<Coupon> findCouponsByUsersId(UUID id);

    /**
     * Get all coupons from DB of determined company
     *
     * @param id company id, whose coupons are need
     * @return list of coupons
     */
    List<Coupon> findCouponsByCompanyId(UUID id);
}
