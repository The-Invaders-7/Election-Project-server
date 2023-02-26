package com.example.ElectionProject.models;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="users")
public class User {

    @Id
    private long aadhar_no;
    private String first_name;

    private String last_name;
    private String city;

    public long getAadhar_no() {
        return aadhar_no;
    }

    public void setAadhar_no(long aadhar_no) {
        this.aadhar_no = aadhar_no;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public User(long aadhar_no, String first_name, String last_name, String city) {
        this.aadhar_no = aadhar_no;
        this.first_name = first_name;
        this.last_name = last_name;
        this.city = city;
    }


}
