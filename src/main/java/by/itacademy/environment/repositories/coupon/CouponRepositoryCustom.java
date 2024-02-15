package by.itacademy.environment.repositories.coupon;

import by.itacademy.environment.entities.Coupon;

import java.util.List;

public interface CouponRepositoryCustom {

    List<Coupon> findCouponsByCostSmallerThanUserBonuses(Integer userBonuses);
}
