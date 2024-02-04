package by.itacademy.mappers;

import by.itacademy.dto.request.CreateUserRequestDto;
import by.itacademy.dto.request.UpdateUserRequestDto;
import by.itacademy.dto.response.UserResponseDto;
import by.itacademy.entities.User;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    //    expression - в любом случае установит указанное значение дажe если не null
//    @InheritInverseConfiguration - если есть аннотированный метод toDTO, toModel можно не аннотировать, а просто поставить эту аннотацию
//    @InheritConfiguration - MapStruct будет искать другой, уже настроенный метод, конфигурация которого может быть применена и к этому
    @Mapping(source = "bonuses", target = "bonuses", defaultExpression = "java(0)")
    User mapToUser(CreateUserRequestDto userRequestDto);

    UserResponseDto mapToUserResponse(User user);

    @InheritConfiguration
    void updateUser(UpdateUserRequestDto userRequestDto, @MappingTarget User user);
}
