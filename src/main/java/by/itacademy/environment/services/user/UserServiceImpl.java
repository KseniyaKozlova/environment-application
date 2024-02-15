package by.itacademy.environment.services.user;

import by.itacademy.environment.dto.request.CreateUserRequestDto;
import by.itacademy.environment.dto.request.UpdateUserRequestDto;
import by.itacademy.environment.dto.response.UserResponseDto;
import by.itacademy.environment.entities.Coupon;
import by.itacademy.environment.entities.Tare;
import by.itacademy.environment.entities.User;
import by.itacademy.environment.exceptions.DisabledCouponException;
import by.itacademy.environment.exceptions.UserByLoginNotFoundException;
import by.itacademy.environment.exceptions.UserNotFoundException;
import by.itacademy.environment.mappers.UserMapper;
import by.itacademy.environment.repositories.user.UserRepository;
import by.itacademy.environment.services.coupon.CouponService;
import by.itacademy.environment.services.tare.TareService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final TareService tareService;
    private final CouponService couponService;

    @Override
    @Transactional
    public UserResponseDto saveUser(CreateUserRequestDto userRequestDto) {
        final User user = userMapper.mapToUser(userRequestDto);
        final User savedUser = userRepository.save(user);
        return userMapper.mapToUserResponse(savedUser);
    }

    @Override
    @Transactional
    public UserResponseDto update(final UUID id, final UpdateUserRequestDto userRequestDto) {
        final User userToUpdate = getUserById(id);
        userMapper.updateUser(userRequestDto, userToUpdate);
        final User updatedUser = userRepository.save(userToUpdate);
        return userMapper.mapToUserResponse(updatedUser);
    }

    @Override
    @Transactional
    public void deleteUser(final UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDto getUserByLogin(final String login) {
        final User user = userRepository.findUserByLogin(login)
                .orElseThrow(() -> new UserByLoginNotFoundException(login));
        return userMapper.mapToUserResponse(user);
    }

    @Override
    @Transactional
    public UserResponseDto buyCoupon(final UUID userId, final UUID couponId) {
        final User user = getUserById(userId);
        final Coupon coupon = couponService.getCouponById(couponId);
        user.addCoupon(coupon);
        final User updatedUser = userRepository.save(user);
        return userMapper.mapToUserResponse(updatedUser);
    }

    @Override
    @Transactional
    public UserResponseDto depositTare(final UUID userId, final UUID tareId) {
        final User user = getUserById(userId);
        final Tare tare = tareService.getTareById(tareId);
        user.addTare(tare);
        final User updatedUser = userRepository.save(user);
        return userMapper.mapToUserResponse(updatedUser);
    }

    @Override
    @Transactional
    public UserResponseDto useCoupon(final UUID userId, final UUID couponId) {
        final User user = getUserById(userId);
        final Coupon coupon = getCoupon(couponId, userId);
        user.useCoupon(coupon);
        final User updatedUser = userRepository.save(user);
        return userMapper.mapToUserResponse(updatedUser);
    }

    @Override
    public UserResponseDto returnCoupon(final UUID userId, final UUID couponId) {
        final User user = getUserById(userId);
        final Coupon coupon = getCoupon(couponId, userId);
        user.removeCoupon(coupon);
        final User updatedUser = userRepository.save(user);
        return userMapper.mapToUserResponse(updatedUser);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDto getUserResponseById(final UUID id) {
        final User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id.toString()));
        return userMapper.mapToUserResponse(user);
    }

    private User getUserById(final UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id.toString()));
    }

    private Coupon getCoupon(final UUID couponId, final UUID id) {
        return couponService.getCouponsByUserIdAsCoupons(id).stream()
                .filter(userCoupon -> userCoupon.getId().equals(couponId))
                .findFirst()
                .orElseThrow(() -> new DisabledCouponException(couponId.toString()));
    }
}
