package by.itacademy.environment.services.user;

import by.itacademy.environment.dto.request.CreateUserRequestDto;
import by.itacademy.environment.dto.request.UpdateUserRequestDto;
import by.itacademy.environment.dto.response.UserResponseDto;
import by.itacademy.environment.entities.Coupon;
import by.itacademy.environment.entities.User;
import by.itacademy.environment.exceptions.DisabledCouponException;
import by.itacademy.environment.exceptions.notFound.UserByLoginNotFoundException;
import by.itacademy.environment.exceptions.notFound.UserNotFoundException;
import by.itacademy.environment.mappers.UserMapper;
import by.itacademy.environment.repositories.user.UserRepository;
import by.itacademy.environment.services.coupon.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final CouponService couponService;

    @Override
    @Transactional
    public UserResponseDto saveUser(final CreateUserRequestDto userRequestDto) {
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
        final User user = getUserById(id);
        final List<Coupon> coupons = user.getCoupons();
        coupons.forEach(coupon -> coupon.getUsers().remove(user));
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
    public UserResponseDto useCoupon(final UUID userId, final UUID couponId) {
        final User user = getUserById(userId);
        final Coupon coupon = getCoupon(couponId, userId);
        user.useCoupon(coupon);
        final User updatedUser = userRepository.save(user);
        return userMapper.mapToUserResponse(updatedUser);
    }

    @Override
    @Transactional
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

    @Override
    public User getUserById(final UUID id) {
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
