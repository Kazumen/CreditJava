package com.labs.lab48.serviceImpl;

import com.labs.lab48.dto.BankCreatingDto;
import com.labs.lab48.dto.BankDto;
import com.labs.lab48.entity.Bank;

import com.labs.lab48.exception.BankAlreadyExistsException;
import com.labs.lab48.mapper.BankMapper;
import com.labs.lab48.repository.BankRepository;
import com.labs.lab48.service.BankService;
import com.labs.lab48.service.EmailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@AllArgsConstructor
@Service
@Slf4j
public class BankServiceImpl implements BankService {
    private BankRepository bankRepository;
    private BankMapper bankMapper;

    @Override
    public Page<BankDto> getAllBanks(Pageable pageable) {
        log.info("Return all banks.");
        return bankRepository.findAll(pageable).map(bankMapper::bankToBankDto);
    }

    @Override
    public BankDto getBank(Integer id) {
        Optional<Bank> optionalBank = bankRepository.findById(id);
        Bank bank = optionalBank.orElseThrow(() -> new NoSuchElementException("There is no bank with that id!"));
        log.info("Bank with id "+ id+ "was found!");
        return bankMapper.bankToBankDto(bank);
    }

    @Override
    public BankDto addBank(BankCreatingDto bankCreatingDto) throws BankAlreadyExistsException {
        Bank bank = new Bank();
        if (bankRepository.findByName(bankCreatingDto.name())!=null){
            throw new BankAlreadyExistsException("Bank with that name already exist");
        }
        if (bankRepository.findByWebsite(bankCreatingDto.website())!=null){
            throw new BankAlreadyExistsException("Bank with that website already exist");
        }
        bank.setName(bankCreatingDto.name());
        bank.setOwner(bankCreatingDto.owner());
        bank.setAddress(bankCreatingDto.address());
        bank.setWebsite(bankCreatingDto.website());
        bank.setMaxLimit(bankCreatingDto.maxLimit());
        log.info("Bank was added.");
        return bankMapper.bankToBankDto(bankRepository.save(bank));

    }

    @Override
    public BankDto editBank(Integer id, BankCreatingDto bankCreatingDto) throws BankAlreadyExistsException {
        Optional<Bank> optionalBank = bankRepository.findById(id);
        Bank bank =optionalBank.orElseThrow(() -> new NoSuchElementException("There is no bank with that id!"));
        if (bankCreatingDto.name()!=null) {
            if (bankRepository.findByName(bankCreatingDto.name()) != null)
                throw new BankAlreadyExistsException("Genre with that name already exist");
            bank.setName(bankCreatingDto.name());
        }
        if (bankCreatingDto.owner()!=null)
            bank.setOwner(bankCreatingDto.owner());
        if (bankCreatingDto.address()!=null)
            bank.setAddress(bankCreatingDto.address());
        if (bankCreatingDto.website()!=null)
            bank.setWebsite(bankCreatingDto.website());
        if (bankCreatingDto.maxLimit()!=null)
            bank.setMaxLimit(bankCreatingDto.maxLimit());
        log.info("Bank was edited.");
        return bankMapper.bankToBankDto(bankRepository.save(bank));
    }

    @Override
    public Integer deleteBank(Integer id) {
        bankRepository.deleteById(id);
        log.info("Bank was deleted.");
        return id;
    }
}
