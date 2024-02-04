package by.itacademy.services.coupon;

import by.itacademy.dto.request.CreateCouponRequestDto;
import by.itacademy.dto.request.UpdateCouponRequestDto;
import by.itacademy.dto.response.CouponResponseDto;
import by.itacademy.entities.Coupon;
import by.itacademy.mappers.CouponMapper;
import by.itacademy.repositories.coupon.CouponRepository;
import by.itacademy.services.exceptions.CouponServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;
    private final CouponMapper couponMapper;

    @Override
    public CouponResponseDto saveCoupon(final CreateCouponRequestDto couponRequestDto) {
        final Coupon coupon = couponMapper.mapToCoupon(couponRequestDto);
        final Coupon savedCoupon = couponRepository.save(coupon);
        return couponMapper.mapToCouponResponse(savedCoupon);
    }

    @Override
    public List<CouponResponseDto> readCoupons() {
        return couponRepository.findAll().stream()
                .map(couponMapper::mapToCouponResponse)
                .collect(toList());
    }

    @Override
    public CouponResponseDto updateCoupon(final UUID id, final UpdateCouponRequestDto couponRequestDto) {
        final Coupon couponToUpdate = couponRepository.findById(id)
                .orElseThrow(() -> new CouponServiceException("You can't update this coupon"));
        couponMapper.updateCoupon(couponRequestDto, couponToUpdate);
        final Coupon updatedCoupon = couponRepository.save(couponToUpdate);
        return couponMapper.mapToCouponResponse(updatedCoupon);
    }

    @Override
    public void deleteCoupon(final UUID id) {
        couponRepository.deleteById(id);
    }

    @Override
    public CouponResponseDto getById(final UUID id) {
        final Coupon coupon = couponRepository.findById(id)
                .orElseThrow(() -> new CouponServiceException("This coupon doesn't exist"));
        return couponMapper.mapToCouponResponse(coupon);
    }

    //    @Override
//    public List<Coupon> getCouponsByUserId(final UUID id) {
//
//        return couponRepository.findCouponsByUsers(userId);
//    }
//
    @Override
    public List<CouponResponseDto> getCouponsByCompanyId(final UUID companyId) {
        return couponRepository.findCouponsByCompanyId(companyId).stream()
                .map(couponMapper::mapToCouponResponse)
                .collect(toList());
    }

    @Override
    public Coupon getCouponById(final UUID id) {
        return couponRepository.findById(id)
                .orElseThrow(() -> new CouponServiceException("This coupon doesn't exist"));
    }
}
