package by.itacademy.controllers;

import by.itacademy.dto.request.CreateUserRequestDto;
import by.itacademy.dto.request.UpdateUserRequestDto;
import by.itacademy.dto.response.UserResponseDto;
import by.itacademy.services.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static by.itacademy.util.Constants.*;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(path = START_PART_URL)
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
                                      @RequestBody UpdateUserRequestDto userRequestDto) {
        return userService.updateUser(id, userRequestDto);
    }

    @DeleteMapping(path = DELETE_USER_URL)
    public void deleteUser(@PathVariable(name = ID) UUID id) {
        userService.deleteUser(id);
    }

    @PostMapping(path = DEPOSIT_TARE_BY_USER_URL)
    public UserResponseDto depositTareByUser(@PathVariable(value = USER_ID) UUID userId,
                                             @PathVariable(value = TARE_ID) UUID tareId) {
        return userService.depositTare(userId, tareId);
    }
}
