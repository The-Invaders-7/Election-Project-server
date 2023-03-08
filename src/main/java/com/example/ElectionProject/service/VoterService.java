package com.example.ElectionProject.service;

import com.example.ElectionProject.models.Voter;
import com.example.ElectionProject.repository.VoterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VoterService {
    @Autowired
    private VoterRepository voterRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    public List<Voter> findBy(String firstName,String middleName,String lastName,String gender,int age,String district,String city,String ward, Pageable pageable){
        try{
            Query query = new Query();
            query.with(pageable);
            if(firstName=="" && middleName=="" && lastName=="" && gender=="" && age==-1 && district=="" && city=="" && ward==""){
                return mongoTemplate.find(query, Voter.class);
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
            if(gender!=""){
                gender="^"+gender;
                query.addCriteria(Criteria.where("gender").regex("^"+gender));
            }
            if(age!=-1){
                query.addCriteria(Criteria.where("$where").is("/^"+age+".*/.test(this.age)"));
            }
            if(district!="") {
                district = "^" + district;
                query.addCriteria(Criteria.where("district").regex("^" + district));
            }
            if(city!=""){
                city="^"+city;
                query.addCriteria(Criteria.where("city").regex("^"+city));
            }
            if(ward!=""){
                ward="^"+ward;
                query.addCriteria(Criteria.where("ward").regex("^"+ward));
            }
            List<Voter> voters = mongoTemplate.find(query, Voter.class);
            return voters;
        }
        catch(Exception e){
            e.printStackTrace();
            return new ArrayList<Voter>();
        }
    }

    
}
