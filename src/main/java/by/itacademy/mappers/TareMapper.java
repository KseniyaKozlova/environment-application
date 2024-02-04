package by.itacademy.mappers;

import by.itacademy.dto.request.CreateTareRequestDto;
import by.itacademy.dto.request.UpdateTareRequestDto;
import by.itacademy.entities.Tare;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TareMapper {

    Tare mapToTare(CreateTareRequestDto tareRequestDto);

    @InheritConfiguration
    void updateTare(UpdateTareRequestDto tareRequestDto, @MappingTarget Tare tare);
}
