package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.TodoItemDAO;
import lv.javaguru.java2.domain.TodoItem;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by alekmiku on 2014.12.04..
 */
@Component("ORM_TodoItemDAO")
public class TodoItemDAOImpl implements TodoItemDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(TodoItem todoItem) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        session.persist(todoItem);
    }

    @Override
    public TodoItem getById(Long id) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        return (TodoItem) session.get(TodoItem.class, id);
    }

    @Override
    public void delete(Long id) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        TodoItem todoItem = new TodoItem();
        todoItem.setItemId(id);
        session.delete(todoItem);
    }

    @Override
    public void update(TodoItem todoItem) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        session.update(todoItem);
    }

    @Override
    public List<TodoItem> getAll() throws DBException {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(TodoItem.class);
        return criteria.list();
    }


    @Override
    public List<TodoItem> getByUserId(Long userId) throws DBException {
//        Session session = getCurrentSession();
//        Criteria criteria = session.createCriteria(TodoItem.class);
        return null;
    }


    @Override
    public List<TodoItem> getByTodoUserAndGroupId(Long userId, Long groupId) throws DBException {
        return null;
    }

    @Override
    public List<TodoItem> getByGroupId(Long groupId) throws DBException {
        return null;
    }

    @Override
    public void setTodoGroup(Long todoItemId, Long todoGroupId) throws DBException {

    }

    @Override
    public void setAuthor(Long todoItemId, Long userId) throws DBException {

    }
}
