package lv.javaguru.java2.services.impl;

import lv.javaguru.java2.database.TodoItemDAO;
import lv.javaguru.java2.domain.TodoItem;
import lv.javaguru.java2.services.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by alekmiku on 2014.12.05..
 */
@Component
public class TodoItemServiceImpl implements TodoItemService {

    @Autowired
    private TodoItemDAO todoItemDAO;

    @Override
    @Transactional
    public void deleteTodoItem(long id) {
        todoItemDAO.delete(id);
    }

    @Override
    @Transactional
    public List<TodoItem> getAll() {
        return todoItemDAO.getAll();
    }
}
