package ir.mapsa.secondspringproject.tutorials1.converters;

import ir.mapsa.secondspringproject.tutorials1.models.TeacherDto;
import ir.mapsa.secondspringproject.tutorials1.models.TeacherEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeacherConverter extends BaseConverter<TeacherDto, TeacherEntity> {

}
