package by.itacademy.environment.services.tare;

import by.itacademy.environment.dto.request.CreateTareRequestDto;
import by.itacademy.environment.dto.response.TareResponseDto;

import java.util.List;
import java.util.UUID;

/**
 * Tare processing service
 */
public interface TareService {

    /**
     * Save tare in DB with owner(user), if he exists
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
    List<TareResponseDto> getTares();

    /**
     * Get all tares from DB of determined user
     *
     * @param id user id, whose tares are need
     * @return list of tares in response view
     */
    List<TareResponseDto> getTaresByUserId(UUID id);

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
}
