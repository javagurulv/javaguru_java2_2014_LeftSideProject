package lv.javaguru.java2.domain;

import javax.persistence.*;
import java.util.Calendar;

/**
 * Created by SM on 11/1/2014.
 */
@Entity
@Table(name = "todoItemComments")
public class TodoItemComment implements DomainObject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CommentID", columnDefinition = "int(11)")
    private long commentId;
    @Column(name = "UserID", columnDefinition = "int(11)")
    private long userId;
    @ManyToOne
    @JoinColumn(name = "ItemID")
    private TodoItem todoItem;
    @ManyToOne
    @JoinColumn(name = "ReplyToID", nullable = true)
    private TodoItemComment replyToItemComment;
    @Column(name = "Date", columnDefinition = "TIMESTAMP")
    private Calendar date;
    @Column(name = "Title", length = 100)
    private String title;
    @Column(name = "Message", length = 1000)
    private String message;

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public TodoItemComment getReplyToItemComment() {
        return replyToItemComment;
    }

    public void setReplyToItemComment(TodoItemComment replyToItemComment) {
        this.replyToItemComment = replyToItemComment;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void setId(Long id) {
        setCommentId(id);
    }

    public TodoItem getTodoItem() {
        return todoItem;
    }

    public void setTodoItem(TodoItem todoItem) {
        this.todoItem = todoItem;
    }

}