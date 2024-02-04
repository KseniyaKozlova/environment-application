package by.itacademy.controllers;

import by.itacademy.dto.request.CreateCouponRequestDto;
import by.itacademy.dto.request.UpdateCouponRequestDto;
import by.itacademy.dto.response.CouponResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import by.itacademy.services.coupon.CouponService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/version1")
@RequiredArgsConstructor
public class CouponController {

    private final CouponService couponService;

    @PostMapping(path = "/coupon")
    public CouponResponseDto saveCoupon(@RequestBody CreateCouponRequestDto couponRequestDto) {
        return couponService.saveCoupon(couponRequestDto);
    }

    @GetMapping(path = "/coupon/{id}")
    public CouponResponseDto getCouponById(@PathVariable(name = "id") UUID id) {
        return couponService.getById(id);
    }

    @GetMapping(path = "/coupons")
    public List<CouponResponseDto> getAllCoupons() {
        return couponService.readCoupons();
    }

//    @GetMapping(path = "/coupons/user/{id}")
//    public List<Coupon> getCouponsByUserId(@PathVariable(name = "id") UUID id) {
//        return couponService.getCouponsByUserId(id);
//    }

    @GetMapping(path = "/coupons/user/{id}")
    public List<CouponResponseDto> getCouponsByCompanyId(@PathVariable(name = "id") UUID id) {
        return couponService.getCouponsByCompanyId(id);
    }

    @PutMapping(path = "/coupon/update/{id}")
    public CouponResponseDto updateCoupon(@PathVariable(name = "id") UUID id, @RequestBody UpdateCouponRequestDto couponRequestDto) {
        return couponService.updateCoupon(id, couponRequestDto);
    }

    @DeleteMapping(path = "/coupon/delete/{id}")
    public void deleteCoupon(@PathVariable(name = "id") UUID id) {
        couponService.deleteCoupon(id);
    }
}
