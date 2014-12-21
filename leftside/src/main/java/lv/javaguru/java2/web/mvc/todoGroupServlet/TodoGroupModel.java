package lv.javaguru.java2.web.mvc.todoGroupServlet;

import lv.javaguru.java2.domain.TodoGroup;

import java.util.List;

/**
 * Created by Emils on 2014.11.15..
 */
public class TodoGroupModel {

    private List<TodoGroup> todoGroups;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TodoGroupModel(List<TodoGroup> todoGroups) {
        this.todoGroups = todoGroups;
    }

    public List<TodoGroup> getTodoGroups() {
        return todoGroups;
    }

    public void setTodoGroups(List<TodoGroup> todoGroups) {
        this.todoGroups = todoGroups;
    }

}





