package by.itacademy.dto.response;

import by.itacademy.enums.Role;
import lombok.Data;

import java.util.UUID;

@Data
public class UserResponseDto {

    private UUID id;
    private String login;
    private String name;
    private Integer bonuses;
    private Role role;
}
