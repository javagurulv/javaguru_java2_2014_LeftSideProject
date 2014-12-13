package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.TodoGroupDAO;
import lv.javaguru.java2.domain.TodoGroup;
import lv.javaguru.java2.domain.TodoItem;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by SM on 12/6/2014.
 */
@Component("ORM_TodoGroupDAO")
public class TodoGroupDAOImpl extends DAOImpl<TodoGroup> implements TodoGroupDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<TodoItem> getByGroupId(Long number)throws DBException {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM TodoGroup WHERE GroupID = :group_id");
        query.setParameter("group_id", number);
        return query.list();
    }
}
