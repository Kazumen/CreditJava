package com.labs.lab48.serviceImpl;

import com.labs.lab48.dto.UserCreatingDto;
import com.labs.lab48.dto.UserDto;
import com.labs.lab48.entity.User;
import com.labs.lab48.exception.UserAlreadyExistsException;
import com.labs.lab48.mapper.UserMapper;
import com.labs.lab48.repository.UserRepository;
import com.labs.lab48.service.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@AllArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private UserMapper userMapper;

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
        log.info("User was deleted.");
    }

    @Override
    public Page<UserDto> getAllUsers(Pageable pageable) {
        log.info("Return all users.");
        return userRepository.findAll(pageable).map(userMapper::userToUserDto);
    }

    @Transactional
    @Override
    public UserDto getUser(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.orElseThrow(() -> new NoSuchElementException("There is no user with that id!"));
        log.info("User with id " + id + "was found!");
        return userMapper.userToUserDto(user);
    }


    @Override
    public UserDto editUser(Integer id, UserCreatingDto userCreatingDto) throws UserAlreadyExistsException {
        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.orElseThrow(() -> new NoSuchElementException("There is no user with that id!"));
        if (userCreatingDto.name() != null && !userCreatingDto.name().equals(user.getName())) {
            user.setName(userCreatingDto.name());
        }
        if (userCreatingDto.surname() != null && !userCreatingDto.surname().equals(user.getSurname()))
            user.setSurname(userCreatingDto.surname());
        if (userCreatingDto.email() != null && !userCreatingDto.email().equals(user.getEmail())) {
            if (userRepository.findByEmail(userCreatingDto.email()) != null)
                throw new UserAlreadyExistsException("User with that email already exist");
            user.setEmail(userCreatingDto.email());
        }
        log.info("User was edited.");
        log.info(userCreatingDto.name()+' ' + userCreatingDto.surname());
        return userMapper.userToUserDto(userRepository.save(user));
    }

    @Override
    public UserDto addUser(UserCreatingDto userCreatingDto) throws UserAlreadyExistsException {
        User user = new User();
        if (userRepository.findByEmail(userCreatingDto.email()) != null)
            throw new UserAlreadyExistsException("User with that email already exist");
        user.setName(userCreatingDto.name());
        user.setEmail(userCreatingDto.email());
        user.setSurname(userCreatingDto.surname());
        log.info("User was added.");
        return userMapper.userToUserDto(userRepository.save(user));
    }


}
