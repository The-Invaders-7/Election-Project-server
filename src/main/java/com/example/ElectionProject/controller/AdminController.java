package com.example.ElectionProject.controller;


import com.example.ElectionProject.message.MessageResponse;
import com.example.ElectionProject.models.Admin;
import com.example.ElectionProject.models.Voter;
import com.example.ElectionProject.repository.AdminRepository;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    AdminRepository adminRepository;

   @GetMapping("/register")
    public ResponseEntity<?> register(){
//       BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//       String encodedPassword = passwordEncoder.encode(admin.getPassword());
//       admin.setPassword(encodedPassword);
//
//       adminRepository.save(admin);

       return ResponseEntity.ok(this.adminRepository.findAll());
   }

    @PostMapping("/register")
    public ResponseEntity<?>  register(@RequestBody Admin admin){
        admin.setPassword(this.bCryptPasswordEncoder.encode(admin.getPassword()));
        Admin adminSave=this.adminRepository.save(admin);
        return ResponseEntity.ok(adminSave);
    }




}
