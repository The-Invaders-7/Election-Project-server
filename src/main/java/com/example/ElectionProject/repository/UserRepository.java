package com.example.ElectionProject.repository;

import com.example.ElectionProject.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,Long> {
}
