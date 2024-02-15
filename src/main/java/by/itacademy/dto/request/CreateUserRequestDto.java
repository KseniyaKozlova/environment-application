package by.itacademy.dto.request;

import by.itacademy.enums.Role;
import by.itacademy.util.Constants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateUserRequestDto {

    @NotNull
    @Pattern(regexp = Constants.EMAIL_REGEX)
    private String login;

    @NotNull
    @Pattern(regexp = Constants.PASSWORD_REGEX)
    private String password;

    @NotBlank
    private String name;

    @PositiveOrZero
    private Integer bonuses;

    private Role role;
}
