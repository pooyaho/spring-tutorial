package ir.mapsa.secondspringproject.tutorials1;

import jdk.jshell.spi.ExecutionControlProvider;

import java.util.List;

public interface BaseRepository<T> {

    void add(T entity) throws ServiceException;

    void update(T entity)throws ServiceException;

    void removeById(Long id)throws ServiceException;

    T findById(Long id)throws ServiceException;

    List<T> getAll() throws ServiceException;
}
