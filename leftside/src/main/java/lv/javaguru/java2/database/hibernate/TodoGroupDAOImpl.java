package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.TodoGroupDAO;
import lv.javaguru.java2.domain.TodoGroup;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * Created by SM on 12/6/2014.
 */
@Component("ORM_TodoGroupDAO")
@Transactional
public class TodoGroupDAOImpl extends DAOImpl<TodoGroup> implements TodoGroupDAO {
    @Override
    public void deleteGroupFromDatabase(String groupName) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("DELETE FROM TodoGroup WHERE Name = :group_name");
        query.setParameter("group_name", groupName);
        query.executeUpdate();
    }

    @Override
    public void addGroupToDatabase(TodoGroup todoGroup) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        String name = todoGroup.getName();
        Long id = todoGroup.getGroupId();

        Query query = session.createSQLQuery("INSERT INTO todogroups(GroupID, Name) VALUES (:id,:name)");
        query.setParameter("id",id);
        query.setParameter("name",name);
        query.executeUpdate();

    }

}
