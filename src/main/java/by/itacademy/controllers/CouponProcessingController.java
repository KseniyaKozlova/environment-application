package by.itacademy.controllers;

import by.itacademy.dto.response.UserResponseDto;
import by.itacademy.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static by.itacademy.util.Constants.*;

@RestController
@RequestMapping(path = COUPON_PROCESSING_START_URL)
@RequiredArgsConstructor
public class CouponProcessingController {

    private final UserService userService;

    @PostMapping(path = BUY_COUPON_BY_USER_URL)
    public UserResponseDto buyCouponByUser(@PathVariable(value = USER_ID) UUID userId,
                                           @PathVariable(value = COUPON_ID) UUID couponId) {
        return userService.buyCoupon(userId, couponId);
    }

    @PostMapping(path = USE_COUPON_BY_USER_URL)
    public UserResponseDto useCouponByUser(@PathVariable(value = USER_ID) UUID userId,
                                           @PathVariable(value = COUPON_ID) UUID couponId) {
        return userService.useCoupon(userId, couponId);
    }

    @PostMapping(path = RETURN_COUPON_BY_USER_URL)
    public UserResponseDto returnCouponByUser(@PathVariable(value = USER_ID) UUID userId,
                                              @PathVariable(value = COUPON_ID) UUID couponId) {
        return userService.returnCoupon(userId, couponId);
    }
}
