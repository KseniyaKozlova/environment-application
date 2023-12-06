package mapper;

import entity.Coupon;

import javax.servlet.http.HttpServletRequest;

import static util.Constants.COUPON_COST;
import static util.Constants.COUPON_DESCRIPTION;

public class CouponMapper {

    public Coupon build(HttpServletRequest request) {
        return Coupon.builder()
                .description(request.getParameter(COUPON_DESCRIPTION))
                .cost(Integer.parseInt(request.getParameter(COUPON_COST)))
                .build();
    }
}
