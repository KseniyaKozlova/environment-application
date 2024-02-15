package by.itacademy.mappers;

import by.itacademy.dto.request.CreateCompanyRequestDto;
import by.itacademy.dto.request.CreateCouponRequestDto;
import by.itacademy.dto.request.UpdateCouponRequestDto;
import by.itacademy.dto.response.CouponResponseDto;
import by.itacademy.entities.Coupon;
import by.itacademy.util.Constants;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = SPRING, uses = CreateCompanyRequestDto.class, nullValuePropertyMappingStrategy = IGNORE)
public interface CouponMapper {

    @Mapping(source = Constants.COMPANY_ID, target = Constants.COMPANY_FIELD_NAME, ignore = true)
    Coupon mapToCoupon(CreateCouponRequestDto couponRequestDto);

    CouponResponseDto mapToCouponResponse(Coupon coupon);

    @InheritConfiguration
    void updateCoupon(UpdateCouponRequestDto couponRequestDto, @MappingTarget Coupon coupon);
}
