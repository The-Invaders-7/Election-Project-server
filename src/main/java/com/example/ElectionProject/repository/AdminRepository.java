package com.example.ElectionProject.repository;

import com.example.ElectionProject.models.Admin;
import com.sun.jdi.BooleanValue;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AdminRepository extends MongoRepository<Admin,Long> {

    Admin findByUserName(String userName);
    Boolean existsByEmail(String email);

    boolean existsByEmailAndPassword(String userName, String password);

    Optional<Admin> findOneByUserNameAndPassword(String userName, String password);
}
