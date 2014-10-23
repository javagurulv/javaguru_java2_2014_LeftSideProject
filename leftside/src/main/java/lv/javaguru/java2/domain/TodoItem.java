package lv.javaguru.java2.domain;

import org.joda.time.DateTime;

/**
 * Created by SM on 10/23/2014.
 */
public class TodoItem {
    private long itemId;
    private long stateId;
    private String caption;
    private String description;
    private DateTime dueDate;

    public long getItemId() {
        return itemId;
    }

    ;

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public long getStateId() {
        return stateId;
    }

    public void setStateId(long stateId) {
        this.stateId = stateId;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(DateTime dueDate) {
        this.dueDate = dueDate;
    }

    public enum State {
        CREATED(1l), PROCESSING(2l), DONE(3l), CANCELLED(4l);
        public long value;

        private State(long value) {
            this.value = value;
        }
    }
}