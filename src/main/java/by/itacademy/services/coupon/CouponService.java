package by.itacademy.services.coupon;

import by.itacademy.dto.request.CreateCouponRequestDto;
import by.itacademy.dto.request.UpdateCouponRequestDto;
import by.itacademy.dto.response.CouponResponseDto;
import by.itacademy.entities.Coupon;

import java.util.List;
import java.util.UUID;

/**
 * Coupon processing service
 */
public interface CouponService {

    /**
     * Save coupon in DB
     *
     * @param couponRequestDto request for coupon creating
     * @return coupon in response view
     */
    CouponResponseDto saveCoupon(CreateCouponRequestDto couponRequestDto);

    /**
     * Get all coupons from DB
     *
     * @return list of coupons in response view
     */
    List<CouponResponseDto> readCoupons();

    /**
     * Get all coupons from DB that certain user can buy
     *
     * @param id user id, for who coupons are need
     * @return list of coupons in response view
     */
    List<CouponResponseDto> getAvailableCoupons(UUID id);

    /**
     * Update coupon in DB by id
     *
     * @param id coupon id
     * @param couponRequestDto request for coupon with fields for updating
     * @return updated coupon in response view
     */
    CouponResponseDto updateCoupon(UUID id, UpdateCouponRequestDto couponRequestDto);

    /**
     * Delete coupon in DB by id
     *
     * @param id coupon id
     */
    void deleteCoupon(UUID id);

    /**
     * Get all coupons from DB of determined user
     *
     * @param id user id, whose coupons are need
     * @return list of coupons in response view
     */
    List<CouponResponseDto> getCouponsByUserId(UUID id);

    /**
     * Get coupon from DB by id
     *
     * @param id coupon id
     * @return coupon in response view
     */
    CouponResponseDto getCouponResponseById(UUID id);

    /**
     * Get all coupons from DB of determined company
     *
     * @param companyId company id, whose coupons are need
     * @return list of coupons in response view
     */
    List<CouponResponseDto> getCouponsByCompanyId(UUID companyId);

    /**
     * Get entity coupon from DB by id
     *
     * @param id coupon id
     * @return entity
     */
    Coupon getCouponById(UUID id);

    /**
     * Get all entities coupons from DB of determined user
     *
     * @param id user id, whose coupons are need
     * @return list of entities
     */
    List<Coupon> getCouponsByUserIdAsCoupons(UUID id);
}
