package by.itacademy.environment.dto.request;

import by.itacademy.environment.enums.TareCategory;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateTareRequestDto {

    @NotNull
    private TareCategory tareCategory;

    @NotNull
    private Double litresVolume;

    @NotNull
    @PositiveOrZero
    private Integer bonusesToAccounting;
}
