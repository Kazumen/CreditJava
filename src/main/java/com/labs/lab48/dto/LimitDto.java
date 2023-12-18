package com.labs.lab48.dto;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Date;

public record LimitDto(Long id, @NotNull Date createdAt,
                       @NotNull Integer userId,
                       @NotNull Integer bankId,
                       Double maxLimit,
                       Double currentLimit) implements Serializable  {
}
