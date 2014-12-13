package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.TodoGroup;
import lv.javaguru.java2.domain.TodoItem;

import java.util.List;

/**
 * Created by SM on 10/23/2014.
 */
public interface TodoGroupDAO extends CrudDAO<TodoGroup> {

    List<TodoItem> getByGroupId(Long number)throws DBException;

}
