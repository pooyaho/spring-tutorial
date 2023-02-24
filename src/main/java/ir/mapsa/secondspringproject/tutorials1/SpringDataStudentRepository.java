package ir.mapsa.secondspringproject.tutorials1;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataStudentRepository extends JpaRepository<Student,Long> {
    Student findOneByNameLike(String name);
}
