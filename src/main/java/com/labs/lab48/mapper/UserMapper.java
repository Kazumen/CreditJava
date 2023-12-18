package com.labs.lab48.mapper;

import com.labs.lab48.dto.UserCreatingDto;
import com.labs.lab48.dto.UserDto;
import com.labs.lab48.entity.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.Optional;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {
    UserDto userToUserDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User userCreatingDtoToUser(UserCreatingDto userCreatingDto);
}
