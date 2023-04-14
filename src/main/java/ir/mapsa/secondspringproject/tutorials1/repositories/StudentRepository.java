package ir.mapsa.secondspringproject.tutorials1.repositories;

import ir.mapsa.secondspringproject.tutorials1.models.Student;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface StudentRepository extends BaseRepository<Student, Long> {
    Student findOneByNameLike(String name);

    CompletableFuture<List<Student>> findByOrderByNameAsc();

}
