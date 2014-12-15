package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.TodoItemCommentDAO;
import lv.javaguru.java2.domain.TodoItemComment;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by SM on 12/6/2014.
 */
@Component("ORM_TodoItemCommentDAO")
public class TodoItemCommentDAOImpl extends DAOImpl<TodoItemComment> implements TodoItemCommentDAO {
    @Override
    public List<TodoItemComment> getByUserId(Long userId) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM TodoItemComment WHERE UserID = :user_id");
        query.setParameter("user_id", userId);
        return query.list();

    }

    @Override
    public List<TodoItemComment> getByItemId(Long itemId) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM TodoItemComment WHERE ItemID = :item_id");
        query.setParameter("item_id", itemId);
        return query.list();
    }
}
