package by.itacademy.environment.mappers;

import by.itacademy.environment.dto.request.CreateTareRequestDto;
import by.itacademy.environment.dto.response.TareResponseDto;
import by.itacademy.environment.entities.Tare;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static by.itacademy.environment.util.Constants.USER_FIELD_NAME;
import static by.itacademy.environment.util.Constants.USER_ID;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = SPRING, nullValuePropertyMappingStrategy = IGNORE)
public interface TareMapper {

    /**
     * Mapping tare request to entity.
     *
     * @param tareRequestDto request for tare creating
     * @return entity
     */
    @Mapping(source = USER_ID, target = USER_FIELD_NAME, ignore = true)
    Tare mapToTare(CreateTareRequestDto tareRequestDto);

    /**
     * Mapping tare entity to response.
     *
     * @param tare entity
     * @return TareResponseDto response with tare details
     */
    TareResponseDto mapToTareResponse(Tare tare);
}
