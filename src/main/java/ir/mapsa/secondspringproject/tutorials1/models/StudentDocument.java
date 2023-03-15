package ir.mapsa.secondspringproject.tutorials1.models;

import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "student")
@Data
public class StudentDocument {
    @MongoId
    private String id;
    @Indexed(unique = true)
    private String name;
    private String family;
}
