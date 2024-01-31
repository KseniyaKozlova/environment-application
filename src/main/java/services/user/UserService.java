package services.user;

import entities.Coupon;
import entities.Tare;
import entities.User;
import services.Service;

import java.util.UUID;

/**
 * some additional methods will be here
 */
public interface UserService extends Service<User> {

    User getUserByLogin(String login);

    boolean isUserPresent(String login, String password);

    boolean isLoginExist(String login);

    Coupon buyCoupon(UUID userId, UUID couponId);

    Tare depositTare(UUID userId, UUID tareId);

    Coupon useCoupon(UUID userId, UUID couponId);
}
