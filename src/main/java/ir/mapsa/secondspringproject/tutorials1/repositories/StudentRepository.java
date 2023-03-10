package ir.mapsa.secondspringproject.tutorials1.repositories;

import ir.mapsa.secondspringproject.tutorials1.models.Student;

public interface StudentRepository extends BaseRepository<Student, Long> {
    Student findOneByNameLike(String name);
}
