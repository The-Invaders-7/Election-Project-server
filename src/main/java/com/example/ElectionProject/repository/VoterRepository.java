package com.example.ElectionProject.repository;

import com.example.ElectionProject.models.Voter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.awt.print.Pageable;
import java.util.List;

public interface VoterRepository extends MongoRepository<Voter,Long> {
    List<Voter> findByFirstNameStartingWith(String firstname);

    Voter findByVoterId(String VoterId);
    List<Voter> findByCity(String city);

    @Query("{'firstName': {$regex:?0},'middleName': {$regex:?1},'lastName': {$regex:?2},'gender': {$regex:?3},'age': {$regex:?4},'district': {$regex:?5},'city': {$regex:?6},'ward': {$regex:?7}}")
    List<Voter> findBy(String firstName,String middleName,String lastName,String gender,int age,String district,String city,String ward);

}
