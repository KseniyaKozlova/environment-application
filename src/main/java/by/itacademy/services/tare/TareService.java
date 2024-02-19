package by.itacademy.services.tare;

import by.itacademy.dto.request.CreateTareRequestDto;
import by.itacademy.dto.request.UpdateTareRequestDto;
import by.itacademy.dto.response.TareResponseDto;
import by.itacademy.entities.Tare;

import java.util.List;
import java.util.UUID;

/**
 * Tare processing service
 */
public interface TareService {

    /**
     * Save tare in DB with its coupons, if they are need
     *
     * @param tareRequestDto request for tare creating
     * @return tare in response view
     */
    TareResponseDto saveTare(CreateTareRequestDto tareRequestDto);

    /**
     * Get all tares from DB
     *
     * @return list of tares in response view
     */
    List<TareResponseDto> readTares();

    /**
     * Get all tares from DB of determined user
     *
     * @param id user id, whose tares are need
     * @return list of tares in response view
     */
    List<TareResponseDto> getTaresByUserId(UUID id);

    /**
     * Update tare in DB by id
     *
     * @param id tare id
     * @param tareRequestDto request for tare with fields for updating
     * @return updated tare in response view
     */
    TareResponseDto updateTare(UUID id, UpdateTareRequestDto tareRequestDto);

    /**
     * Delete tare in DB by id
     *
     * @param id tare id
     */
    void delete(UUID id);

    /**
     * Get tare from DB by id
     *
     * @param id tare id
     * @return tare in response view
     */
    TareResponseDto getTareResponseById(UUID id);

    /**
     * Get tare company from DB by id
     *
     * @param id tare id
     * @return entity
     */
    Tare getTareById(UUID id);
}
