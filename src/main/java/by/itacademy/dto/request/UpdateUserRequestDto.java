package by.itacademy.dto.request;

import by.itacademy.enums.Role;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import static by.itacademy.util.Constants.EMAIL_REGEX;
import static by.itacademy.util.Constants.PASSWORD_REGEX;

@Data
public class UpdateUserRequestDto {

    @Pattern(regexp = EMAIL_REGEX)
    private String login;

    @Pattern(regexp = PASSWORD_REGEX)
    private String password;

    private String name;

    @PositiveOrZero
    private Integer bonuses;

    private Role role;
}
