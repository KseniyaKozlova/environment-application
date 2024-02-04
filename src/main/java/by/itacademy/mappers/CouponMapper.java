package by.itacademy.mappers;

import by.itacademy.dto.request.CreateCouponRequestDto;
import by.itacademy.dto.request.UpdateCouponRequestDto;
import by.itacademy.entities.Coupon;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = CompanyMapper.class)
public interface CouponMapper {

    Coupon mapToCoupon(CreateCouponRequestDto couponRequestDto);

    @InheritConfiguration
    void updateCoupon(UpdateCouponRequestDto couponRequestDto, @MappingTarget Coupon coupon);
}
