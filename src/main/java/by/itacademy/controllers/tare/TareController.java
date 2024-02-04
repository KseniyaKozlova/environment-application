package by.itacademy.controllers.tare;

import by.itacademy.dto.request.CreateTareRequestDto;
import by.itacademy.dto.request.UpdateTareRequestDto;
import by.itacademy.entities.Tare;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import by.itacademy.services.tare.TareService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/version1")
@RequiredArgsConstructor
public class TareController {

    private final TareService tareService;

    @PostMapping(path = "/tare")
    public Tare saveTare(@RequestBody CreateTareRequestDto tareRequestDto) {
        return tareService.saveTare(tareRequestDto);
    }

    @GetMapping(path = "/tare/{id}")
    public Tare getTareById(@PathVariable UUID id) {
        return tareService.getTareById(id);
    }

    @GetMapping(path = "/tares")
    public List<Tare> getAllTares() {
        return tareService.readTares();
    }

//    @GetMapping(path = "/tares/user/{id}")
//    public List<Tare> getTaresByUserId(@PathVariable(name = "id") UUID id) {
//        return tareService.getTaresByUserId(id);
//    }

    @PutMapping(path = "/tare/updateTare/{id}")
    public Tare updateTare(@PathVariable UUID id, @RequestBody UpdateTareRequestDto tareRequestDto) {
        return tareService.updateTare(id, tareRequestDto);
    }

    @DeleteMapping(path = "/tare/delete/{id}")
    public void deleteTare(@PathVariable UUID id) {
        tareService.delete(id);
    }
}