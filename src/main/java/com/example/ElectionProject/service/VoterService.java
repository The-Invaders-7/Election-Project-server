package com.example.ElectionProject.service;

import com.example.ElectionProject.models.User;
import com.example.ElectionProject.repository.UserRepository;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class VoterService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findBy(String firstName, String middleName, String lastName, String city){
        try{
            firstName="^"+firstName;
            middleName="^"+middleName;
            lastName="^"+lastName;
            city="^"+city;
            return this.userRepository.findBy(firstName,middleName,lastName,city);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ArrayList<User>();
        }
    }

    
}
