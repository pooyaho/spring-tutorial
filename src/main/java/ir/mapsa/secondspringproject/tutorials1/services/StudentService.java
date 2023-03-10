package ir.mapsa.secondspringproject.tutorials1.services;

import ir.mapsa.secondspringproject.tutorials1.controllers.AbstractService;
import ir.mapsa.secondspringproject.tutorials1.models.Course;
import ir.mapsa.secondspringproject.tutorials1.models.QCourse;
import ir.mapsa.secondspringproject.tutorials1.models.QStudent;
import ir.mapsa.secondspringproject.tutorials1.models.Student;
import ir.mapsa.secondspringproject.tutorials1.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService extends AbstractService<StudentRepository, Student> {
    public List<Student> findEvenPassedCourseStudents() {
        QStudent student = QStudent.student;
        QCourse qc = QCourse.course;
        List<Student> students = new ArrayList<>();

        repository.findAll(student.passedCourse.mod(2).eq(0)
                        .and(student.name.startsWith("p")).and(student.courses.contains(Course.builder().name("Math").build())))
                .forEach(students::add);
        return students;
    }

}
