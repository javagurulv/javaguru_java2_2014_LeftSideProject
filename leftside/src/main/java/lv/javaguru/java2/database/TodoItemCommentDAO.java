package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.TodoItemComment;

import java.util.List;

/**
 * Created by SM on 11/1/2014.
 */
public interface TodoItemCommentDAO extends CrudDAO<TodoItemComment> {

    List<TodoItemComment> getByUserId(Long userId) throws DBException;

    List<TodoItemComment> getByItemId(Long itemId) throws DBException;
}
