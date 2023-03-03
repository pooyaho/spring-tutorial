package ir.mapsa.secondspringproject.tutorials1.controllers;

import ir.mapsa.secondspringproject.tutorials1.models.TeacherEntity;
import ir.mapsa.secondspringproject.tutorials1.repositories.TeacherRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController extends AbstractController<TeacherEntity, TeacherRepository> {
    @Override
    public List<TeacherEntity> findByExample(TeacherEntity teacherEntity) {
        return super.findByExample(teacherEntity);
    }
}
