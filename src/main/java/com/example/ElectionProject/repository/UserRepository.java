package com.example.ElectionProject.repository;

import com.example.ElectionProject.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.awt.print.Pageable;
import java.util.List;

public interface UserRepository extends MongoRepository<User,Long> {
    List<User> findByFirstNameStartingWith(String firstname);
    List<User> findByCity(String city);

    @Query("{'firstName': {$regex:?0},'middleName': {$regex:?1},'lastName': {$regex:?2},'city': {$regex:?3}}")
    List<User> findBy(String firstName,String middleName,String lastName,String city,Pageable pageable);

}
