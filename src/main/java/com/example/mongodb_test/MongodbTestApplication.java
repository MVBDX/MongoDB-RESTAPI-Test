package com.example.mongodb_test;

import com.example.mongodb_test.entity.Address;
import com.example.mongodb_test.entity.Gender;
import com.example.mongodb_test.entity.Student;
import com.example.mongodb_test.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class MongodbTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongodbTestApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(StudentRepository repository) {
        return args -> {
            Student student = new Student(null, "Mohammad", "Faalian",
                    new Address("Iran", "Tehran", 21),
                    List.of("IT Man", "Programmer", "Pianist"), Gender.MALE, LocalDateTime.now(),
                    "mvbdx3@yahoo.com");
            repository.findStudentByEmail("mvbdx3@yahoo.com")
                    .ifPresentOrElse(s -> {
                                throw new IllegalStateException(student + "is exist");
                            },
                            () -> repository.insert(student));

            // usingMongoTemplate(repository, mongoTemplate, student);
        };
    }

    private static void usingMongoTemplate(StudentRepository repository, MongoTemplate mongoTemplate, Student student) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is("mvbdx@yahoo.com"));
        List<Student> students = mongoTemplate.find(query, Student.class);
        if (students.size() > 0) {
            System.out.println(students.get(0));
            throw new IllegalStateException("found with this email");
        }
        repository.insert(student);
    }

}
