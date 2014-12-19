package lv.javaguru.java2.web.mvc.todoGroupServlet;

import lv.javaguru.java2.domain.TodoGroup;

import java.util.List;

/**
 * Created by Emils on 2014.11.15..
 */
public class TodoGroupModel {

    private List<TodoGroup> todoGroups;

    public TodoGroupModel(List<TodoGroup> todoGroups) {
        this.todoGroups = todoGroups;
    }

    public TodoGroup getTodoGroup(int i) {
        return todoGroups.get(i);
    }

    public int getTodoGroupAmount() {
        return this.todoGroups.size();
    }

    public List<TodoGroup> getAllTodoGroups() { return todoGroups; }

    public TodoGroup getTodoGroupByName(String name){
        TodoGroup group = null;
        for(int i = 0; i < todoGroups.size(); i++){
            String groupName = todoGroups.get(i).getName().toLowerCase();
            if(name != null && groupName.equals(name.toLowerCase())){
                group = todoGroups.get(i);
            }
        }

        return group;
    }
}



