package com.labs.lab48.repository;

import com.labs.lab48.dto.ContractDto;
import com.labs.lab48.entity.Bank;
import com.labs.lab48.entity.Contract;
import com.labs.lab48.entity.Credit;
import com.labs.lab48.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface ContractRepository extends JpaRepository<Contract,Integer> {
    Page<ContractDto> findAllByUser(User user, Pageable pageable);
}
