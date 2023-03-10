package ir.mapsa.secondspringproject.tutorials1.controllers;

import ir.mapsa.secondspringproject.tutorials1.converters.BaseConverter;
import ir.mapsa.secondspringproject.tutorials1.exceptions.ServiceException;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Transactional(readOnly = true)
public abstract class AbstractController<E, D> {
    protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractController.class);

    @Autowired
    private AbstractService<? extends JpaRepository<E, Long>, E> service;

    @Autowired
    private BaseConverter<D, E> converter;

    @PostMapping()
    @Transactional
    public void add(@Valid @RequestBody D e) throws Exception {
//        LOGGER.debug("Add method called!");
//        LOGGER.info("Add arguments is :" + e);
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
