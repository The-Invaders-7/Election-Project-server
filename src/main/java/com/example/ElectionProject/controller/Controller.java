package com.example.ElectionProject.controller;

import com.example.ElectionProject.models.User;
import com.example.ElectionProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class Controller {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public ResponseEntity<?> getAllVoters(){
        return ResponseEntity.ok(this.userRepository.findAll());
    }

    @PostMapping("/")
    public ResponseEntity<?> addVoter(@RequestBody User user){
        User userSave=this.userRepository.save(user);
        return ResponseEntity.ok(userSave);
    }

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
