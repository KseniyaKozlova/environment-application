package by.itacademy.environment.dto.request;

import by.itacademy.environment.enums.Role;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static by.itacademy.environment.util.Constants.EMAIL_REGEX;
import static by.itacademy.environment.util.Constants.PASSWORD_REGEX;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
