package lv.javaguru.java2.domain;

import org.joda.time.DateTime;

import javax.persistence.*;

/**
 * Created by SM on 10/23/2014.
 */

@Entity
@Table(name="todoitems")
public class TodoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ItemID")
    private long itemId;
    @Column(name="StateID")
    private long stateId;
    @Column(name="Title")
    private String title;
    @Column(name="Description")
    private String description;
    @Column(name="DueDate")
    private DateTime dueDate;

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public long getStateId() {
        return stateId;
    }

    public void setStateId(long stateId) {
        this.stateId = stateId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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