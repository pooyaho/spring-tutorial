package ir.mapsa.secondspringproject.tutorials1.services;

import ir.mapsa.secondspringproject.tutorials1.models.Student;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CachedService {

    @Cacheable("Sample")
    public String doSomething() {
        try {
            Thread.sleep(10000);
            return "Hello world!";
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @CachePut(cacheNames = "Sample1", condition = "#student.studentId=='a'", key = "#student.studentId")
    public String doSomething(Student student) {
        try {
            Thread.sleep(10000);
            return "Hello world!";
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @CacheEvict(cacheNames = "Sample1", key = "#student.studentId")
    public void removeCache(Student student) {
        System.out.println("Student removed!");
    }
}
