package by.itacademy.environment.services.tare;

import by.itacademy.environment.dto.request.CreateTareRequestDto;
import by.itacademy.environment.dto.request.UpdateTareRequestDto;
import by.itacademy.environment.dto.response.TareResponseDto;
import by.itacademy.environment.entities.Tare;

import java.util.List;
import java.util.UUID;

public interface TareService {

    TareResponseDto saveTare(CreateTareRequestDto tareRequestDto);

    List<TareResponseDto> readTares();

    List<TareResponseDto> getTaresByUserId(UUID id);

    TareResponseDto updateTare(UUID id, UpdateTareRequestDto tareRequestDto);

    void delete(UUID id);

    TareResponseDto getTareResponseById(UUID id);

    Tare getTareById(UUID id);
}
