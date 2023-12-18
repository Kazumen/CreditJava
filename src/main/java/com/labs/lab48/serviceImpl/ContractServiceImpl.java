package com.labs.lab48.serviceImpl;

import com.labs.lab48.dto.ContractCreatingDto;
import com.labs.lab48.dto.ContractDto;
import com.labs.lab48.dto.CreditDto;
import com.labs.lab48.dto.LimitDto;
import com.labs.lab48.entity.*;
import com.labs.lab48.exception.LimitAlreadyExistsException;
import com.labs.lab48.exception.SumOutOfLimitException;
import com.labs.lab48.mapper.ContractMapper;
import com.labs.lab48.repository.*;
import com.labs.lab48.service.ContractService;
import com.labs.lab48.service.CreditService;
import com.labs.lab48.service.EmailService;
import com.labs.lab48.service.LimitService;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.NoSuchElementException;
import java.util.Optional;

@AllArgsConstructor
@Service
@Slf4j
public class ContractServiceImpl  implements ContractService {
    private final ContractRepository contractRepository;
    private ContractMapper contractMapper;
    private final UserRepository userRepository;
    private final CreditRepository creditRepository;
    private final LimitService limitService;
    private final LimitRepository limitRepository;
    private EmailService emailService;
    @Override
    public Page<ContractDto> getAllContracts(Pageable pageable) {
        log.info("Return all contracts.");
        return contractRepository.findAll(pageable).map(contractMapper::contractToContractDto);

    }

    @Override
    public ContractDto getContract(Integer id) {
        Optional<Contract> optionalContract = contractRepository.findById(id);
        Contract contract = optionalContract.orElseThrow(() -> new NoSuchElementException("There is no contract with that id!"));
        log.info("Contract with id "+ id+ " was found!");
        return contractMapper.contractToContractDto(contract);
    }

    @Override
    public Integer deleteContract(Integer id) {
        contractRepository.deleteById(id);
        log.info("Contract was deleted.");
        return id;
    }

    @Override
    public Page<ContractDto> getContractByUser(Pageable pageable, Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.orElseThrow(() -> new NoSuchElementException("There is no user with that id!"));
        log.info("Returned all contracts by user.");
        return contractRepository.findAllByUser(user, pageable);

    }

    @Override
    public ContractDto createContract(Integer creditId, Integer userId) throws SumOutOfLimitException, LimitAlreadyExistsException {
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<Credit> optionalCredit = creditRepository.findById(creditId);
        Credit credit = optionalCredit.orElseThrow(() -> new NoSuchElementException("There is no credit with that id!"));
        User user = optionalUser.orElseThrow(() -> new NoSuchElementException("There is no user with that id!"));
        Limit limit=limitRepository.findByUserAndBank(user, credit.getBank());
        if (limit!=null){
            if (credit.getMaxSum()>limit.getCurrentLimit())
                throw new SumOutOfLimitException("Sum of credit is out of limit for this bank");
            limit.setCurrentLimit(limit.getCurrentLimit()-credit.getMaxSum());
            limitRepository.save(limit);
        }else {
            if (credit.getMaxSum()>credit.getBank().getMaxLimit())
                throw new SumOutOfLimitException("Sum of credit is out of limit for this bank");
            limitService.addLimit(userId,credit.getBank().getId());
            Limit tempLimit = limitRepository.findByUserAndBank(user,credit.getBank());
            tempLimit.setCurrentLimit(tempLimit.getCurrentLimit()-credit.getMaxSum());
            limitRepository.save(tempLimit);
        }

        Contract contract = new Contract();
        contract.setUser(user);
        contract.setCredit(credit);
        contract.setRepayment(credit.getMaxSum());
        contract.setOpened(true);
        log.info("Contract was created.");
        return contractMapper.contractToContractDto(contractRepository.save(contract));
    }
    @Override
    public ContractDto payRepayment(Double payment, Integer id) {
        Optional<Contract> contractOptional = contractRepository.findById(id);
        Contract contract = contractOptional.orElseThrow(() -> {
            try {
                emailService.sendEmail("yurii.romanchak.oi.2023@lpnu.ua", String.valueOf(new NoSuchElementException("There is no contract with that id!")));
            } catch (MessagingException | UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
            return null;
        });
        if (!contract.getOpened()){
            ResponseEntity.ok("You`re already cancel a credit!");
            log.info("Credit was already canceled.");
            return contractMapper.contractToContractDto(contractRepository.save(contract));
        }
        contract.setRepayment(contract.getRepayment()-payment);
        if (contract.getRepayment()<=0){
            ResponseEntity.ok("Congratulation, you cancel a credit!");
            contract.setOpened(false);
            Limit limit = limitRepository.findByUserAndBank(contract.getUser(),contract.getCredit().getBank());
            limitService.updateLimit(limit.getMaxLimit()+contract.getCredit().getMaxSum(),limit.getMaxLimit()+contract.getCredit().getMaxSum(),limit.getId());
            log.info("Credit was canceled.");
        }
        return contractMapper.contractToContractDto(contractRepository.save(contract));
    }
}
