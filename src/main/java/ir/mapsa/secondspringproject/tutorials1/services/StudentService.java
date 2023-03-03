package ir.mapsa.secondspringproject.tutorials1.services;

import ir.mapsa.secondspringproject.tutorials1.controllers.AbstractService;
import ir.mapsa.secondspringproject.tutorials1.models.Student;
import ir.mapsa.secondspringproject.tutorials1.repositories.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService extends AbstractService<StudentRepository, Student> {
}
