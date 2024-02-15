package by.itacademy.environment.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CreateCompanyRequestDto {

    @NotBlank
    private String companyName;

    @NotBlank
    private String details;

    private List<CreateCouponRequestDto> coupons;
}
