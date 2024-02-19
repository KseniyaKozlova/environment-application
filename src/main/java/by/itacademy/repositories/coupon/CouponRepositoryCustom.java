package by.itacademy.repositories.coupon;

import by.itacademy.entities.Coupon;

import java.util.List;

public interface CouponRepositoryCustom {

    List<Coupon> findCouponsByCostSmallerThanUserBonuses(Integer userBonuses);
}
