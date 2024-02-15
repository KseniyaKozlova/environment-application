package by.itacademy.dto.request;

import by.itacademy.enums.Role;
import by.itacademy.util.Constants;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class UpdateUserRequestDto {

    @Pattern(regexp = Constants.EMAIL_REGEX)
    private String login;

    @Pattern(regexp = Constants.PASSWORD_REGEX)
    private String password;

    private String name;

    @PositiveOrZero
    private Integer bonuses;

    private Role role;
}
