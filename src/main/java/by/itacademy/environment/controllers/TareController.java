package by.itacademy.environment.controllers;

import by.itacademy.environment.dto.request.CreateTareRequestDto;
import by.itacademy.environment.dto.response.TareResponseDto;
import by.itacademy.environment.services.tare.TareService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static by.itacademy.environment.util.Constants.*;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(path = API_URL)
@RequiredArgsConstructor
public class TareController {

    private final TareService tareService;

    @ResponseStatus(CREATED)
    @PostMapping(path = SAVE_TARE_URL)
    public TareResponseDto saveTare(@RequestBody @Valid CreateTareRequestDto tareRequestDto) {
        return tareService.saveTare(tareRequestDto);
    }

    @GetMapping(path = GET_TARE_URL)
    public TareResponseDto getTareById(@PathVariable(value = ID) UUID id) {
        return tareService.getTareResponseById(id);
    }

    @GetMapping(path = GET_TARES_URL)
    public List<TareResponseDto> getTares() {
        return tareService.getTares();
    }

    @GetMapping(path = GET_USER_TARES_URL)
    public List<TareResponseDto> getTaresByUserId(@PathVariable(value = ID) UUID id) {
        return tareService.getTaresByUserId(id);
    }

    @DeleteMapping(path = DELETE_TARE_URL)
    public void deleteTare(@PathVariable(value = ID) UUID id) {
        tareService.delete(id);
    }
}