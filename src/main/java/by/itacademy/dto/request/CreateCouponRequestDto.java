package by.itacademy.dto.request;

import by.itacademy.entities.Company;
import lombok.Data;

@Data
public class CreateCouponRequestDto {

    private String description;
    private Integer cost;
    private Company company;
}
