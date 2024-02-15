package by.itacademy.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CreateCouponRequestDto {

    @NotBlank
    private String description;

    @NotNull
    @PositiveOrZero
    private Integer cost;

    @NotNull
    private UUID companyId;
}
