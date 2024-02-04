package by.itacademy.dto.request;

import by.itacademy.enums.Role;
import lombok.Data;

@Data
public class UpdateUserRequestDto {

    private String login;
    private String password;
    private String name;
    private Integer bonuses;
    private Role role;
}
