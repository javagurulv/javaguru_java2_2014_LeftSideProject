package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.User;

/**
 * Created by Viktor on 01/07/2014.
 */
public interface UserDAO extends CrudDAO<User> {
    User getByLogin(String login) throws DBException;
}
