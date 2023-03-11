package com.example.ElectionProject.security;

import com.example.ElectionProject.models.Admin;
import com.example.ElectionProject.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomAdminDetailService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //loading user from database by username
        //Admin user = this.adminRepository.findByUserName(username).orElseThrow(() -> new ConfigDataResourceNotFoundException("User ", " email : " + username, 0));
        Admin admin=this.adminRepository.findByUserName(username);
        if(admin==null){
            throw new UsernameNotFoundException("Could not found user !!");
        }
        CustomAdminDetails customAdminDetails=new CustomAdminDetails(admin);
        return customAdminDetails;
    }
}
