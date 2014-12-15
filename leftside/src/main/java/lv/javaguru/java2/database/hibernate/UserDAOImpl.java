package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

/**
 * Created by SM on 12/6/2014.
 */
@Component("ORM_UserDAO")
public class UserDAOImpl extends DAOImpl<User> implements UserDAO {

    @Override
    public User getByLogin(String login) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM User WHERE Login = :login");
        query.setParameter("login", login);
        return (User) query.uniqueResult();
    }

}
