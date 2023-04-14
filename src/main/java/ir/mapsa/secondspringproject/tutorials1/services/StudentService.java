package ir.mapsa.secondspringproject.tutorials1.services;

import ir.mapsa.secondspringproject.tutorials1.controllers.AbstractService;
import ir.mapsa.secondspringproject.tutorials1.models.Student;
import ir.mapsa.secondspringproject.tutorials1.repositories.StudentRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.StopWatch;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class StudentService extends AbstractService<StudentRepository, Student> {
//    public List<Student> findEvenPassedCourseStudents() {
//        QStudent student = QStudent.student;
//        QCourse qc = QCourse.course;
//        List<Student> students = new ArrayList<>();
//
//        repository.findAll(student.passedCourse.mod(2).eq(0)
//                        .and(student.name.startsWith("p")).and(student.courses.contains(Course.builder().name("Math").build())))
//                .forEach(students::add);
//        return students;
//    }

    @Async
    public CompletableFuture<List<Student>> sortedStudents() {
        return this.repository.findAll();
    }

//    public List<Student> findMoreThan20UnitPassed(){
//
//    }

    @PostConstruct
    public void init() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        CompletableFuture<List<Student>> byOrderByNameAsc = this.repository.findByOrderByNameAsc();

        stopWatch.stop();
        byOrderByNameAsc.thenAccept(i ->
                System.out.println(i)
        );


        System.out.println(stopWatch.prettyPrint());
//        System.out.println(byOrderByNameAsc);
    }
}
