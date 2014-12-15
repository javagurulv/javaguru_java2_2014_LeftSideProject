package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.TodoGroupDAO;
import lv.javaguru.java2.domain.TodoGroup;
import lv.javaguru.java2.domain.TodoItem;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SM on 12/6/2014.
 */
@Component("ORM_TodoGroupDAO")
public class TodoGroupDAOImpl extends DAOImpl<TodoGroup> implements TodoGroupDAO {

    //@Autowired
    //SessionFactory sessionFactory;

    @Override
    public List<TodoItem> getByGroupId(Long number)throws DBException {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM TodoGroup WHERE GroupID = :group_id");
        query.setParameter("group_id", number);

        return query.list();
    }

    @Override
    public List<TodoGroup> getAllGroups(){
        List<TodoGroup> todoGroups;
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM TodoGroup");
        todoGroups = query.list();

        return todoGroups;
    }
}
