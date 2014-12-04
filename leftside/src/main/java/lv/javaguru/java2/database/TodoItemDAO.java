package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.TodoItem;

import java.util.List;

/**
 * Created by SM on 10/23/2014.
 */

public interface TodoItemDAO {

    void create(TodoItem todoItem) throws DBException;

    TodoItem getById(Long id) throws DBException;

    void delete(Long id) throws DBException;

    void update(TodoItem todoItem) throws DBException;

    List<TodoItem> getAll() throws DBException;

    List<TodoItem> getByUserId(Long userId) throws DBException;

    List<TodoItem> getByTodoUserAndGroupId(Long userId, Long groupId) throws DBException;

    List<TodoItem> getByGroupId(Long groupId) throws DBException;

    void setTodoGroup(Long todoItemId, Long todoGroupId) throws DBException;

    void setAuthor(Long todoItemId, Long userId) throws DBException;
}
