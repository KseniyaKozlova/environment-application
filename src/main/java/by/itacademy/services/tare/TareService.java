package by.itacademy.services.tare;

import by.itacademy.dto.request.CreateTareRequestDto;
import by.itacademy.dto.request.UpdateTareRequestDto;
import by.itacademy.dto.response.TareResponseDto;
import by.itacademy.entities.Tare;

import java.util.List;
import java.util.UUID;

public interface TareService {

    TareResponseDto saveTare(CreateTareRequestDto tareRequestDto);

    List<TareResponseDto> readTares();

//    List<Tare> getTaresByUserId(UUID id);

    TareResponseDto updateTare(UUID id, UpdateTareRequestDto tareRequestDto);

    void delete(UUID id);

    TareResponseDto getById(UUID id);

    Tare getTareById(UUID id);
}
