package by.itacademy.mappers;

import by.itacademy.dto.request.CreateUserRequestDto;
import by.itacademy.dto.request.UpdateUserRequestDto;
import by.itacademy.dto.response.UserResponseDto;
import by.itacademy.entities.User;
import by.itacademy.enums.Role;
import by.itacademy.util.Constants;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = SPRING, imports = Role.class, nullValuePropertyMappingStrategy = IGNORE)
public interface UserMapper {

    @Mapping(source = Constants.BONUSES_FIELD_NAME, target = Constants.BONUSES_FIELD_NAME, defaultExpression = Constants.ZERO_EXPRESSION_DEFAULT)
    @Mapping(source = Constants.ROLE_FIELD_NAME, target = Constants.ROLE_FIELD_NAME, defaultExpression = Constants.CONSUMER_ROLE_DEFAULT)
    User mapToUser(CreateUserRequestDto userRequestDto);

    UserResponseDto mapToUserResponse(User user);

    @InheritConfiguration
    void updateUser(UpdateUserRequestDto userRequestDto, @MappingTarget User user);
}