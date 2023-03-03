package ir.mapsa.secondspringproject.tutorials1.controllers;

import ir.mapsa.secondspringproject.tutorials1.models.TeacherDto;
import ir.mapsa.secondspringproject.tutorials1.models.TeacherEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teacher")
public class TeacherController extends AbstractController<TeacherEntity, TeacherDto> {

}