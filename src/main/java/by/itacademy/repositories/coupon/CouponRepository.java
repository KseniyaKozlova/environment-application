package by.itacademy.repositories.coupon;

import by.itacademy.entities.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
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
