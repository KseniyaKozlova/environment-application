package by.itacademy.environment.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

import static by.itacademy.environment.util.Constants.UUID_REGEX;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCouponRequestDto {

    @NotBlank
    private String description;

    @NotNull
    @PositiveOrZero
    private Integer cost;

    @NotNull
    @Pattern(regexp = UUID_REGEX)
    private UUID companyId;
}
