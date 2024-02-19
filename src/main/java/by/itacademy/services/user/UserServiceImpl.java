package by.itacademy.services.user;

import by.itacademy.dto.request.CreateUserRequestDto;
import by.itacademy.dto.request.UpdateUserRequestDto;
import by.itacademy.dto.response.UserResponseDto;
import by.itacademy.entities.Coupon;
import by.itacademy.entities.Tare;
import by.itacademy.entities.User;
import by.itacademy.exceptions.DisabledCouponException;
import by.itacademy.exceptions.notFound.UserByLoginNotFoundException;
import by.itacademy.exceptions.notFound.UserNotFoundException;
import by.itacademy.mappers.UserMapper;
import by.itacademy.repositories.user.UserRepository;
import by.itacademy.services.coupon.CouponService;
import by.itacademy.services.tare.TareService;
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
    public UserResponseDto updateUser(final UUID id, final UpdateUserRequestDto userRequestDto) {
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
