package com.example.ElectionProject.service;

import com.example.ElectionProject.message.QueryResponse;
import com.example.ElectionProject.models.Voter;
import com.example.ElectionProject.repository.VoterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.support.PageableExecutionUtils.getPage;


@Service
public class VoterService {
    @Autowired
    private VoterRepository voterRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    public QueryResponse findBy(String firstName, String middleName, String lastName, String gender, int age, String district, String city, String ward, Pageable pageable){
        try{
            Query query = new Query();
            if(firstName=="" && middleName=="" && lastName=="" && gender=="" && age==-1 && district=="" && city=="" && ward==""){
                return new QueryResponse(new ArrayList<Voter>(),0);
            }
            if(firstName!=""){;
                firstName="^"+firstName;
                query.addCriteria(Criteria.where("firstName").regex(firstName));
            }
            if(middleName!=""){
                middleName="^"+middleName;
                query.addCriteria(Criteria.where("middleName").regex("^"+middleName));
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
                query.addCriteria(Criteria.where("age").is(age));
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
            long count=mongoTemplate.count(query,Voter.class);
            Query voterQuery = query.with(pageable);
            List<Voter> filteredVoter = mongoTemplate.find(voterQuery, Voter.class);
            Page<Voter> resultantVoter=PageableExecutionUtils.getPage(filteredVoter,pageable,()->count);
//            System.out.println(resultantVoter+" Total Pages "+resultantVoter.getTotalPages()+" Total results "+resultantVoter.getTotalElements());
            QueryResponse results=new QueryResponse(filteredVoter,resultantVoter.getTotalPages());
            return results;
        }
        catch(Exception e){
            e.printStackTrace();
            return new QueryResponse(new ArrayList<Voter>(),0);
        }
    }

    
}
