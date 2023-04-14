package ir.mapsa.secondspringproject.tutorials1.services;

import ir.mapsa.secondspringproject.tutorials1.repositories.StudentMongoRepository;
import jakarta.annotation.PostConstruct;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
public class StudentMongoTest {
    @Autowired
    private StudentMongoRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostConstruct
    public void init() {
        String query = "{\n" +
                "  \"aggregate\": \"student_collection\",\n" +
                "  \"pipeline\": [\n" +
                "    {\n" +
                "      \"$project\": {\n" +
                "        \"s\": \"$$ROOT\",\n" +
                "        \"_id\": 0\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"$lookup\": {\n" +
                "        \"localField\": \"s._id\",\n" +
                "        \"from\": \"st_co\",\n" +
                "        \"foreignField\": \"st_id\",\n" +
                "        \"as\": \"sc\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"$unwind\": {\n" +
                "        \"path\": \"$sc\",\n" +
                "        \"preserveNullAndEmptyArrays\": false\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"$group\": {\n" +
                "        \"_id\": {\n" +
                "          \"s᎐_id\": \"$s._id\"\n" +
                "        },\n" +
                "        \"count\": {\n" +
                "          \"$sum\": 1\n" +
                "        }\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"$match\": {\n" +
                "        \"$expr\": {\n" +
                "          \"$gt\":[\n" +
                "            \"$count\",\n" +
                "            20\n" +
                "          ]\n" +
                "        }\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"$sort\": {\n" +
                "        \"_id.s᎐_id\": 1\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"$project\": {\n" +
                "        \"_id\": \"$_id.s᎐_id\",\n" +
                "        \"count(*)\": \"$count\"\n" +
                "      }\n" +
                "    }\n" +
                "  ],\n" +
                "  \"cursor\": { }\n" +
                "}";
        Document x = mongoTemplate.executeCommand(query);

        GroupOperation groupByStateAndSumPop = group("state")
                .sum("1").as("statePop");
        MatchOperation filterStates = match(new Criteria("statePop").gt(10000000));
        SortOperation sortByPopDesc = sort(Sort.by(Sort.Direction.DESC, "statePop"));

        Aggregation aggregation = newAggregation(
                groupByStateAndSumPop, filterStates, sortByPopDesc);

        System.out.println(x);
    }
}
