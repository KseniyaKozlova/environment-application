package by.itacademy.services.user;

import by.itacademy.dto.request.CreateUserRequestDto;
import by.itacademy.dto.request.UpdateUserRequestDto;
import by.itacademy.dto.response.UserResponseDto;

import java.util.UUID;

public interface UserService {

    UserResponseDto saveUser(CreateUserRequestDto userRequestDto);

    UserResponseDto update(UUID id, UpdateUserRequestDto userRequestDto);

    void deleteUser(UUID id);

    UserResponseDto getUserByLogin(String login);

//    boolean isUserPresent(String login, String password);  //TODO : delete 2 methods?

//    boolean isLoginExist(String login);

    UserResponseDto buyCoupon(UUID userId, UUID couponId);

    UserResponseDto depositTare(UUID userId, UUID tareId);

    UserResponseDto useCoupon(UUID userId, UUID couponId);

    UserResponseDto getById(UUID id);
}
