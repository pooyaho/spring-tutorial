package ir.mapsa.secondspringproject.tutorials1;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


public abstract class AbstractController<E,T extends BaseRepository<E>> {
    @Autowired
    private T repository;

    @PostMapping()
    public void add(@RequestBody E e) throws Exception {
        repository.add(e);
    }

    @PutMapping()
    public void update(@RequestBody E e) throws Exception {
        repository.update(e);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id, HttpServletResponse response) throws Exception {
        try {
            repository.removeById(id);
        } catch (Exception x) {
            response.setStatus(400);
            response.getWriter().println(x.getClass().getSimpleName());
        }
    }

    @GetMapping("/{id}")
    public E findById(@PathVariable("id") Long id) throws Exception {
        return repository.findById(id);
    }
    @GetMapping()
    public List<E> getAll() throws Exception {
        return repository.getAll();
    }


}
