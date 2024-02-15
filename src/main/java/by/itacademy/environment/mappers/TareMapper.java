package by.itacademy.environment.mappers;

import by.itacademy.environment.dto.request.CreateTareRequestDto;
import by.itacademy.environment.dto.request.UpdateTareRequestDto;
import by.itacademy.environment.dto.response.TareResponseDto;
import by.itacademy.environment.entities.Tare;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = SPRING, nullValuePropertyMappingStrategy = IGNORE)
public interface TareMapper {

    Tare mapToTare(CreateTareRequestDto tareRequestDto);

    TareResponseDto mapToTareResponse(Tare tare);

    @InheritConfiguration
    void updateTare(UpdateTareRequestDto tareRequestDto, @MappingTarget Tare tare);
}
