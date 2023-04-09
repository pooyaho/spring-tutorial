package ir.mapsa.secondspringproject.tutorials1.services;

import ir.mapsa.secondspringproject.tutorials1.models.CourseDocument;
import ir.mapsa.secondspringproject.tutorials1.repositories.CourseMongoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CourseMongoTest {
    @Autowired
    private CourseMongoRepository repository;

    @PostConstruct
    @Transactional
    public void init() {
        repository.save(CourseDocument.builder()
                .studentId("123")
                .unit(3)
                .name("Statistics")
                .build());
        repository.save(CourseDocument.builder()
                .studentId("123")
                .unit(3)
                .name("Statistics 2")
                .build());
        throw new IllegalArgumentException();
    }
}
