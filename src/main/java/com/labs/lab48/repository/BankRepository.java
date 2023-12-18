package com.labs.lab48.repository;

import com.labs.lab48.entity.Bank;
import com.labs.lab48.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface BankRepository extends JpaRepository<Bank,Integer> {
    Bank findByName(String name);

    Bank findByWebsite(String website);
}
