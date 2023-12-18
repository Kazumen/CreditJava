package com.labs.lab48.dto;

import java.io.Serializable;

public record BankDto(Integer id, String name, String owner, String address, String website, Double maxLimit) implements Serializable {
}
