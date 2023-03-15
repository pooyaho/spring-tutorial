package ir.mapsa.secondspringproject.tutorials1.converters;

import ir.mapsa.secondspringproject.tutorials1.models.Course;
import ir.mapsa.secondspringproject.tutorials1.models.CourseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CourseConverter implements BaseConverter<CourseDto, Course> {

}
