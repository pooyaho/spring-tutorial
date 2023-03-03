package ir.mapsa.secondspringproject.tutorials1.repositories;

import ir.mapsa.secondspringproject.tutorials1.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findOneByNameLike(String name);
}
