package by.itacademy.mappers;

import by.itacademy.dto.request.CreateTareRequestDto;
import by.itacademy.dto.request.UpdateTareRequestDto;
import by.itacademy.dto.response.TareResponseDto;
import by.itacademy.entities.Tare;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = SPRING, nullValuePropertyMappingStrategy = IGNORE)
public interface TareMapper {

    /** Mapping tare request to entity.
     *
     * @param tareRequestDto request for tare creating
     * @return entity
     */
    Tare mapToTare(CreateTareRequestDto tareRequestDto);

    /** Mapping tare entity to response.
     *
     * @param tare entity
     * @return TareResponseDto response with tare details
     */
    TareResponseDto mapToTareResponse(Tare tare);

    /** Mapping tare entity to response.
     *
     * @param tareRequestDto request for tare with fields for update
     * @param tare entity for updating
     */
    @InheritConfiguration
    void updateTare(UpdateTareRequestDto tareRequestDto, @MappingTarget Tare tare);
}
