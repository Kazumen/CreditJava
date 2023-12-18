package com.labs.lab48;

import com.labs.lab48.dto.UserCreatingDto;
import com.labs.lab48.dto.UserDto;
import com.labs.lab48.entity.User;
import com.labs.lab48.exception.UserAlreadyExistsException;
import com.labs.lab48.mapper.UserMapper;
import com.labs.lab48.repository.UserRepository;
import com.labs.lab48.serviceImpl.UserServiceImpl;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

//    @Test
//    void testDeleteUser() {
//        Integer userId = 1;
//        when(userRepository.deleteById(userId)).thenReturn(Optional.of(new User()));
//
//        Integer deletedUserId = userService.delete(userId);
//
//        assertEquals(userId, deletedUserId);
//        verify(userRepository, times(1)).deleteById(userId);
//    }

    @Test
    void testGetAllUsers() {
        Pageable pageable = Pageable.unpaged();
        Page<User> usersPage = mock(Page.class);
        when(userRepository.findAll(pageable)).thenReturn(usersPage);

        userService.getAllUsers(pageable);

        verify(userRepository, times(1)).findAll(pageable);
        verify(userMapper, times(1)).userToUserDto(any());
    }

}
