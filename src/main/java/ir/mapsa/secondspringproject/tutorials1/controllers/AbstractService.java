package ir.mapsa.secondspringproject.tutorials1.controllers;

import ir.mapsa.secondspringproject.tutorials1.exceptions.ServiceException;
import ir.mapsa.secondspringproject.tutorials1.repositories.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import java.util.List;
import java.util.Optional;

public abstract class AbstractService<R extends BaseRepository<E, Long>, E> {
    @Autowired
    protected R repository;

    public void add(E e) throws Exception {
        repository.save(e);
    }

    public void update(E e) throws Exception {
        repository.save(e);
    }


    public void deleteById(Long id) throws ServiceException {
        repository.deleteById(id);
    }

    public Optional<E> findById(Long id) throws Exception {
        return repository.findById(id);
    }

    public List<E> getAll() throws Exception {
        return repository.findAll();
    }

    public List<E> findByExample(E e) {
        return repository.findAll(Example.of(e));
    }
}