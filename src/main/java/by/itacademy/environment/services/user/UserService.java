package by.itacademy.environment.services.user;

import by.itacademy.environment.dto.request.CreateUserRequestDto;
import by.itacademy.environment.dto.request.UpdateUserRequestDto;
import by.itacademy.environment.dto.response.UserResponseDto;

import java.util.UUID;

public interface UserService {

    UserResponseDto saveUser(CreateUserRequestDto userRequestDto);

    UserResponseDto update(UUID id, UpdateUserRequestDto userRequestDto);

    void deleteUser(UUID id);

    UserResponseDto getUserByLogin(String login);

    UserResponseDto buyCoupon(UUID userId, UUID couponId);

    UserResponseDto depositTare(UUID userId, UUID tareId);

    UserResponseDto useCoupon(UUID userId, UUID couponId);

    UserResponseDto getUserResponseById(UUID id);

    UserResponseDto returnCoupon(UUID userId, UUID couponId);
}
