package com.labs.lab48.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public record CreditCreatingDto(@NotBlank(message = "Can`t be blank") String name,
                                @NotNull Double maxSum,
                                @NotNull Double commission,
                                @NotNull Long contractTerm,
                                Integer bankId) implements Serializable {}
