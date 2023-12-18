package com.labs.lab48.dto;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record ContractDto(Integer id,Date createdAt, Integer userId,
                          Integer creditId, Double repayment,
                          Boolean opened) {
}
