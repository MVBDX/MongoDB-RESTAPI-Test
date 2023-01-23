package com.example.mongodb_test.service;

import com.example.mongodb_test.entity.Student;
import com.example.mongodb_test.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    public List<Student> fetchAllStudents() {
        return studentRepository.findAll();
    }
}
