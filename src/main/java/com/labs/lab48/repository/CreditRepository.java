package com.labs.lab48.repository;

import com.labs.lab48.entity.Bank;
import com.labs.lab48.entity.Credit;
import org.springframework.data.domain.Page;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Transactional
@Repository
public interface CreditRepository extends JpaRepository<Credit,Integer> {
    Credit findByName(String name);

    Page<Credit> findAllByBank(Bank bank, Pageable pageable);
}
