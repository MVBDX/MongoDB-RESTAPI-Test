package com.example.mongodb_test.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document
@AllArgsConstructor
public class Student {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private Address address;
    private List<String> jobs;
    private Gender gender;
    private LocalDateTime created;
    @Indexed(unique = true)
    private String email;

/*    public Student(String firstName, String lastName, Address address, List<String> jobs, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.jobs = jobs;
        this.gender = gender;
    }*/
}
