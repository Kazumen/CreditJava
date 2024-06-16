package com.labs.lab48.serviceImpl;

import com.labs.lab48.dto.LimitDto;
import com.labs.lab48.entity.Bank;
import com.labs.lab48.entity.Limit;
import com.labs.lab48.entity.User;
import com.labs.lab48.exception.CreditAlreadyExistsException;
import com.labs.lab48.exception.LimitAlreadyExistsException;
import com.labs.lab48.mapper.LimitMapper;
import com.labs.lab48.repository.BankRepository;
import com.labs.lab48.repository.LimitRepository;
import com.labs.lab48.repository.UserRepository;
import com.labs.lab48.service.EmailService;
import com.labs.lab48.service.LimitService;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.NoSuchElementException;
import java.util.Optional;

@AllArgsConstructor
@Service
@Slf4j
public class LimitServiceImpl implements LimitService {
    private LimitRepository limitRepository;
    private LimitMapper limitMapper;
    private UserRepository userRepository;
    private BankRepository bankRepository;

    @Override
    public Page<LimitDto> getAllLimits(Pageable pageable) {
        log.info("Return all limits.");
        return limitRepository.findAll(pageable).map(limitMapper::limitToLimitDto);
    }

    @Override
    public LimitDto getLimit(Integer id) {
        Optional<Limit> optionalLimit = limitRepository.findById(id);
        Limit limit = optionalLimit.orElseThrow(() -> new NoSuchElementException("There is no limit with that id!"));
        log.info("Limit with id " + id + "was found!");
        return limitMapper.limitToLimitDto(limit);
    }


    @Override
    public Integer delete(Integer id) {
        log.info("Limit was deleted.");
        limitRepository.deleteById(id);
        return id;
    }

    @Override
    public Page<LimitDto> getLimitsByUser(Pageable pageable, Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.orElseThrow(() -> new NoSuchElementException("There is no user with that id!"));
        log.info("Returned limits by user.");
        return limitRepository.findAllByUser(user, pageable).map(limitMapper::limitToLimitDto);

    }

    @Override
    public Page<LimitDto> getLimitsByBank(Pageable pageable, Integer id) {
        Optional<Bank> optionalBank = bankRepository.findById(id);
        Bank bank = optionalBank.orElseThrow(() -> new NoSuchElementException("There is no bank with that id!"));
        log.info("Returned limits by bank.");
        return limitRepository.findAllByBank(bank, pageable).map(limitMapper::limitToLimitDto);

    }

    @Override
    public LimitDto addLimit(Integer userId, Integer bankId) throws LimitAlreadyExistsException {
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<Bank> optionalBank = bankRepository.findById(bankId);
        Limit limit = new Limit();
        User user = optionalUser.orElseThrow(NullPointerException::new);
        Bank bank = optionalBank.orElseThrow(NullPointerException::new);
        if (limitRepository.findByUserAndBank(user, bank) != null)
            throw new LimitAlreadyExistsException("Limit with that user and bank already exist");
        limit.setUser(user);
        limit.setBank(bank);
        limit.setMaxLimit(bank.getMaxLimit());
        limit.setCurrentLimit(limit.getMaxLimit());
        log.info("Limit was added.");
        return limitMapper.limitToLimitDto(limitRepository.save(limit));
    }

    @Override
    public LimitDto updateLimit(Double maxLimit, Double currentLimit, Integer id) {
        Optional<Limit> optionalLimit = limitRepository.findById(id);
        Limit limit = optionalLimit.orElseThrow(() -> new NoSuchElementException("There is no limit with that id!"));

        if (currentLimit != null)
            limit.setCurrentLimit(currentLimit);
        if (maxLimit != null)
            limit.setMaxLimit(maxLimit);

        log.info("Limit was edited.");
        return limitMapper.limitToLimitDto(limitRepository.save(limit));
    }

    @Override
    public LimitDto getLimitByUserAndBank(Integer userId, Integer bankId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<Bank> optionalBank = bankRepository.findById(bankId);
        User user = optionalUser.orElseThrow(NullPointerException::new);
        Bank bank = optionalBank.orElseThrow(NullPointerException::new);
        Limit limit = limitRepository.findByUserAndBank(user, bank);
        if (limit == null) {
            throw new NoSuchElementException("No limit found for the given user and bank.");
        }

        log.info("Limit found for user id {} and bank id {}", userId, bankId);
        return limitMapper.limitToLimitDto(limit);
    }
}



