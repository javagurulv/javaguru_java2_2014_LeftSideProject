package lv.javaguru.java2.domain;

import org.joda.time.DateTime;

/**
 * Created by SM on 11/1/2014.
 */
public class TodoItemComment {
    private long commentId;
    private long userId;
    private long itemId;
    private Long ReplyToID;
    private DateTime date;
    private String title;
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

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
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