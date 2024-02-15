package by.itacademy.controllers;

import by.itacademy.dto.request.CreateTareRequestDto;
import by.itacademy.dto.request.UpdateTareRequestDto;
import by.itacademy.dto.response.TareResponseDto;
import by.itacademy.services.tare.TareService;
import by.itacademy.util.Constants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(path = Constants.START_PART_URL)
@RequiredArgsConstructor
public class TareController {

    private final TareService tareService;

    @ResponseStatus(CREATED)
    @PostMapping(path = Constants.SAVE_TARE_URL)
    public TareResponseDto saveTare(@RequestBody @Valid CreateTareRequestDto tareRequestDto) {
        return tareService.saveTare(tareRequestDto);
    }

    @GetMapping(path = Constants.GET_TARE_URL)
    public TareResponseDto getTareById(@PathVariable(value = Constants.ID) UUID id) {
        return tareService.getTareResponseById(id);
    }

    @GetMapping(path = Constants.GET_TARES_URL)
    public List<TareResponseDto> getAllTares() {
        return tareService.readTares();
    }

    @GetMapping(path = Constants.GET_USER_TARES_URL)
    public List<TareResponseDto> getTaresByUserId(@PathVariable(value = Constants.ID) UUID id) {
        return tareService.getTaresByUserId(id);
    }

    @PatchMapping(path = Constants.UPDATE_TARE_URL)
    public TareResponseDto updateTare(@PathVariable(value = Constants.ID) UUID id, @RequestBody @Valid UpdateTareRequestDto tareRequestDto) {
        return tareService.updateTare(id, tareRequestDto);
    }

    @DeleteMapping(path = Constants.DELETE_TARE_URL)
    public void deleteTare(@PathVariable(value = Constants.ID) UUID id) {
        tareService.delete(id);
    }
}