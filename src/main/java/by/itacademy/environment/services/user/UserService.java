package by.itacademy.environment.services.user;

import by.itacademy.environment.dto.request.CreateUserRequestDto;
import by.itacademy.environment.dto.request.UpdateUserRequestDto;
import by.itacademy.environment.dto.response.UserResponseDto;
import by.itacademy.environment.entities.User;

import java.util.UUID;

/**
 * User processing service
 */
public interface UserService {

    /**
     * Save user in DB with its coupons, if they are need
     *
     * @param userRequestDto request for user creating
     * @return user in response view
     */
    UserResponseDto saveUser(CreateUserRequestDto userRequestDto);

    /**
     * Update user in DB by id
     *
     * @param id             user id
     * @param userRequestDto request for user with fields for updating
     * @return updated user in response view
     */
    UserResponseDto updateUser(UUID id, UpdateUserRequestDto userRequestDto);

    /**
     * Delete user in DB by id
     *
     * @param id user id
     */
    void deleteUser(UUID id);

    /**
     * Get user from DB by login
     *
     * @param login user login
     * @return user in response view
     */
    UserResponseDto getUserByLogin(String login);

    /**
     * Buy indicated coupon for user
     *
     * @param userId   user id, who buys coupon
     * @param couponId coupon id for buying
     * @return user in response view
     */
    UserResponseDto buyCoupon(UUID userId, UUID couponId);

    /**
     * Use indicated coupon in shop without money back
     *
     * @param userId   user id, who uses coupon
     * @param couponId coupon id for using
     * @return user in response view
     */
    UserResponseDto useCoupon(UUID userId, UUID couponId);

    /**
     * Get user from DB by id
     *
     * @param id user id
     * @return user in response view
     */
    UserResponseDto getUserResponseById(UUID id);

    /**
     * Return indicated coupon to the user with money back
     *
     * @param userId   user id, who returns coupon
     * @param couponId coupon id for returning
     * @return user in response view
     */
    UserResponseDto returnCoupon(UUID userId, UUID couponId);

    /**
     * Get user from DB by id
     *
     * @param id user id
     * @return entity
     */
    User getUserById(UUID id);
}
