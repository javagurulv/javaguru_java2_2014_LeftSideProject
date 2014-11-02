package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.TodoItemComment;

import java.util.List;

/**
 * Created by SM on 11/1/2014.
 */
public interface TodoItemCommentDAO {

    void create(TodoItemComment itemComment) throws DBException;

    TodoItemComment getById(Long id) throws DBException;

    void delete(Long id) throws DBException;

    void update(TodoItemComment itemComment) throws DBException;

    List<TodoItemComment> getAll() throws DBException;

    List<TodoItemComment> getByUserId(Long userId) throws DBException;

    List<TodoItemComment> getByItemId(Long itemId) throws DBException;
}
