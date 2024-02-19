package by.itacademy.repositories.coupon;

import by.itacademy.entities.Coupon;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

import static by.itacademy.entities.QCoupon.coupon;

public class CouponRepositoryCustomImpl extends QuerydslRepositorySupport implements CouponRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    public CouponRepositoryCustomImpl(final JPAQueryFactory jpaQueryFactory) {
        super(Coupon.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<Coupon> findCouponsByCostSmallerThanUserBonuses(final Integer userBonuses) {
        return jpaQueryFactory.select(coupon)
                .from(coupon)
                .where(coupon.cost.loe(userBonuses))
                .orderBy(coupon.cost.asc())
                .fetch();
    }
}
