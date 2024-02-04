package by.itacademy.services.coupon;

import by.itacademy.dto.request.CreateCouponRequestDto;
import by.itacademy.dto.request.UpdateCouponRequestDto;
import by.itacademy.entities.Coupon;
import by.itacademy.mappers.CouponMapper;
import by.itacademy.repositories.coupon.CouponRepository;
import by.itacademy.services.exceptions.CouponServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;
    private final CouponMapper couponMapper;

    @Override
    public Coupon saveCoupon(final CreateCouponRequestDto couponRequestDto) {
        final Coupon coupon = couponMapper.mapToCoupon(couponRequestDto);
        return couponRepository.save(coupon);
    }

    @Override
    public List<Coupon> readCoupons() {
        return couponRepository.findAll();
    }

    @Override
    public Coupon updateCoupon(final UUID id, final UpdateCouponRequestDto couponRequestDto) {
        final Coupon couponToUpdate = getCouponById(id);
        couponMapper.updateCoupon(couponRequestDto, couponToUpdate);
        return couponRepository.save(couponToUpdate);
    }

    @Override
    public void deleteCoupon(final UUID id) {
        couponRepository.deleteById(id);
    }

    @Override
    public Coupon getCouponById(final UUID id) {
        return couponRepository.findById(id).orElseThrow(() -> new CouponServiceException("This coupon doesn't exist"));
    }

//    @Override
//    public List<Coupon> getCouponsByUserId(final UUID id) {
//
//        return couponRepository.findCouponsByUsers(userId);
//    }
//
    @Override
    public List<Coupon> getCouponsByCompanyId(final UUID companyId) {
        return couponRepository.findCouponsByCompanyId(companyId);
    }
}
