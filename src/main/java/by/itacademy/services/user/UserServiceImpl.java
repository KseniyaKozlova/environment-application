package by.itacademy.services.user;

import by.itacademy.dto.request.CreateUserRequestDto;
import by.itacademy.dto.request.UpdateUserRequestDto;
import by.itacademy.dto.response.UserResponseDto;
import by.itacademy.entities.Coupon;
import by.itacademy.entities.Tare;
import by.itacademy.entities.User;
import lombok.RequiredArgsConstructor;
import by.itacademy.mappers.UserMapper;
import org.springframework.stereotype.Service;
import by.itacademy.repositories.user.UserRepository;
import by.itacademy.services.coupon.CouponService;
import by.itacademy.services.exceptions.CouponServiceException;
import by.itacademy.services.exceptions.UserServiceException;
import by.itacademy.services.tare.TareService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final TareService tareService;
    private final CouponService couponService;

    @Override
    public UserResponseDto saveUser(CreateUserRequestDto userRequestDto) {
        final User user = userMapper.mapToUser(userRequestDto);
        final User savedUser = userRepository.save(user);
        return userMapper.mapToUserResponse(savedUser);
    }

    @Override
    public UserResponseDto update(final UUID id, final UpdateUserRequestDto userRequestDto) {
        final User userToUpdate = getUserById(id);
        userMapper.updateUser(userRequestDto, userToUpdate);
        final User updatedUser = userRepository.save(userToUpdate);
        return userMapper.mapToUserResponse(updatedUser);
    }

    @Override
    public void deleteUser(final UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserResponseDto getUserByLogin(final String login) {
        final User user = userRepository.getUserByLogin(login)
                .orElseThrow(() -> new UserServiceException("User doesn't exist"));
        return userMapper.mapToUserResponse(user);
    }

//    @Override
//    public boolean isUserPresent(final String login, final String password) {
//        return userRepository.getUserByLogin(login)
//                .map(value -> value.getPassword().equals(password))
//                .orElse(false);
//    }

//    @Override
//    public boolean isLoginExist(final String login) {
//        return userRepository.getUserByLogin(login).isPresent();
//    }

    @Override
    public UserResponseDto buyCoupon(final UUID userId, final UUID couponId) {
        final User user = getUserById(userId);
        final Coupon coupon = couponService.getCouponById(couponId);

        final Integer userBonuses = user.getBonuses();
        final Integer couponCost = coupon.getCost();
        final boolean isBonusesEnough = (userBonuses - couponCost) >= 0;

        if (isBonusesEnough) {
            return buyCouponByUser(user, coupon, userBonuses, couponCost);
        } else {
            throw new UserServiceException("You don't have enough bonuses to buy this coupon: " + coupon);
        }
    }

    @Override
    public UserResponseDto depositTare(final UUID userId, final UUID tareId) {
        final User user = getUserById(userId);
        final Tare tare = tareService.getTareById(tareId);

        final Integer initialBonusesAmount = user.getBonuses();
        final Integer bonusesToAccounting = tare.getBonusesToAccounting();
        final Integer refreshedBonusesAmount = initialBonusesAmount + bonusesToAccounting;
        user.setBonuses(refreshedBonusesAmount);

        user.addTare(tare);
        final User updatedUser = userRepository.save(user);
        return userMapper.mapToUserResponse(updatedUser);
    }

    @Override
    public UserResponseDto useCoupon(final UUID userId, final UUID couponId) {
        final User user = getUserById(userId);
        final Coupon coupon = user.getCoupons().stream()
                .filter(userCoupon -> userCoupon.getId().equals(couponId))
                .findFirst()
                .orElseThrow(() -> new CouponServiceException("You hadn't bought this coupon"));
        user.removeCoupon(coupon);
        final User updatedUser = userRepository.save(user);
        return userMapper.mapToUserResponse(updatedUser);
    }

    @Override
    public UserResponseDto getById(final UUID id) {
        final User user = userRepository.findById(id)
                .orElseThrow(() -> new UserServiceException("User doesn't exist"));
        return userMapper.mapToUserResponse(user);
    }

    private UserResponseDto buyCouponByUser(final User user, final Coupon coupon, final Integer userBonuses, final Integer couponCost) {
        final Integer accountBalance = userBonuses - couponCost;
        user.setBonuses(accountBalance);

        user.addCoupon(coupon);
        userRepository.save(user);
        final User updatedUser = userRepository.save(user);
        return userMapper.mapToUserResponse(updatedUser);
    }

    private User getUserById(final UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserServiceException("User doesn't exist"));
    }
}
