package com.workshop.testapp.repositories;

import com.workshop.testapp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByPhone(String phone);
    User findByEmail(String email);

    Optional<User> findByUsername(String username);

    User findByName(String name);
}
