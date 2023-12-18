package com.labs.lab48.repository;

import com.labs.lab48.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail(String email);

    User findByName(String name);

    User findByNameAndSurname(String name, String surname);
}
