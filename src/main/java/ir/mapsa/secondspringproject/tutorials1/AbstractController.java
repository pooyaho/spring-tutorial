package ir.mapsa.secondspringproject.tutorials1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Transactional(readOnly = true)
public abstract class AbstractController<E, T extends JpaRepository<E, Long>> {
    @Autowired
    private T repository;

    @PostMapping()
    @Transactional
    public void add(@RequestBody E e) throws Exception {
        repository.save(e);
    }

    @PutMapping()
    @Transactional
    public void update(@RequestBody E e) throws Exception {
        repository.save(e);
    }

    @DeleteMapping("/{id}")
    @Transactional
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
