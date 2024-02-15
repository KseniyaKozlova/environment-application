package by.itacademy.repositories.coupon;

import by.itacademy.entities.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, UUID>, CouponRepositoryCustom {

    List<Coupon> findCouponsByUsersId(UUID id);

    List<Coupon> findCouponsByCompanyId(UUID id);
}
