package com.example.ElectionProject.repository;

import com.example.ElectionProject.models.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AdminRepository extends MongoRepository<Admin,Long> {

    Admin findByEmail(String email);
    Boolean existsByEmail(String email);
    Boolean existsByaadharNo(Long aadharNo);
}
