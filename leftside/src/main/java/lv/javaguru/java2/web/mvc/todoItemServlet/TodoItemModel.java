package lv.javaguru.java2.web.mvc.todoItemServlet;

import lv.javaguru.java2.domain.TodoItem;

import java.util.List;

/**
 * Created by alekmiku on 2014.11.11..
 */
public class TodoItemModel {
    private List<TodoItem> todoItemList;
    private TodoItem todoItem;

    public TodoItemModel(List<TodoItem> todoItemList) {
        this.todoItemList = todoItemList;
    }

    public TodoItemModel(TodoItem todoItem) {
        this.todoItem = todoItem;
    }

    public TodoItem getTodoItem(int i) {
        return todoItemList.get(i);
    }

    public List<TodoItem> getTodoItemList() {
        return todoItemList;
    }

    public int getTodoItemSize() {
        return this.todoItemList.size();
    }
}
