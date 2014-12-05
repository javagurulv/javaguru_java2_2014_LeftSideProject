package lv.javaguru.java2.domain;


import javax.persistence.*;
import java.util.Date;

/**
 * Created by SM on 10/23/2014.
 */

@Entity
@Table(name="todoitems")
public class TodoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ItemID", columnDefinition = "int(11)")
    private long itemId;

    @Column(name="StateID", columnDefinition = "int(2)")
    private long stateId;

    @Column(name="Title", columnDefinition = "varchar(100)")
    private String title;

    @Column(name="Description", columnDefinition = "varchar(2000)")
    private String description;

    @Column(name="DueDate", columnDefinition="datetime")
    private Date dueDate;

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

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
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