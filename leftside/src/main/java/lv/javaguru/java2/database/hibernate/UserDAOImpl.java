package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by SM on 12/6/2014.
 */
@Component("ORM_UserDAO")
public class UserDAOImpl implements UserDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(User user) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        session.persist(user);
    }

    @Override
    public User getById(Long id) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        return (User) session.get(User.class, id);
    }

    @Override
    public User getByLogin(String login) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM User WHERE Login = :login");
        query.setParameter("login", login);
        return (User) query.uniqueResult();
    }

    @Override
    public void delete(Long id) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        User user = new User();
        user.setUserId(id);
        session.delete(user);
    }

    @Override
    public void update(User user) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
    }

    @Override
    public List<User> getAll() throws DBException {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(User.class).list();
    }
}
