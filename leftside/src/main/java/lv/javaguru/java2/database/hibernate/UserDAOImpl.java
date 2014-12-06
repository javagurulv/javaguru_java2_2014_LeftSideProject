package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by SM on 12/6/2014.
 */
@Component("ORM_UserDAO")
public class UserDAOImpl implements UserDAO {
    @Override
    public void create(User user) throws DBException {

    }

    @Override
    public User getById(Long id) throws DBException {
        return null;
    }

    @Override
    public User getByLogin(String login) throws DBException {
        return null;
    }

    @Override
    public void delete(Long id) throws DBException {

    }

    @Override
    public void update(User user) throws DBException {

    }

    @Override
    public List<User> getAll() throws DBException {
        return null;
    }
}
