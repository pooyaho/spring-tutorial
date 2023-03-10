package ir.mapsa.secondspringproject.tutorials1.converters;

import ir.mapsa.secondspringproject.tutorials1.models.Course;
import ir.mapsa.secondspringproject.tutorials1.models.CourseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseConverter extends BaseConverter<CourseDto, Course> {
    
}
