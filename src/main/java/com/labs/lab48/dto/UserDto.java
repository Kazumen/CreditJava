package com.labs.lab48.dto;

import java.io.Serializable;
import java.util.List;

public record UserDto(Integer id, String name, String surname, String email, List<ContractDto> contracts) implements Serializable {
}
