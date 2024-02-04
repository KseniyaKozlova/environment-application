package by.itacademy.repositories.coupon;

import by.itacademy.entities.Coupon;
import by.itacademy.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, UUID> {

    List<Coupon> findCouponsByUsers(User user);

    List<Coupon> findCouponsByCompanyId(UUID id);
}
