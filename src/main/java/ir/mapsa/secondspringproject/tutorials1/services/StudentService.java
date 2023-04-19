package ir.mapsa.secondspringproject.tutorials1.services;

import ir.mapsa.secondspringproject.tutorials1.controllers.AbstractService;
import ir.mapsa.secondspringproject.tutorials1.models.Student;
import ir.mapsa.secondspringproject.tutorials1.repositories.StudentRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.concurrent.ExecutionException;

@Service
public class StudentService extends AbstractService<StudentRepository, Student> {

    @Autowired
    private AsyncService asyncService;
    @Autowired
    private CachedService cachedService;
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


//    public List<Student> findMoreThan20UnitPassed(){
//
//    }

    @PostConstruct
    public void init2() {

    }

    //    @PostConstruct
    public void init() throws ExecutionException, InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        asyncService.doSomething(2).whenComplete((s, throwable) -> {
            if (throwable == null) {
                System.out.println(s);

            } else {
                throwable.printStackTrace();
            }
        });
//        CompletableFuture.allOf(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17).stream().map(i -> {
//            return CompletableFuture.supplyAsync(() -> {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                return i * 2;
//            });
//        }).toArray(CompletableFuture[]::new))
//                .
//        asyncService.doSomething().thenApply(i -> {
//            return i + " and apply called";
//        }).thenAccept(i -> System.out.println(i));
//        this.repository.findByOrderByNameAsc()
//                .thenAccept(i ->
//                System.out.println(i)
//        );

        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
//        System.out.println(byOrderByNameAsc);
    }

    @Async
    public void doSomething() {
        try {
            Thread.sleep(10000);
            System.out.println("Task executed");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
