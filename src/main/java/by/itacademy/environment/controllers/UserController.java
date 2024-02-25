package by.itacademy.environment.controllers;

import by.itacademy.environment.dto.request.CreateUserRequestDto;
import by.itacademy.environment.dto.request.UpdateUserRequestDto;
import by.itacademy.environment.dto.response.UserResponseDto;
import by.itacademy.environment.services.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static by.itacademy.environment.util.Constants.*;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(path = API_URL)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(path = GET_USER_BY_ID_URL)
    public UserResponseDto getUserById(@PathVariable(value = ID) UUID id) {
        return userService.getUserResponseById(id);
    }

    @GetMapping(path = GET_USER_BY_LOGIN_URL)
    public UserResponseDto getUserByLogin(@PathVariable(value = LOGIN_FIELD_NAME) String login) {
        return userService.getUserByLogin(login);
    }

    @ResponseStatus(CREATED)
    @PostMapping(path = SAVE_USER_URL)
    public UserResponseDto saveUser(@RequestBody @Valid CreateUserRequestDto userRequestDto) {
        return userService.saveUser(userRequestDto);
    }

    @PutMapping(path = UPDATE_USER_URL)
    public UserResponseDto updateUser(@PathVariable(value = ID) UUID id,
                                      @RequestBody @Valid UpdateUserRequestDto userRequestDto) {
        return userService.updateUser(id, userRequestDto);
    }

    @DeleteMapping(path = DELETE_USER_URL)
    public void deleteUser(@PathVariable(name = ID) UUID id) {
        userService.deleteUser(id);
    }
}
