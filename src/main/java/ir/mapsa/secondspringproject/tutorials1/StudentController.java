package ir.mapsa.secondspringproject.tutorials1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController  extends AbstractController<Student,SpringDataStudentRepository>{

}
