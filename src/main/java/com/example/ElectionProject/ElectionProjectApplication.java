package com.example.ElectionProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;



@RestController
@SpringBootApplication
public class ElectionProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElectionProjectApplication.class, args);
	}


	//home page
	@RequestMapping("/")
	public String home(){
		return "Main Page Electon Management System, testing";
	}
}
