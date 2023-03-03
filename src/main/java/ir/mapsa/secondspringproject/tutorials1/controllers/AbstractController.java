package ir.mapsa.secondspringproject.tutorials1.controllers;

import ir.mapsa.secondspringproject.tutorials1.converters.BaseConverter;
import ir.mapsa.secondspringproject.tutorials1.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Transactional(readOnly = true)
public abstract class AbstractController<C extends BaseConverter<D, E>, E, D, T extends AbstractService<?, E>> {
    @Autowired
    private T service;

    @Autowired
    private C converter;

    @PostMapping()
    @Transactional
    public void add(@RequestBody D e) throws Exception {
        service.add(converter.convertDto(e));
    }

    @PutMapping()
    @Transactional
    public void update(@RequestBody D e) throws Exception {
        service.update(converter.convertDto(e));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteById(@PathVariable("id") Long id) throws ServiceException {
        service.deleteById(id);
    }

    @GetMapping("/{id}")
    public D findById(@PathVariable("id") Long id) throws Exception {
        return converter.convertEntity(service.findById(id).get());
    }

    @GetMapping()
    public List<D> getAll() throws Exception {
        return converter.convertEntity(service.getAll());
    }

    @PostMapping("/search")
    public List<D> findByExample(@RequestBody D e) {
        return converter.convertEntity(this.service.findByExample(converter.convertDto(e)));
    }
}
