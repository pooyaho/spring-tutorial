package ir.mapsa.secondspringproject.tutorials1.converters;

import ir.mapsa.secondspringproject.tutorials1.models.TeacherDto;
import ir.mapsa.secondspringproject.tutorials1.models.TeacherEntity;
import org.springframework.stereotype.Service;

@Service
public class TeacherConverter implements BaseConverter<TeacherDto, TeacherEntity> {
    @Override
    public TeacherEntity convertDto(TeacherDto d) {
        TeacherEntity e = new TeacherEntity();
        e.setFamily(d.getFamily());
        e.setName(d.getName());
        e.setGender(d.getGender());
        e.setId(d.getId());
        return null;
    }

    @Override
    public TeacherDto convertEntity(TeacherEntity teacherEntity) {
        return null;
    }
}
