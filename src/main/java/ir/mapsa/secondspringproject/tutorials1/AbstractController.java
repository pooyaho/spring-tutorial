package ir.mapsa.secondspringproject.tutorials1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;


public abstract class AbstractController<E, T extends JpaRepository<E,Long>> {
    @Autowired
    private T repository;

    @PostMapping()
    public void add(@RequestBody E e) throws Exception {
        repository.save(e);
        throw new RuntimeException();
    }

    @PutMapping()
    public void update(@RequestBody E e) throws Exception {
        repository.save(e);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) throws ServiceException {
        repository.deleteById(id);

    }

    @GetMapping("/{id}")
    public E findById(@PathVariable("id") Long id) throws Exception {
        return repository.findById(id).get();
    }

    @GetMapping()
    public List<E> getAll() throws Exception {
        return repository.findAll();
    }


}
