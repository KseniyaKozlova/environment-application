package by.itacademy.environment.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CouponResponseDto {

    private UUID id;

    private String description;

    private Integer cost;

    private CompanyResponseDto company;
}
