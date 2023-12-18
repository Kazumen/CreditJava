package com.labs.lab48.service;

import com.labs.lab48.dto.UserCreatingDto;
import com.labs.lab48.dto.UserDto;
import com.labs.lab48.exception.UserAlreadyExistsException;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

public interface UserService {
    Integer delete(Integer id);

    Page<UserDto> getAllUsers(Pageable pageable);


    UserDto getUser(Integer id);


    UserDto editUser(Integer id, UserCreatingDto userCreatingDto) throws UserAlreadyExistsException;

    UserDto addUser(UserCreatingDto userCreatingDto) throws UserAlreadyExistsException;

}
