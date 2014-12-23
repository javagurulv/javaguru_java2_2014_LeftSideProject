package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.CrudDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.DomainObject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by SM on 12/6/2014.
 */
public abstract class DAOImpl<T extends DomainObject> implements CrudDAO<T> {

    @Autowired
    protected SessionFactory sessionFactory;

    private Class<T> persistentClass;

    public DAOImpl() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public void create(T obj) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        session.persist(obj);
    }

    @Override
    public T getById(Long id) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        return (T) session.get(getPersistentClass(), id);
    }

    @Override
    public void delete(T obj) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        session.delete(obj);
    }

    @Override
    public void update(T obj) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        session.update(obj);
    }

    @Override
    public T merge(T obj) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        return (T) session.merge(obj);
    }

    @Override
    public List getAll() throws DBException {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(getPersistentClass()).list();
    }

    public Class<T> getPersistentClass() {
        return persistentClass;
    }
}
