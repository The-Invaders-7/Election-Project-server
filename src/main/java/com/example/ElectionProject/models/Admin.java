package com.example.ElectionProject.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="admin")
public class Admin {
    @Id
    private long aadharNo;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String password;


}
