package by.itacademy.environment.dto.request;

import by.itacademy.environment.enums.TareCategory;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateTareRequestDto {

    @NotNull
    private TareCategory tareCategory;

    @NotNull
    private BigDecimal litresVolume;

    private UUID userId;
}
