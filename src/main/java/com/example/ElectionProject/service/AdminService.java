package com.example.ElectionProject.service;

import com.example.ElectionProject.message.LoginResponse;
import com.example.ElectionProject.models.Admin;
import com.example.ElectionProject.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AdminRepository adminRepository;

    public LoginResponse login(Admin admin){
        if(admin.getEmail()!=null){
            String email= admin.getEmail();
            String password=admin.getPassword();
            Boolean isPresent=false;
            isPresent=this.adminRepository.existsByEmailAndPassword(email,password);

            if(isPresent){
                return new LoginResponse("Login Successful",isPresent);
            }
            return new LoginResponse("Login Failed",false);
        }
        return new LoginResponse("Login Failed",false);
    }
}
