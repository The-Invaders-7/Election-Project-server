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
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
public class Controller {

    @Autowired
    private VoterRepository voterRepository;

    @Autowired
    private VoterService voterService;

    @GetMapping("/get/{VoterId}")
    public ResponseEntity<?> findByVoterId(@PathVariable("VoterId") String voterId){
        Voter voter=this.voterRepository.findByVoterId(voterId);
        return ResponseEntity.ok(voter);
    }

    @GetMapping("/get")
    public ResponseEntity<?> getUser(@RequestParam(value="voterId") String voterId,@RequestParam(value="firstName") String firstName,@RequestParam(value="middleName") String middleName,@RequestParam(value="lastName") String lastName,@RequestParam(value="gender") String gender,@RequestParam(value="age") int age,@RequestParam(value="district") String district, @RequestParam(value="city") String city,@RequestParam(value="ward") String ward,@RequestParam(value="pageNo") int pageNo){
        Pageable paging = PageRequest.of(pageNo, 20);
        QueryResponse voter=this.voterService.findBy(voterId,firstName,middleName,lastName,gender,age,district,city,ward,paging);
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
