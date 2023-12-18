package com.labs.lab48.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public record UserCreatingDto(@NotBlank(message = "Can`t be blank") String name,
                              @NotBlank(message = "Can`t be blank") String surname,
                              @Email @NotBlank(message = "Can`t be blank") String email) implements Serializable {
}
