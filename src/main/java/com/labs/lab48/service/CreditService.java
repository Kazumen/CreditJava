package com.labs.lab48.service;

import com.labs.lab48.dto.BankDto;
import com.labs.lab48.dto.CreditCreatingDto;
import com.labs.lab48.dto.CreditDto;
import com.labs.lab48.exception.CreditAlreadyExistsException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CreditService {
    Page<CreditDto> getAllCredits(Pageable pageable);

    CreditDto getCredit(Integer id);

    CreditDto addCredit(CreditCreatingDto creditCreatingDto);

    CreditDto editCredit(Integer id, CreditCreatingDto creditCreatingDto) throws CreditAlreadyExistsException;

    Page<CreditDto> getCreditsByBank(Pageable pageable, Integer id);

    Integer deleteCredit(Integer id);

}
