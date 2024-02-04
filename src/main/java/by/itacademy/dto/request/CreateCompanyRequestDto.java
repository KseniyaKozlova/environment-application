package by.itacademy.dto.request;

import by.itacademy.entities.Coupon;
import lombok.Data;

import java.util.List;

@Data
public class CreateCompanyRequestDto {

    private String companyName;
    private String details;
    private List<Coupon> coupons;
}
