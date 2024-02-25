package by.itacademy.environment.dto.request;

import by.itacademy.environment.annotations.AtLeastOneFieldPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@AtLeastOneFieldPresent
public class UpdateCompanyRequestDto {

    private String companyName;

    private String details;
}
