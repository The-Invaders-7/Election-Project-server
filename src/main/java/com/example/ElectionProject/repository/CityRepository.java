package com.example.ElectionProject.repository;

import com.example.ElectionProject.models.City;
import com.example.ElectionProject.models.State;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CityRepository  extends MongoRepository<City,Integer> {
    public City findByNameAndState(String cityName,State state);
}
