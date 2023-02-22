package ir.mapsa.secondspringproject.tutorials1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentRepository repository;

    @PostMapping("/student")
    public void add(@RequestBody Student student) throws Exception {
        repository.add(student);
    }

    @PutMapping("/student")
    public void update(@RequestBody Student student) throws Exception {
        repository.update(student);
    }

    @DeleteMapping("/student/{id}")
    public void deleteById(@PathVariable("id") Long id) throws Exception {
        repository.removeById(id);
    }

    @GetMapping("/student/{id}")
    public Student findById(@PathVariable("id") Long id) throws Exception {
        return repository.findById(id);
    }
    @GetMapping("/student")
    public List<Student> getAll() throws Exception {
        return repository.getAll();
    }


}
