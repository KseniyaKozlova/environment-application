package by.itacademy.dto.response;

import by.itacademy.entities.Company;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import java.util.UUID;

@Data
public class CouponResponseDto {

    private UUID id;
    private String description;
    private Integer cost;
    @JsonBackReference
    private Company company;
}
