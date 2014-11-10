package lv.javaguru.java2.web.mvc.todoItemCommentServlet;

import lv.javaguru.java2.domain.TodoItemComment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SM on 11/9/2014.
 */
public class TodoItemCommentsTree {
    public TodoItemComment comment;
    public List<TodoItemCommentsTree> childs;

    TodoItemCommentsTree(TodoItemComment comment) {
        this.comment = comment;
        childs = new ArrayList<TodoItemCommentsTree>();
    }

    public TodoItemComment getComment() {
        return comment;
    }

    public List<TodoItemCommentsTree> getChilds() {
        return childs;
    }
}