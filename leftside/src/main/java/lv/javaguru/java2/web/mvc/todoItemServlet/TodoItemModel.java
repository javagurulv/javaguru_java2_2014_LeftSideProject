package lv.javaguru.java2.web.mvc.todoItemServlet;

import lv.javaguru.java2.domain.TodoItem;

/**
 * Created by alekmiku on 2014.11.11..
 */
public class TodoItemModel {
    private TodoItem todoItem;

    public TodoItemModel(TodoItem todoItem){
        this.todoItem = todoItem;
    }

    public TodoItem getTodoItem() {
        return todoItem;
    }
}
