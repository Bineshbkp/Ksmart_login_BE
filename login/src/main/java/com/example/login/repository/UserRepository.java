package com.example.login.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.login.model.User;
@Repository

public interface UserRepository extends JpaRepository<User, UUID>{

       
    Optional<User> findByEmail(String email);

    Optional<User> findByPhoneNumber(String phoneNumber);

    Optional<User> findByPhoneNumberAndEmail(String phoneNumber, String email);

   

    
    
    
}
