package by.itacademy.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class CompanyResponseDto {

    private UUID id;

    private String companyName;

    private String details;

    private List<AddressResponseDto> addresses;
}
