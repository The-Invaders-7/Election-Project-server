package com.example.ElectionProject.controller;


import com.example.ElectionProject.message.MessageResponse;
import com.example.ElectionProject.models.Admin;
import com.example.ElectionProject.repository.AdminRepository;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminRepository adminRepository;

   @PostMapping("/register")
    public ResponseEntity<?> register(Admin admin){
       BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
       String encodedPassword = passwordEncoder.encode(admin.getPassword());
       admin.setPassword(encodedPassword);

       adminRepository.save(admin);

       return ResponseEntity.ok(admin);
   }


}
