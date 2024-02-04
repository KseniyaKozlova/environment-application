package by.itacademy.services.coupon;

import by.itacademy.dto.request.CreateCouponRequestDto;
import by.itacademy.dto.request.UpdateCouponRequestDto;
import by.itacademy.entities.Coupon;

import java.util.List;
import java.util.UUID;

public interface CouponService {

    Coupon saveCoupon(CreateCouponRequestDto couponRequestDto);

    List<Coupon> readCoupons();

    Coupon updateCoupon(UUID id, UpdateCouponRequestDto couponRequestDto);

    void deleteCoupon(UUID id);

    Coupon getCouponById(UUID id);

//    List<Coupon> getCouponsByUserId(UUID id);

    List<Coupon> getCouponsByCompanyId(UUID companyId);
}
