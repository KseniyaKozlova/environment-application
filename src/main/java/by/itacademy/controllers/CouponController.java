package by.itacademy.controllers;

import by.itacademy.dto.request.CreateCouponRequestDto;
import by.itacademy.dto.request.UpdateCouponRequestDto;
import by.itacademy.dto.response.CouponResponseDto;
import by.itacademy.services.coupon.CouponService;
import by.itacademy.util.Constants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(path = Constants.START_PART_URL)
@RequiredArgsConstructor
public class CouponController {

    private final CouponService couponService;

    @ResponseStatus(CREATED)
    @PostMapping(path = Constants.SAVE_COUPON_URL)
    public CouponResponseDto saveCoupon(@RequestBody @Valid CreateCouponRequestDto couponRequestDto) {
        return couponService.saveCoupon(couponRequestDto);
    }

    @GetMapping(path = Constants.GET_COUPON_URL)
    public CouponResponseDto getCouponById(@PathVariable(value = Constants.ID) UUID id) {
        return couponService.getCouponResponseById(id);
    }

    @GetMapping(path = Constants.GET_COUPONS_URL)
    public List<CouponResponseDto> getAllCoupons() {
        return couponService.readCoupons();
    }

    @GetMapping(path = Constants.GET_USER_COUPONS_URL)
    public List<CouponResponseDto> getCouponsByUserId(@PathVariable(value = Constants.ID) UUID id) {
        return couponService.getCouponsByUserId(id);
    }

    @GetMapping(path = Constants.GET_COMPANY_COUPONS_URL)
    public List<CouponResponseDto> getCouponsByCompanyId(@PathVariable(value = Constants.ID) UUID id) {
        return couponService.getCouponsByCompanyId(id);
    }

    @GetMapping(path = Constants.GET_AVAILABLE_USER_COUPONS_URL)
    public List<CouponResponseDto> getAvailableUserCoupons(@PathVariable(value = Constants.ID) UUID id) {
        return couponService.getAvailableCoupons(id);
    }

    @PatchMapping(path = Constants.UPDATE_COUPON_URL)
    public CouponResponseDto updateCoupon(@PathVariable(value = Constants.ID) UUID id, @RequestBody @Valid UpdateCouponRequestDto couponRequestDto) {
        return couponService.updateCoupon(id, couponRequestDto);
    }

    @DeleteMapping(path = Constants.DELETE_COUPON_URL)
    public void deleteCoupon(@PathVariable(value = Constants.ID) UUID id) {
        couponService.deleteCoupon(id);
    }
}
