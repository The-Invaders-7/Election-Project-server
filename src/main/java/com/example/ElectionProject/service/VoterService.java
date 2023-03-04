package com.example.ElectionProject.service;

import com.example.ElectionProject.models.User;
import com.example.ElectionProject.repository.UserRepository;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
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

    @Autowired
    MongoTemplate mongoTemplate;

    public List<User> findBy(String firstName, String middleName, String lastName, String city, Pageable pageable){
        try{
            Query query = new Query();
            query.with(pageable);
            if(firstName=="" && middleName=="" && lastName=="" && city==""){
                return mongoTemplate.find(query, User.class);
            }
            if(firstName!=""){;
                firstName="^"+firstName;
                query.addCriteria(Criteria.where("firstName").regex(firstName));
            }
            if(middleName!=""){
                middleName="^"+middleName;
                query.addCriteria(Criteria.where("lastName").regex("^"+lastName));
            }
            if(lastName!=""){
                lastName="^"+lastName;
                query.addCriteria(Criteria.where("lastName").regex("^"+lastName));
            }
            if(city!=""){
                city="^"+city;
                query.addCriteria(Criteria.where("city").regex("^"+city));
            }
            List<User> users = mongoTemplate.find(query, User.class);
            return users;
        }
        catch(Exception e){
            e.printStackTrace();
            return new ArrayList<User>();
        }
    }

    
}
