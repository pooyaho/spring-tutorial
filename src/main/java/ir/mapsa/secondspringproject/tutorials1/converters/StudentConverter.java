package ir.mapsa.secondspringproject.tutorials1.converters;

import ir.mapsa.secondspringproject.tutorials1.models.Student;
import ir.mapsa.secondspringproject.tutorials1.models.StudentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentConverter implements BaseConverter<StudentDto, Student> {

    @Autowired
    private CourseConverter courseConverter;

    @Override
    public Student convertDto(StudentDto studentDto) {
        Student e = new Student();
        e.setCourses(courseConverter.convertDto(studentDto.getCourses()));
        return e;
    }

    @Override
    public StudentDto convertEntity(Student student) {
        return null;
    }
}
