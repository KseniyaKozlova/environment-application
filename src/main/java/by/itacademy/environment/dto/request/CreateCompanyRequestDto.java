package by.itacademy.environment.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCompanyRequestDto {

    @NotBlank
    private String companyName;

    @NotBlank
    private String details;

    private List<CreateCouponRequestDto> coupons;
}
