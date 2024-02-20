package by.itacademy.environment.repositories.coupon;

import by.itacademy.environment.entities.Coupon;
import by.itacademy.environment.entities.QCoupon;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class CouponRepositoryCustomImpl extends QuerydslRepositorySupport implements CouponRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    public CouponRepositoryCustomImpl(final JPAQueryFactory jpaQueryFactory) {
        super(Coupon.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<Coupon> findCouponsByCostSmallerThanUserBonuses(final Integer userBonuses) {
        return jpaQueryFactory.select(QCoupon.coupon)
                .from(QCoupon.coupon)
                .where(QCoupon.coupon.cost.loe(userBonuses))
                .orderBy(QCoupon.coupon.cost.asc())
                .fetch();
    }
}
