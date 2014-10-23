package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.TodoGroup;

import java.util.List;

/**
 * Created by SM on 10/23/2014.
 */
public interface TodoGroupDAO {

    void create(TodoGroup todoGroup) throws DBException;

    TodoGroup getById(Long id) throws DBException;

    void delete(Long id) throws DBException;

    void update(TodoGroup todoGroup) throws DBException;

    List<TodoGroup> getAll() throws DBException;

}
