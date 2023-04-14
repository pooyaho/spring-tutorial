package ir.mapsa.secondspringproject.tutorials1.repositories;

import ir.mapsa.secondspringproject.tutorials1.models.StudentDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface StudentMongoRepository extends MongoRepository<StudentDocument, String> {
    @Query("db.student_collection.aggregate([\n" +
            "    {\n" +
            "        $project: {\"s\": \"$$ROOT\", \"_id\": 0}\n" +
            "    },\n" +
            "    {\n" +
            "        $lookup: {\n" +
            "            localField: \"s._id\",\n" +
            "            from: \"st_co\",\n" +
            "            foreignField: \"st_id\",\n" +
            "            as: \"sc\"\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        $unwind: {\n" +
            "            path: \"$sc\",\n" +
            "            preserveNullAndEmptyArrays: false\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        $group: {\n" +
            "            _id: {\"s᎐_id\": \"$s._id\"},\n" +
            "            \"count\": {$sum: 1}\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        $match: {$expr: {$gt: [\"$count\", 20]}}\n" +
            "    },\n" +
            "    {\n" +
            "        $sort: {\"_id.s᎐_id\": 1}\n" +
            "    },\n" +
            "    {\n" +
            "        $project: {\"_id\": \"$_id.s᎐_id\", \"count(*)\": \"$count\"}\n" +
            "    }\n" +
            "])")
    List<Object> findStudents();

}
