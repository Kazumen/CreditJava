package com.labs.lab48.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;

public record BankCreatingDto(@NotBlank(message = "Can`t be blank") String name,
                              @NotBlank(message = "Can`t be blank") String owner,
                              @NotBlank(message = "Can`t be blank") String address,
                              @NotBlank(message = "Can`t be blank") String website,
                               Double maxLimit) implements Serializable {
}
