package by.itacademy.controllers;

import by.itacademy.dto.request.CreateCouponRequestDto;
import by.itacademy.dto.request.UpdateCouponRequestDto;
import by.itacademy.dto.response.CouponResponseDto;
import by.itacademy.services.coupon.CouponService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static by.itacademy.util.Constants.*;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(path = START_PART_URL)
@RequiredArgsConstructor
public class CouponController {

    private final CouponService couponService;

    @ResponseStatus(CREATED)
    @PostMapping(path = SAVE_COUPON_URL)
    public CouponResponseDto saveCoupon(@RequestBody @Valid CreateCouponRequestDto couponRequestDto) {
        return couponService.saveCoupon(couponRequestDto);
    }

    @GetMapping(path = GET_COUPON_URL)
    public CouponResponseDto getCouponById(@PathVariable(value = ID) UUID id) {
        return couponService.getCouponResponseById(id);
    }

    @GetMapping(path = GET_COUPONS_URL)
    public List<CouponResponseDto> getAllCoupons() {
        return couponService.readCoupons();
    }

    @GetMapping(path = GET_USER_COUPONS_URL)
    public List<CouponResponseDto> getCouponsByUserId(@PathVariable(value = ID) UUID id) {
        return couponService.getCouponsByUserId(id);
    }

    @GetMapping(path = GET_COMPANY_COUPONS_URL)
    public List<CouponResponseDto> getCouponsByCompanyId(@PathVariable(value = ID) UUID id) {
        return couponService.getCouponsByCompanyId(id);
    }

    @GetMapping(path = GET_AVAILABLE_USER_COUPONS_URL)
    public List<CouponResponseDto> getAvailableUserCoupons(@PathVariable(value = ID) UUID id) {
        return couponService.getAvailableCoupons(id);
    }

    @PatchMapping(path = UPDATE_COUPON_URL)
    public CouponResponseDto updateCoupon(@PathVariable(value = ID) UUID id, @RequestBody @Valid UpdateCouponRequestDto couponRequestDto) {
        return couponService.updateCoupon(id, couponRequestDto);
    }

    @DeleteMapping(path = DELETE_COUPON_URL)
    public void deleteCoupon(@PathVariable(value = ID) UUID id) {
        couponService.deleteCoupon(id);
    }
}
