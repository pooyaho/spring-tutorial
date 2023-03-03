package ir.mapsa.secondspringproject.tutorials1.converters;

import ir.mapsa.secondspringproject.tutorials1.models.Course;
import ir.mapsa.secondspringproject.tutorials1.models.CourseDto;
import org.springframework.stereotype.Service;

@Service
public class CourseConverter implements BaseConverter<CourseDto, Course> {

    @Override
    public Course convertDto(CourseDto courseDto) {
        return null;
    }

    @Override
    public CourseDto convertEntity(Course course) {
        return null;
    }
}
