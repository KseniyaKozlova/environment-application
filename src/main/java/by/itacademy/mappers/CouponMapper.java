package by.itacademy.mappers;

import by.itacademy.dto.request.CreateCompanyRequestDto;
import by.itacademy.dto.request.CreateCouponRequestDto;
import by.itacademy.dto.request.UpdateCouponRequestDto;
import by.itacademy.dto.response.CouponResponseDto;
import by.itacademy.entities.Coupon;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static by.itacademy.util.Constants.COMPANY_FIELD_NAME;
import static by.itacademy.util.Constants.COMPANY_ID;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = SPRING, uses = CreateCompanyRequestDto.class, nullValuePropertyMappingStrategy = IGNORE)
public interface CouponMapper {

    /** Mapping coupon request to entity.
     *
     * @param couponRequestDto request for coupon creating
     * @return entity
     */
    @Mapping(source = COMPANY_ID, target = COMPANY_FIELD_NAME, ignore = true)
    Coupon mapToCoupon(CreateCouponRequestDto couponRequestDto);

    /** Mapping coupon entity to response.
     *
     * @param coupon entity
     * @return CouponResponseDto response with coupon details
     */
    CouponResponseDto mapToCouponResponse(Coupon coupon);

    /** Mapping coupon entity to response.
     *
     * @param couponRequestDto request for coupon with fields for update
     * @param coupon entity for updating
     */
    @InheritConfiguration
    void updateCoupon(UpdateCouponRequestDto couponRequestDto, @MappingTarget Coupon coupon);
}
