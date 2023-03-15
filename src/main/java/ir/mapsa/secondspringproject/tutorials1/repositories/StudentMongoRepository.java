package ir.mapsa.secondspringproject.tutorials1.repositories;

import ir.mapsa.secondspringproject.tutorials1.models.StudentDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentMongoRepository extends MongoRepository<StudentDocument, String> {

}
