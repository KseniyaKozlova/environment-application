package by.itacademy.dto.response;

import lombok.Data;

import java.util.UUID;

@Data
public class CompanyResponseDto {

    private UUID id;
    private String companyName;
    private String details;
}
