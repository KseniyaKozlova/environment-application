package by.itacademy.environment.mappers;

import by.itacademy.environment.dto.request.CreateCompanyRequestDto;
import by.itacademy.environment.dto.request.CreateCouponRequestDto;
import by.itacademy.environment.dto.request.UpdateCouponRequestDto;
import by.itacademy.environment.dto.response.CouponResponseDto;
import by.itacademy.environment.entities.Coupon;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static by.itacademy.environment.util.Constants.COMPANY_FIELD_NAME;
import static by.itacademy.environment.util.Constants.COMPANY_ID;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = SPRING, uses = CreateCompanyRequestDto.class, nullValuePropertyMappingStrategy = IGNORE)
public interface CouponMapper {

    @Mapping(source = COMPANY_ID, target = COMPANY_FIELD_NAME, ignore = true)
    Coupon mapToCoupon(CreateCouponRequestDto couponRequestDto);

    CouponResponseDto mapToCouponResponse(Coupon coupon);

    @InheritConfiguration
    void updateCoupon(UpdateCouponRequestDto couponRequestDto, @MappingTarget Coupon coupon);
}
