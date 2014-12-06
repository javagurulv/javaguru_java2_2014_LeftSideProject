package lv.javaguru.java2.domain;

import javax.persistence.*;
import java.util.Calendar;

/**
 * Created by SM on 11/1/2014.
 */
@Entity
@Table(name = "todoItemComments")
public class TodoItemComment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CommentID", columnDefinition = "int(11)")
    private long commentId;
    @Column(name = "UserID", columnDefinition = "int(11)")
    private long userId;
    @Column(name = "ItemID", columnDefinition = "int(11)")
    private long itemId;
    @Column(name = "ReplyToID", columnDefinition = "int(11)", nullable = true)
    private Long ReplyToID;
    @Column(name = "Date", columnDefinition = "TIMESTAMP")
    private Calendar date;
    @Column(name = "Title")
    private String title;
    @Column(name = "Message")
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

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public Long getReplyToID() {
        return ReplyToID;
    }

    public void setReplyToID(Long replyToID) {
        ReplyToID = replyToID;
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
}