package com.labs.lab48.repository;

import com.labs.lab48.dto.LimitDto;
import com.labs.lab48.entity.Bank;
import com.labs.lab48.entity.Limit;
import com.labs.lab48.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Transactional
@Repository
public interface LimitRepository extends JpaRepository<Limit,Integer> {
    Page<Limit> findAllByBank(Bank bank, Pageable pageable);
    Page<Limit> findAllByUser(User user, Pageable pageable);
    Limit findByUserAndBank(User user, Bank bank);
}

