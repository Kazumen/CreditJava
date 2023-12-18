package com.labs.lab48.mapper;

import com.labs.lab48.dto.ContractDto;
import com.labs.lab48.entity.Contract;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ContractMapper {
    ContractDto contractToContractDto(Contract contract);

}
