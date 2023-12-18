package com.labs.lab48.mapper;

import com.labs.lab48.dto.BankCreatingDto;
import com.labs.lab48.dto.BankDto;
import com.labs.lab48.dto.UserCreatingDto;
import com.labs.lab48.dto.UserDto;
import com.labs.lab48.entity.Bank;
import com.labs.lab48.entity.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface BankMapper {
    BankDto bankToBankDto(Bank bank);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Bank bankCreatingDtoToUser(BankCreatingDto bankCreatingDto);
}
