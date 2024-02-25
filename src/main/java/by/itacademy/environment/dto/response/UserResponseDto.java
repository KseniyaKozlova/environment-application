package by.itacademy.environment.dto.response;

import by.itacademy.environment.enums.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;
import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Data
public class UserResponseDto {

    private UUID id;

    private String login;

    private String name;

    private Integer bonuses;

    private Role role;

    @JsonInclude(NON_EMPTY)
    private List<CouponResponseDto> coupons;
}
