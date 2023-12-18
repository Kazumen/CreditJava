package com.labs.lab48.mapper;

import com.labs.lab48.dto.LimitDto;
import com.labs.lab48.entity.Limit;
import org.mapstruct.BeanMapping;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface LimitMapper {
    LimitDto limitToLimitDto(Limit limit);

}
