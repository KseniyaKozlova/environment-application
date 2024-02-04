package by.itacademy.dto.request;

import by.itacademy.enums.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder //TODO :is it need?
public class CreateUserRequestDto {

    private String login;
    private String password;
    private String name;
    private Integer bonuses;
    private Role role;
}
