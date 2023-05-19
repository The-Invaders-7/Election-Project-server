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
@Document(collection="Voters")
public class Voter {

    @Id
    private String voterID;
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private int age;
    private String imageString;
    private String district;
    private String city;
    private String ward;
}
