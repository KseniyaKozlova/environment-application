package services.user;

import entities.Coupon;
import entities.Tare;
import entities.User;
import repositories.user.UserRepository;
import repositories.user.UserRepositoryImpl;
import services.coupon.CouponService;
import services.coupon.CouponServiceImpl;
import services.exceptions.CouponServiceException;
import services.exceptions.UserServiceException;
import services.tare.TareService;
import services.tare.TareServiceImpl;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class UserServiceImpl implements UserService {

    private static UserService userService;

    private final UserRepository userRepository = UserRepositoryImpl.getInstance();
    private final TareService tareService = TareServiceImpl.getInstance();
    private final CouponService couponService = CouponServiceImpl.getInstance();

    private UserServiceImpl() {
    }

    public static UserService getInstance() {
        return Objects.requireNonNullElseGet(userService, () -> userService = new UserServiceImpl());
    }

    @Override
    public User create(User user) {
        return userRepository.create(user);
    }

    @Override
    public List<User> read() {
        return userRepository.read();
    }

    @Override
    public User update(final UUID id, final User user) {
        final User updatedUser = userRepository.update(id, user).orElseThrow(() -> new UserServiceException("This user can't be updated"))
                .setLogin(user.getLogin())
                .setPassword(user.getPassword())
                .setName(user.getName())
                .setRole(user.getRole());
        return userRepository.create(updatedUser);
    }

    @Override
    public boolean delete(final UUID id) {
        return userRepository.delete(id);
    }

    @Override
    public User getUserByLogin(final String login) {
        return userRepository.getUserByLogin(login).orElseThrow(
                () -> new UserServiceException("User doesn't exist")
        );
    }

    @Override
    public boolean isUserPresent(final String login, final String password) {
        return userRepository.getUserByLogin(login).map(value -> value.getPassword().equals(password))
                .orElse(false);
    }

    @Override
    public boolean isLoginExist(final String login) {
        return userRepository.getUserByLogin(login).isPresent();
    }

    @Override
    public Coupon buyCoupon(final UUID userId, final UUID couponId) {
        final User user = getById(userId);
        final Coupon coupon = couponService.getById(couponId);

        final Integer userBonuses = user.getBonuses();
        final Integer couponCost = coupon.getCost();

        if (userBonuses >= couponCost) {
            final Integer accountBalance = userBonuses - couponCost;
            user.setBonuses(accountBalance);

            user.addCoupon(coupon);
            userRepository.create(user);
            return coupon;
        } else {
            throw new UserServiceException("You don't have enough bonuses to buy this coupon: " + coupon);
        }
    }

    @Override
    public Tare depositTare(final UUID userId, final UUID tareId) {
        final User user = getById(userId);
        final Tare tare = tareService.getById(tareId);

        final Integer initialBonusesAmount = user.getBonuses();
        final Integer bonusesToAccounting = tare.getBonusesToAccounting();
        final Integer refreshedBonusesAmount = initialBonusesAmount + bonusesToAccounting;
        user.setBonuses(refreshedBonusesAmount);

        user.addTare(tare);
        userRepository.create(user);
        return tare;
    }

    @Override
    public Coupon useCoupon(final UUID userId, final UUID couponId) {
        final User user = getById(userId);
        user.getCoupons().forEach(System.out::println);
        final Coupon coupon = user.getCoupons().stream()
                .filter(userCoupon -> userCoupon.getId().equals(couponId))
                .findFirst()
                .orElseThrow(() -> new CouponServiceException("You hadn't bought this coupon"));
        user.removeCoupon(coupon);
        userRepository.create(user);
        return coupon;
    }

    private User getById(final UUID id) {
        return userRepository.getById(id).orElseThrow(() -> new UserServiceException("User doesn't exist"));
    }
}
