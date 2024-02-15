package by.itacademy.dto.response;

import by.itacademy.entities.Company;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CouponResponseDto {

    private UUID id;

    private String description;

    private Integer cost;

    @JsonIgnore
    private Company company;
}
