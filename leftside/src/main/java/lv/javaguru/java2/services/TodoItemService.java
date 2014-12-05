package lv.javaguru.java2.services;

import lv.javaguru.java2.domain.TodoItem;

import java.util.List;

/**
 * Created by alekmiku on 2014.12.05..
 */
public interface TodoItemService {

    public void deleteTodoItem(long id);

    public List<TodoItem> getAll();
}
