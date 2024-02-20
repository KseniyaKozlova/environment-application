package by.itacademy.environment.services.coupon;

import by.itacademy.environment.dto.request.CreateCouponRequestDto;
import by.itacademy.environment.dto.request.UpdateCouponRequestDto;
import by.itacademy.environment.dto.response.CouponResponseDto;
import by.itacademy.environment.entities.Company;
import by.itacademy.environment.entities.Coupon;
import by.itacademy.environment.exceptions.notFound.CouponNotFoundException;
import by.itacademy.environment.mappers.CouponMapper;
import by.itacademy.environment.repositories.coupon.CouponRepository;
import by.itacademy.environment.repositories.user.UserRepository;
import by.itacademy.environment.services.company.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;
    private final CompanyService companyService;
    private final UserRepository userRepository;
    private final CouponMapper couponMapper;

    @Override
    @Transactional
    public CouponResponseDto saveCoupon(final CreateCouponRequestDto couponRequestDto) {
        final Coupon coupon = couponMapper.mapToCoupon(couponRequestDto);
        final Company company = companyService.getCompanyById(couponRequestDto.getCompanyId());
        company.addCoupon(coupon);
        final Coupon savedCoupon = couponRepository.save(coupon);
        return couponMapper.mapToCouponResponse(savedCoupon);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CouponResponseDto> getCoupons() {
        return couponRepository.findAll().stream()
                .map(couponMapper::mapToCouponResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CouponResponseDto> getAvailableCoupons(final UUID id) {
        final Integer userBonuses = userRepository.findBonusesById(id);
        return couponRepository.findCouponsByCostSmallerThanUserBonuses(userBonuses).stream()
                .map(couponMapper::mapToCouponResponse)
                .toList();
    }

    @Override
    @Transactional
    public CouponResponseDto updateCoupon(final UUID id, final UpdateCouponRequestDto couponRequestDto) {
        final Coupon couponToUpdate = couponRepository.findById(id)
                .orElseThrow(() -> new CouponNotFoundException(id.toString()));
        couponMapper.updateCoupon(couponRequestDto, couponToUpdate);
        final Coupon updatedCoupon = couponRepository.save(couponToUpdate);
        return couponMapper.mapToCouponResponse(updatedCoupon);
    }

    @Override
    @Transactional
    public void deleteCoupon(final UUID id) {
        final Coupon coupon = getCouponById(id);
        userRepository.findUsersByCouponsId(id)
                .forEach(user -> user.removeCoupon(coupon));
        couponRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public CouponResponseDto getCouponResponseById(final UUID id) {
        final Coupon coupon = getCouponById(id);
        return couponMapper.mapToCouponResponse(coupon);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CouponResponseDto> getCouponsByUserId(final UUID id) {
        return couponRepository.findCouponsByUsersId(id).stream()
                .map(couponMapper::mapToCouponResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CouponResponseDto> getCouponsByCompanyId(final UUID companyId) {
        return couponRepository.findCouponsByCompanyId(companyId).stream()
                .map(couponMapper::mapToCouponResponse)
                .toList();
    }

    @Override
    public Coupon getCouponById(final UUID id) {
        return couponRepository.findById(id)
                .orElseThrow(() -> new CouponNotFoundException(id.toString()));
    }

    @Override
    public List<Coupon> getCouponsByUserIdAsCoupons(final UUID id) {
        return couponRepository.findCouponsByUsersId(id);
    }
}
