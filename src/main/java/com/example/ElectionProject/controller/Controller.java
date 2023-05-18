package com.example.ElectionProject.controller;

import com.example.ElectionProject.message.QueryResponse;
import com.example.ElectionProject.models.City;
import com.example.ElectionProject.models.State;
import com.example.ElectionProject.models.Voter;
import com.example.ElectionProject.models.Ward;
import com.example.ElectionProject.repository.CityRepository;
import com.example.ElectionProject.repository.StateRepository;
import com.example.ElectionProject.repository.VoterRepository;
import com.example.ElectionProject.repository.WardRepository;
import com.example.ElectionProject.service.VoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
public class Controller {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private VoterRepository voterRepository;
    private StateRepository stateRepository;
    private CityRepository cityRepository;
    private WardRepository wardRepository;

    @Autowired
    private VoterService voterService;

    @GetMapping("/get/{VoterId}")
    public ResponseEntity<?> findByVoterId(@PathVariable("VoterId") String voterId){
        Voter voter=this.voterRepository.findByVoterId(voterId);
        return ResponseEntity.ok(voter);
    }

    @GetMapping("/get")
    public ResponseEntity<?> getUser(@RequestParam(value="voterId") String voterId,@RequestParam(value="firstName") String firstName,@RequestParam(value="middleName") String middleName,@RequestParam(value="lastName") String lastName,@RequestParam(value="gender") String gender,@RequestParam(value="age") int age,@RequestParam(value="state") String state, @RequestParam(value="city") String city,@RequestParam(value="ward") String ward,@RequestParam(value="pageNo") int pageNo){
        Pageable paging = PageRequest.of(pageNo, 1);
        QueryResponse voter=this.voterService.findBy(voterId,firstName,middleName,lastName,gender,age,state,city,ward,paging);
        //voter.getTotalPages();
        return ResponseEntity.ok(voter);
    }

//    @GetMapping("/retrieve/{id}")
//    public ResponseEntity<ByteArrayResource> retrieve(@PathVariable String id) throws IOException{
//        ImageFile loadImage=voterService.retrieveImage(id);
//        return ResponseEntity.ok().contentType(MediaType.parseMediaType(loadImage.getFileType())).header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+loadImage.getFileName()+"\"").body(new ByteArrayResource(loadImage.getFile()));
//    }

    //Get request to get all voters
    @GetMapping("/")
    public ResponseEntity<?> getAllVoters(){
        return ResponseEntity.ok(this.voterRepository.findAll());
    }



    //post request to save voter
    @PostMapping("/")
    public ResponseEntity<?> addVoter(@RequestParam(value="voterId") String voterId,@RequestParam(value="firstName") String firstName,@RequestParam(value="middleName") String middleName,@RequestParam(value="lastName") String lastName,@RequestParam(value="gender") String gender,@RequestParam(value="age") int age,@RequestParam(value="voterReceipt") String voterReceipt){
        State state;
        if(this.stateRepository==null){
            state = new State(0,"Maharashtra");
            this.stateRepository.save(state);
        }
        else {
            state = this.stateRepository.findByName("Maharashtra");
            if (state == null) {
                state = new State(0,"Maharashtra");
                this.stateRepository.save(state);
            }
        }
        City city;
        if(this.cityRepository==null){
            city = new City(1, "Nashik", state);
            this.cityRepository.save(city);
        }
        else {
            city = this.cityRepository.findByNameAndState("Nashik", state);
            if (city == null) {
                city = new City(1, "Nashik", state);
                this.cityRepository.save(city);
            }
        }
        Ward ward;
        if(this.wardRepository==null){
            ward=new Ward(2,"Saraf Nagar",city);
            this.wardRepository.save(ward);
        }
        else {
            ward = this.wardRepository.findByNameAndCity("Saraf Nagar", city);
            if (ward == null) {
                ward = new Ward(2, "Saraf Nagar", city);
                this.wardRepository.save(ward);
            }
        }
        Voter voter=new Voter(voterId,firstName,middleName,lastName,gender,age,voterReceipt,ward);
        Voter voterSave=this.voterRepository.save(voter);
        System.out.println(voter.toString()+" "+voterSave);
        return ResponseEntity.ok(voterSave);
    }

    //Delete request to delete voter with a given aadhar_no
    @DeleteMapping("/delete/{aadhar_no}")
    public ResponseEntity<?> deleteVoter(@PathVariable("aadhar_no") long aadhar_no){
        try {
            this.voterRepository.deleteById(aadhar_no);
            return ResponseEntity.ok("Deleted voter with Aadhar no "+aadhar_no);
        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Delete Request to delete all users
    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAllVoter(){
        try {
            this.voterRepository.deleteAll();
            return ResponseEntity.ok("Deleted all voters");
        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Put Request to update voter with giveen aadhar_no
    @PutMapping("/update/{VoterId}")
    public ResponseEntity<?> updateVoter(@PathVariable("VoterId") long voterId,@RequestBody Voter voter){
        try{
            Voter voterUpdated=this.voterRepository.save(voter);
            return ResponseEntity.ok("Update voter with VoterId "+voterId);
        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
