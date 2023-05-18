package com.example.ElectionProject.service;

import com.example.ElectionProject.message.QueryResponse;
import com.example.ElectionProject.models.City;
import com.example.ElectionProject.models.State;
import com.example.ElectionProject.models.Voter;
import com.example.ElectionProject.models.Ward;
import com.example.ElectionProject.repository.CityRepository;
import com.example.ElectionProject.repository.StateRepository;
import com.example.ElectionProject.repository.VoterRepository;
import com.example.ElectionProject.repository.WardRepository;
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
    private StateRepository stateRepository;
    private CityRepository cityRepository;
    private WardRepository wardRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    public QueryResponse findBy(String voterId,String firstName, String middleName, String lastName, String gender, int age, String stateName, String cityName, String wardName, Pageable pageable){
        try{
            Query query = new Query();
            if(voterId=="" && firstName=="" && middleName=="" && lastName=="" && gender=="" && age==-1 && stateName=="" && cityName=="" && wardName==""){
                return new QueryResponse(new ArrayList<Voter>(),0);
            }
            if(stateName!="") {
                query.addCriteria(Criteria.where("ward.city.state.name").regex(stateName));
            }
            if(cityName!=""){
                query.addCriteria(Criteria.where("ward.city.name").regex(cityName));
            }
            if(wardName!=""){
                query.addCriteria(Criteria.where("ward.name").regex(wardName));
            }
            if(voterId!=""){;
                voterId="^"+voterId;
                query.addCriteria(Criteria.where("voterId").regex(voterId));
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
            long count=mongoTemplate.count(query,Voter.class);

            Query voterQuery = query.with(pageable);
            List<Voter> filteredVoter = mongoTemplate.find(voterQuery, Voter.class);
            Page<Voter> resultantVoter=PageableExecutionUtils.getPage(filteredVoter,pageable,()->count);
////            System.out.println(resultantVoter+" Total Pages "+resultantVoter.getTotalPages()+" Total results "+resultantVoter.getTotalElements());
            QueryResponse results=new QueryResponse(filteredVoter,resultantVoter.getTotalPages());
            return results;
        }
        catch(Exception e){
            e.printStackTrace();
            return new QueryResponse(new ArrayList<Voter>(),0);
        }
    }

    
}
