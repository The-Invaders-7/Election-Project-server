package com.example.ElectionProject.controller;

import com.example.ElectionProject.models.User;
import com.example.ElectionProject.repository.UserRepository;
import com.example.ElectionProject.service.VoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
public class Controller {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VoterService voterService;

//    @GetMapping("/get/{name}")
//    public ResponseEntity<?> register(@PathVariable("name") String name){
//        List<User> user=this.userRepository.findByFirstNameStartingWith(name);
//        return ResponseEntity.ok(user);
//    }

    @GetMapping("/get")
    public ResponseEntity<?> getUser(@RequestParam(value="firstName") String firstName,@RequestParam(value="middleName") String middleName,@RequestParam(value="lastName") String lastName, @RequestParam(value="city") String city){
        System.out.println(firstName+" "+middleName+" "+lastName);
        List<User> user=this.voterService.findBy(firstName,middleName,lastName,city);
        return ResponseEntity.ok(user);
    }

//    @GetMapping("/retrieve/{id}")
//    public ResponseEntity<ByteArrayResource> retrieve(@PathVariable String id) throws IOException{
//        ImageFile loadImage=voterService.retrieveImage(id);
//        return ResponseEntity.ok().contentType(MediaType.parseMediaType(loadImage.getFileType())).header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+loadImage.getFileName()+"\"").body(new ByteArrayResource(loadImage.getFile()));
//    }

    //Get request to get all voters
    @GetMapping("/")
    public ResponseEntity<?> getAllVoters(){
        return ResponseEntity.ok(this.userRepository.findAll());
    }
    //post request to save voter
    @PostMapping("/")
    public ResponseEntity<?> addVoter(@RequestBody User user){
        User userSave=this.userRepository.save(user);
        return ResponseEntity.ok(userSave);
    }

    //Delete request to delete voter with a given aadhar_no
    @DeleteMapping("/delete/{aadhar_no}")
    public ResponseEntity<?> deleteVoter(@PathVariable("aadhar_no") long aadhar_no){
        try {
            this.userRepository.deleteById(aadhar_no);
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
            this.userRepository.deleteAll();
            return ResponseEntity.ok("Deleted all voters");
        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Put Request to update voter with giveen aadhar_no
    @PutMapping("/update/{aadhar_no}")
    public ResponseEntity<?> updateVoter(@PathVariable("aadhar_no") long aadhar_no,@RequestBody User user){
        try{
            User userUpdated=this.userRepository.save(user);
            return ResponseEntity.ok("Update voter with Aadhar no "+aadhar_no);
        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
