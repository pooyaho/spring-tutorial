package ir.mapsa.secondspringproject.tutorials1.converters;

import ir.mapsa.secondspringproject.tutorials1.models.Student;
import ir.mapsa.secondspringproject.tutorials1.models.StudentDto;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface StudentConverter extends BaseConverter<StudentDto, Student> {

}
