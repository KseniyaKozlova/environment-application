package by.itacademy.controllers.user;

import by.itacademy.dto.request.CreateUserRequestDto;
import by.itacademy.dto.request.UpdateUserRequestDto;
import by.itacademy.entities.Coupon;
import by.itacademy.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import by.itacademy.services.user.UserService;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/version1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(path = "/user/{id}")
    public User getUserById(@PathVariable UUID id) {
        return userService.getById(id);
    }

    @GetMapping(path = "/user/login/{id}")
    public User getUserByLogin(@PathVariable String login) {
        return userService.getUserByLogin(login);
    }

    @PostMapping(path = "/user")
    public User saveUser(@RequestBody CreateUserRequestDto userRequestDto) {
        return userService.saveUser(userRequestDto);
    }

    @PutMapping(path = "/user/updateTare/{id}")
    public User updateUser(@PathVariable UUID id, @RequestBody UpdateUserRequestDto userRequestDto) {
        return userService.update(id, userRequestDto);
    }

    @DeleteMapping(path = "/user/delete/{id}")
    public void deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
    }

    @PutMapping(path = "/user/{userId}/deposit_tare/{tareId}")
    public User depositTareByUser(@PathVariable UUID userId, @PathVariable UUID tareId) {
        return userService.depositTare(userId, tareId);
    }

    @PutMapping(path = "/user/{userId}/buy_coupon/{couponId}")
    public Coupon buyCouponByUser(@PathVariable UUID userId, @PathVariable UUID couponId) {
        return userService.buyCoupon(userId, couponId);
    }

    @PutMapping(path = "/user/{userId}/use_coupon/{couponId}")
    public Coupon useCouponByUser(@PathVariable UUID userId, @PathVariable UUID couponId) {
        return userService.useCoupon(userId, couponId);
    }
}
