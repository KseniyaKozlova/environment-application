package by.itacademy.controllers.user;

import by.itacademy.dto.request.CreateUserRequestDto;
import by.itacademy.dto.request.UpdateUserRequestDto;
import by.itacademy.dto.response.UserResponseDto;
import by.itacademy.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/version1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(path = "/user/{id}")
    public UserResponseDto getUserById(@PathVariable(name = "id") UUID id) {
        return userService.getById(id);
    }

    @GetMapping(path = "/user/login/{id}")
    public UserResponseDto getUserByLogin(@PathVariable(name = "id") String login) {
        return userService.getUserByLogin(login);
    }

    @PostMapping(path = "/user")
    public UserResponseDto saveUser(@RequestBody CreateUserRequestDto userRequestDto) {
        return userService.saveUser(userRequestDto);
    }

    @PutMapping(path = "/user/updateTare/{id}")
    public UserResponseDto updateUser(@PathVariable(name = "id") UUID id,
                                      @RequestBody UpdateUserRequestDto userRequestDto) {
        return userService.update(id, userRequestDto);
    }

    @DeleteMapping(path = "/user/delete/{id}")
    public void deleteUser(@PathVariable(name = "id") UUID id) {
        userService.deleteUser(id);
    }

    @PutMapping(path = "/user/{userId}/deposit_tare/{tareId}")
    public UserResponseDto depositTareByUser(@PathVariable(name = "userId") UUID userId,
                                             @PathVariable(name = "tareId") UUID tareId) {
        return userService.depositTare(userId, tareId);
    }

    @PutMapping(path = "/user/{userId}/buy_coupon/{couponId}")
    public UserResponseDto buyCouponByUser(@PathVariable(name = "userId") UUID userId,
                                           @PathVariable(name = "couponId") UUID couponId) {
        return userService.buyCoupon(userId, couponId);
    }

    @PutMapping(path = "/user/{userId}/use_coupon/{couponId}")
    public UserResponseDto useCouponByUser(@PathVariable(name = "userId") UUID userId,
                                           @PathVariable(name = "couponId") UUID couponId) {
        return userService.useCoupon(userId, couponId);
    }
}
