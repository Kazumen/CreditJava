package com.labs.lab48.service;

import com.labs.lab48.dto.LimitDto;
import com.labs.lab48.exception.LimitAlreadyExistsException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LimitService {
    Page<LimitDto> getAllLimits(Pageable pageable);

    LimitDto getLimit(Integer id);

    Integer delete(Integer id);

    Page<LimitDto> getLimitsByUser(Pageable pageable, Integer id);

    Page<LimitDto> getLimitsByBank(Pageable pageable, Integer id);

    LimitDto addLimit(Integer userId, Integer bankId) throws LimitAlreadyExistsException;

    LimitDto updateLimit(Double maxLimit, Double currentLimit, Integer id);

    LimitDto getLimitByUserAndBank(Integer userId, Integer bankId);
}
