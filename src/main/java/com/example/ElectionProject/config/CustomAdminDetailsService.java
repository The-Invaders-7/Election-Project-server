package com.example.ElectionProject.config;

import com.example.ElectionProject.models.Admin;
import com.example.ElectionProject.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;

public class CustomAdminDetailsService implements UserDetailsService {
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        Admin admin = adminRepository.findByEmail(userName);

        return new Admin(admin.getAadharNo(),admin.getFirstName(), admin.getMiddleName(), admin.getLastName(), admin.getEmail(), admin.getPassword());
    }

}
