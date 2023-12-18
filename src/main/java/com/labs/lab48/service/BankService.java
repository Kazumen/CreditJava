package com.labs.lab48.service;

import com.labs.lab48.dto.BankCreatingDto;
import com.labs.lab48.dto.BankDto;
import com.labs.lab48.exception.BankAlreadyExistsException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BankService {
    Page<BankDto> getAllBanks(Pageable pageable);

    BankDto getBank(Integer id);

    BankDto addBank(BankCreatingDto bankCreatingDto) throws BankAlreadyExistsException;

    BankDto editBank(Integer id, BankCreatingDto bankCreatingDto) throws BankAlreadyExistsException;

    Integer deleteBank(Integer id);
}
