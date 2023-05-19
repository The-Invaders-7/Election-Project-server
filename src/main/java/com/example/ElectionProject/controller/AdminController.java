package com.example.ElectionProject.controller;


import com.example.ElectionProject.message.LoginResponse;
import com.example.ElectionProject.message.MessageResponse;
import com.example.ElectionProject.models.Admin;
import com.example.ElectionProject.models.Voter;
import com.example.ElectionProject.repository.AdminRepository;
import com.example.ElectionProject.service.AdminService;
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
    private AdminService adminService;

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

    @PostMapping("/login")
    public ResponseEntity<?>  login(@RequestBody Admin admin){
        LoginResponse adminDetails=this.adminService.login(admin);
        return ResponseEntity.ok(adminDetails);
    }

    @PostMapping("/register")
    public ResponseEntity<?>  register(@RequestBody Admin admin){
        Admin adminSave=this.adminRepository.save(admin);
        return ResponseEntity.ok(adminSave);
    }




}
