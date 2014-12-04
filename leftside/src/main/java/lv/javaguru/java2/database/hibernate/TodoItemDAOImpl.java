package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.TodoItemDAO;
import lv.javaguru.java2.domain.TodoItem;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by alekmiku on 2014.12.04..
 */

@Repository(value = "TodoItemDAO")
@Transactional
public class TodoItemDAOImpl implements TodoItemDAO{

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Autowired
    TodoItemDAO todoItemDAO;

    @Override
    public void create(TodoItem todoItem) throws DBException {

    }

    @Override
    public TodoItem getById(Long id) throws DBException {
        return null;
    }

    @Override
    public void delete(Long id) throws DBException {

    }

    @Override
    public void update(TodoItem todoItem) throws DBException {

    }

    @Override
    public List<TodoItem> getAll() throws DBException {
        Session session = getCurrentSession();
        Criteria criteria = session.createCriteria(TodoItem.class);
        return criteria.list();
    }



    @Override
    public List<TodoItem> getByUserId(Long userId) throws DBException {
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
