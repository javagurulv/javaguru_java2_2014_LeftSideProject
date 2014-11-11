package lv.javaguru.java2.servlets.mvc;

import lv.javaguru.java2.domain.TodoItem;

/**
 * Created by alekmiku on 2014.11.10..
 */
public class TodoItemModel {
    public TodoItem getTodoItem() {
        return todoItem;
    }

    private TodoItem todoItem;

    public TodoItemModel(TodoItem todoItem) {
        this.todoItem = todoItem;
    }



}
