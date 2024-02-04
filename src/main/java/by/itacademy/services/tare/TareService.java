package by.itacademy.services.tare;

import by.itacademy.dto.request.CreateTareRequestDto;
import by.itacademy.dto.request.UpdateTareRequestDto;
import by.itacademy.entities.Tare;

import java.util.List;
import java.util.UUID;

public interface TareService {

    Tare saveTare(CreateTareRequestDto tareRequestDto);

    List<Tare> readTares();

//    List<Tare> getTaresByUserId(UUID id);

    Tare updateTare(UUID id, UpdateTareRequestDto tareRequestDto);

    void delete(UUID id);

    Tare getTareById(UUID id);
}
