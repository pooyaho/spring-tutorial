package ir.mapsa.secondspringproject.tutorials1.services;

import ir.mapsa.secondspringproject.tutorials1.models.StudentDocument;
import ir.mapsa.secondspringproject.tutorials1.repositories.StudentMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentMongoTest {
    @Autowired
    private StudentMongoRepository repository;

    //    @PostConstruct
    public void init() {
        StudentDocument document = new StudentDocument();

        document.setName("John");
        document.setFamily("Doe");
//        document.setId("1");
        StudentDocument save = repository.save(document);
        System.out.println(save);
    }
}
