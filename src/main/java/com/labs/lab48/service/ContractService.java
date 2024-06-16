package com.labs.lab48.service;

import com.labs.lab48.dto.ContractCreatingDto;
import com.labs.lab48.dto.ContractDto;
import com.labs.lab48.dto.CreditDto;
import com.labs.lab48.exception.LimitAlreadyExistsException;
import com.labs.lab48.exception.SumOutOfLimitException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContractService {
    Page<ContractDto> getAllContracts(Pageable pageable);

    ContractDto getContract(Integer id);

    void deleteContract(Integer id);

    Page<ContractDto> getContractByUser(Pageable pageable, Integer id);

    ContractDto createContract(Integer creditId, Integer userId) throws LimitAlreadyExistsException, SumOutOfLimitException;

    ContractDto payRepayment(Double payment, Integer id);
}
