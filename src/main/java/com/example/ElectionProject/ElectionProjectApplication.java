package com.example.ElectionProject;

import com.example.ElectionProject.models.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@SpringBootApplication
public class ElectionProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElectionProjectApplication.class, args);
	}

	@RequestMapping("/")
	public String home(){
		return "Main Page Electon Management System";
	}
}
