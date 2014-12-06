package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.TodoGroupDAO;
import lv.javaguru.java2.domain.TodoGroup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by SM on 12/6/2014.
 */
@Component("ORM_TodoGroupDAO")
public class TodoGroupDAOImpl implements TodoGroupDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(TodoGroup todoGroup) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        session.persist(todoGroup);
    }

    @Override
    public TodoGroup getById(Long id) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        return (TodoGroup) session.get(TodoGroup.class, id);
    }

    @Override
    public void delete(Long id) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        TodoGroup todoGroup = new TodoGroup();
        todoGroup.setGroupId(id);
        session.delete(todoGroup);
    }

    @Override
    public void update(TodoGroup todoGroup) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        session.update(todoGroup);
    }

    @Override
    public List<TodoGroup> getAll() throws DBException {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(TodoGroup.class).list();
    }
}
