package lv.javaguru.java2.web.mvc.todoItemServlet;

import lv.javaguru.java2.domain.TodoItem;
import java.util.List;

/**
 * Created by alekmiku on 2014.11.11..
 */
public class TodoItemModel {
    private List<TodoItem> todoItem;

    public TodoItemModel(List<TodoItem> todoItem){
        this.todoItem = todoItem;
    }

    public TodoItem getTodoItem(int i) {
        return todoItem.get(i);
    }

    public int getTodoItemSize(){
        return this.todoItem.size();
    }
}
