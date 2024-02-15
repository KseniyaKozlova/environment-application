package by.itacademy.services.coupon;

import by.itacademy.dto.request.CreateCouponRequestDto;
import by.itacademy.dto.request.UpdateCouponRequestDto;
import by.itacademy.dto.response.CouponResponseDto;
import by.itacademy.entities.Coupon;

import java.util.List;
import java.util.UUID;

public interface CouponService {

    CouponResponseDto saveCoupon(CreateCouponRequestDto couponRequestDto);

    List<CouponResponseDto> readCoupons();

    List<CouponResponseDto> getAvailableCoupons(UUID id);

    CouponResponseDto updateCoupon(UUID id, UpdateCouponRequestDto couponRequestDto);

    void deleteCoupon(UUID id);

    List<CouponResponseDto> getCouponsByUserId(UUID id);

    CouponResponseDto getCouponResponseById(UUID id);

    List<CouponResponseDto> getCouponsByCompanyId(UUID companyId);

    Coupon getCouponById(UUID id);

    List<Coupon> getCouponsByUserIdAsCoupons(UUID id);
}
