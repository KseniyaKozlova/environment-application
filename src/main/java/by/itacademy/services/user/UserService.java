package by.itacademy.services.user;

import by.itacademy.dto.request.CreateUserRequestDto;
import by.itacademy.dto.request.UpdateUserRequestDto;
import by.itacademy.entities.Coupon;
import by.itacademy.entities.User;

import java.util.UUID;

public interface UserService {

    User saveUser(CreateUserRequestDto userRequestDto);

    User update(UUID id, UpdateUserRequestDto userRequestDto);

    void deleteUser(UUID id);

    User getUserByLogin(String login);

    boolean isUserPresent(String login, String password);  //TODO : delete 2 methods?

    boolean isLoginExist(String login);

    Coupon buyCoupon(UUID userId, UUID couponId);

    User depositTare(UUID userId, UUID tareId);

    Coupon useCoupon(UUID userId, UUID couponId);

    User getById(UUID id);
}
