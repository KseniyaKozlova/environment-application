package by.itacademy.environment.mappers;

import by.itacademy.environment.dto.request.CreateUserRequestDto;
import by.itacademy.environment.dto.request.UpdateUserRequestDto;
import by.itacademy.environment.dto.response.UserResponseDto;
import by.itacademy.environment.entities.User;
import by.itacademy.environment.enums.Role;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static by.itacademy.environment.util.Constants.*;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = SPRING, imports = Role.class, nullValuePropertyMappingStrategy = IGNORE)
public interface UserMapper {

    /**
     * Mapping user request to entity.
     *
     * @param userRequestDto request for user creating
     * @return entity
     */
    @Mapping(source = BONUSES_FIELD_NAME, target = BONUSES_FIELD_NAME, defaultExpression = ZERO_EXPRESSION_DEFAULT)
    @Mapping(source = ROLE_FIELD_NAME, target = ROLE_FIELD_NAME, defaultExpression = CONSUMER_ROLE_DEFAULT)
    User mapToUser(CreateUserRequestDto userRequestDto);

    /**
     * Mapping user entity to response.
     *
     * @param user entity
     * @return UserResponseDto response with user details
     */
    UserResponseDto mapToUserResponse(User user);

    /**
     * Mapping user entity to response.
     *
     * @param userRequestDto request for user with fields for update
     * @param user           entity for updating
     */
    @InheritConfiguration
    void updateUser(UpdateUserRequestDto userRequestDto, @MappingTarget User user);
}
