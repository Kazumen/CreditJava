package com.labs.lab48.controller;

import com.labs.lab48.dto.CreditDto;
import com.labs.lab48.dto.UserCreatingDto;
import com.labs.lab48.dto.UserDto;
import com.labs.lab48.exception.UserAlreadyExistsException;
import com.labs.lab48.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    @GetMapping
    public Page<UserDto> getAllUsers(@PageableDefault Pageable pageable) {
        return userService.getAllUsers(pageable);
    }
    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Integer id) {
        return userService.getUser(id);
    }
    @PostMapping
    public UserDto addUser(@RequestBody @Valid UserCreatingDto userCreatingDto) throws UserAlreadyExistsException {
        return userService.addUser(userCreatingDto);
    }
    @PatchMapping("/{id}")
    public UserDto editUser(@PathVariable Integer id,@RequestBody UserCreatingDto userCreatingDto) throws UserAlreadyExistsException {
        return userService.editUser(id,userCreatingDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok("User with id:" + userService.delete(id) + " was deleted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e);
        }
    }
}
