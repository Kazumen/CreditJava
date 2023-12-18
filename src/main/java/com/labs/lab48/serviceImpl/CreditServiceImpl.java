package com.labs.lab48.serviceImpl;

import com.labs.lab48.dto.CreditCreatingDto;
import com.labs.lab48.dto.CreditDto;

import com.labs.lab48.entity.Bank;
import com.labs.lab48.entity.Credit;
import com.labs.lab48.exception.CreditAlreadyExistsException;

import com.labs.lab48.mapper.CreditMapper;
import com.labs.lab48.repository.BankRepository;
import com.labs.lab48.repository.CreditRepository;
import com.labs.lab48.service.CreditService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
@Slf4j
@AllArgsConstructor
@Service
public class CreditServiceImpl  implements CreditService {
    private CreditRepository creditRepository;
    private CreditMapper creditMapper;
    private BankRepository bankRepository;
    
    @Override
    public Page<CreditDto> getAllCredits(Pageable pageable) {
        log.info("Return all credits.");
        return creditRepository.findAll(pageable).map(creditMapper::creditToCreditDto);
    }

    @Override
    public CreditDto getCredit(Integer id) {
        Optional<Credit> optionalCredit = creditRepository.findById(id);
        Credit credit = optionalCredit.orElseThrow(() -> new NoSuchElementException("There is no credit with that id!"));
        log.info("Credit with id "+ id+ "was found!");
        return creditMapper.creditToCreditDto(credit);
    }

    @Override
    public CreditDto addCredit(CreditCreatingDto creditCreatingDto) {
        Credit credit = new Credit();
        credit.setName(creditCreatingDto.name());
        credit.setMaxSum(creditCreatingDto.maxSum());
        credit.setCommission(creditCreatingDto.commission());
        credit.setContractTerm(creditCreatingDto.contractTerm());
        Optional<Bank> optionalBank =bankRepository.findById(creditCreatingDto.bankId());
        Bank bank= optionalBank.orElseThrow(() -> new NoSuchElementException("There is no bank with that id!"));
        credit.setBank(bank);
        log.info("Credit was Created.");
        return creditMapper.creditToCreditDto(creditRepository.save(credit));
    }

    @Override
    public CreditDto editCredit(Integer id, CreditCreatingDto creditCreatingDto) throws CreditAlreadyExistsException {
        Optional<Credit> optionalCredit = creditRepository.findById(id);
        Credit credit =optionalCredit.orElseThrow(() -> new NoSuchElementException("There is no credit with that id!"));
        if (creditCreatingDto.name()!=null) {
            if (creditRepository.findByName(creditCreatingDto.name()) != null)
                throw new CreditAlreadyExistsException("Credit with that name already exist");
            credit.setName(creditCreatingDto.name());
        }
        if (creditCreatingDto.contractTerm()!=null)
            credit.setContractTerm(creditCreatingDto.contractTerm());
        if (creditCreatingDto.maxSum()!=null)
            credit.setMaxSum(creditCreatingDto.maxSum());
        if (creditCreatingDto.commission()!=null)
            credit.setCommission(creditCreatingDto.commission());
        log.info("Credit was edited.");
        return creditMapper.creditToCreditDto(creditRepository.save(credit));
    }

    @Override
    public Page<CreditDto> getCreditsByBank(Pageable pageable, Integer id) {
        Optional<Bank> optionalBank = bankRepository.findById(id);
        Bank bank = optionalBank.orElseThrow(() -> new NoSuchElementException("There is no bank with that id!"));
        log.info("Return all credits by bank.");
        return creditRepository.findAllByBank(bank,pageable).map(creditMapper::creditToCreditDto);
    }

    @Override
    public Integer deleteCredit(Integer id) {
        creditRepository.deleteById(id);
        log.info("Credit was deleted.");
        return id;
    }



}
