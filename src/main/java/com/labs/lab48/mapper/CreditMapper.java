package com.labs.lab48.mapper;

import com.labs.lab48.dto.CreditCreatingDto;
import com.labs.lab48.dto.CreditDto;
import com.labs.lab48.dto.UserCreatingDto;
import com.labs.lab48.entity.Credit;
import com.labs.lab48.entity.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CreditMapper {
    CreditDto creditToCreditDto(Credit credit);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Credit creditCreatingDtoToCredit(CreditCreatingDto creditCreatingDto);
}
