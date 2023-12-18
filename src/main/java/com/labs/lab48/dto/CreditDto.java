package com.labs.lab48.dto;

public record CreditDto(Integer id, String name, Double maxSum, Double commission, Long contractTerm, String bankName) {
}
