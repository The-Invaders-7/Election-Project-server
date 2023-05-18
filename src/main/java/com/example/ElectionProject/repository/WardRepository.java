package com.example.ElectionProject.repository;

import com.example.ElectionProject.models.City;
import com.example.ElectionProject.models.State;
import com.example.ElectionProject.models.Ward;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WardRepository  extends MongoRepository<Ward,Integer> {
    public Ward findByNameAndCity(String wardName, City city);
}
