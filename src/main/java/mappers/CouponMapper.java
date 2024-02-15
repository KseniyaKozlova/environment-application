package mappers;

import entities.Coupon;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

import static java.lang.Integer.parseInt;
import static util.Constants.COUPON_COST;
import static util.Constants.COUPON_DESCRIPTION;

public class CouponMapper {

    private static CouponMapper couponMapper;

    private CouponMapper() {
    }

    public static CouponMapper getInstance() {
        return Objects.requireNonNullElseGet(couponMapper, () -> couponMapper = new CouponMapper());
    }

    public Coupon build(HttpServletRequest request) {
        return Coupon.builder()
                .description(request.getParameter(COUPON_DESCRIPTION))
                .cost(parseInt(request.getParameter(COUPON_COST)))
                .build();
    }
}
