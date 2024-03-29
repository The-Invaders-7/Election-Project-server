package com.example.ElectionProject.controller;

import com.example.ElectionProject.message.QueryResponse;
import com.example.ElectionProject.models.Voter;
import com.example.ElectionProject.repository.VoterRepository;
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

    @Autowired
    private VoterService voterService;

    @GetMapping("/get/{voterID}")
    public ResponseEntity<?> findByVoterID(@PathVariable("voterID") String voterID){
        Voter voter=this.voterRepository.findByVoterID(voterID);
        return ResponseEntity.ok(voter);
    }

    @GetMapping("/get")
    public ResponseEntity<?> getUser(@RequestParam(value="voterID") String voterID,@RequestParam(value="firstName") String firstName,@RequestParam(value="middleName") String middleName,@RequestParam(value="lastName") String lastName,@RequestParam(value="gender") String gender,@RequestParam(value="age") int age,@RequestParam(value="district") String district, @RequestParam(value="city") String city,@RequestParam(value="ward") String ward,@RequestParam(value="pageNo") int pageNo){
        Pageable paging = PageRequest.of(pageNo, 20);
        QueryResponse voter=this.voterService.findBy(voterID,firstName,middleName,lastName,gender,age,district,city,ward,paging);
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
    public ResponseEntity<?> addVoter(@RequestBody Voter user){
        Voter voterSave=this.voterRepository.save(user);
        return ResponseEntity.ok(voterSave);
    }

    //Delete request to delete voter with a given aadhar_no
    @DeleteMapping("/delete/{voterID}")
    public ResponseEntity<?> deleteVoter(@PathVariable("voterID") long voterID){
        try {
            this.voterRepository.deleteById(voterID);
            return ResponseEntity.ok("Deleted voter with Aadhar no "+voterID);
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
    @PutMapping("/update/{VoterID}")
    public ResponseEntity<?> updateVoter(@PathVariable("VoterID") long voterID,@RequestBody Voter voter){
        try{
            Voter voterUpdated=this.voterRepository.save(voter);
            return ResponseEntity.ok("Update voter with voterID "+voterID);
        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
