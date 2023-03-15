package ir.mapsa.secondspringproject.tutorials1.services;

import ir.mapsa.secondspringproject.tutorials1.controllers.AbstractService;
import ir.mapsa.secondspringproject.tutorials1.models.TeacherEntity;
import ir.mapsa.secondspringproject.tutorials1.repositories.TeacherRepository;
import org.springframework.stereotype.Service;

@Service
public class TeacherService extends AbstractService<TeacherRepository, TeacherEntity> {

}
