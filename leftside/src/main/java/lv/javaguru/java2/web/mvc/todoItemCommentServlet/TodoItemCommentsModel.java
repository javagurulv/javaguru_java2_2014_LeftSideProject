package lv.javaguru.java2.web.mvc.todoItemCommentServlet;

import lv.javaguru.java2.domain.TodoItem;

/**
 * Created by SM on 11/9/2014.
 */
public class TodoItemCommentsModel {
    private TodoItem todoItem;
    private TodoItemCommentsTree commentTree;
    private Long replyCommentId;

    public TodoItemCommentsModel(TodoItem todoItem, TodoItemCommentsTree commentTree, Long replyCommentId) {
        this.todoItem = todoItem;
        this.commentTree = commentTree;
        this.replyCommentId = replyCommentId;
    }

    public TodoItem getTodoItem() {
        return todoItem;
    }

    public TodoItemCommentsTree getCommentTree() {
        return commentTree;
    }

    public Long getReplyCommentId() {
        return replyCommentId;
    }
}
