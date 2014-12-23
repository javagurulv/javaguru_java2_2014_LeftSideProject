package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.DomainObject;

import java.util.List;

/**
 * Created by SM on 12/6/2014.
 */
public interface CrudDAO<T extends DomainObject> {

    void create(T obj) throws DBException;

    T getById(Long id) throws DBException;

    void delete(T obj) throws DBException;

    void update(T file) throws DBException;

    T merge(T obj) throws DBException;

    List<T> getAll() throws DBException;
}
