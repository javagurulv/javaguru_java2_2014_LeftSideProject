package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.TodoItemDAO;
import lv.javaguru.java2.domain.TodoItem;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by alekmiku on 2014.12.04..
 */
@Component("ORM_TodoItemDAO")
public class TodoItemDAOImpl extends DAOImpl<TodoItem> implements TodoItemDAO {
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
