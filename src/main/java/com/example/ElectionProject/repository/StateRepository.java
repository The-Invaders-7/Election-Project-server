package com.example.ElectionProject.repository;
import com.example.ElectionProject.models.State;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StateRepository extends MongoRepository<State,Integer> {
    public State findByName(String stateName);
}