package by.itacademy.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateCompanyRequestDto {

    private String companyName;

    private String details;
}
