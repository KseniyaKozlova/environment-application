package by.itacademy.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@Builder
public class CompanyResponseDto {

    private UUID id;

    private String companyName;

    private String details;

    @JsonInclude(NON_NULL)
    private List<AddressResponseDto> addresses;
}
