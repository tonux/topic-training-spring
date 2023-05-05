package com.workshop.testapp.repositories;

import com.workshop.testapp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByPhone(String phone);
    User findByEmail(String email);
}
