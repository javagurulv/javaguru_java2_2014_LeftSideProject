package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.TodoGroupDAO;
import lv.javaguru.java2.domain.TodoGroup;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by SM on 12/6/2014.
 */
@Component("ORM_TodoGroupDAO")
public class TodoGroupDAOImpl implements TodoGroupDAO {
    @Override
    public void create(TodoGroup todoGroup) throws DBException {

    }

    @Override
    public TodoGroup getById(Long id) throws DBException {
        return null;
    }

    @Override
    public void delete(Long id) throws DBException {

    }

    @Override
    public void update(TodoGroup todoGroup) throws DBException {

    }

    @Override
    public List<TodoGroup> getAll() throws DBException {
        return null;
    }
}
