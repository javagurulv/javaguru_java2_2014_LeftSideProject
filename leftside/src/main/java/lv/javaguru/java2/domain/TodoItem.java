package lv.javaguru.java2.domain;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by SM on 10/23/2014.
 */

@Entity
@Table(name = "todoitems")
public class TodoItem implements DomainObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ItemID", columnDefinition = "int(11)")
    private long itemId;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "StateID")
    private State stateId;

    @Column(name = "Title", length = 100)
    private String title;

    @Column(name = "Description", length = 2000)
    private String description;

    @Column(name = "DueDate", columnDefinition = "date")
    private Date dueDate;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinTable(name = "todoItemsToGroups",
            joinColumns = {@JoinColumn(name = "ItemID", referencedColumnName = "ItemID")},
            inverseJoinColumns = {@JoinColumn(name = "GroupID", referencedColumnName = "GroupID")})
    private TodoGroup todoGroup;

    @OneToMany(mappedBy = "todoItem", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<TodoItemComment> todoItemComments;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinTable(name = "todoitemstousers",
            joinColumns = {@JoinColumn(name = "ItemID", referencedColumnName = "ItemID")},
            inverseJoinColumns = {@JoinColumn(name = "UserID", referencedColumnName = "UserID")})
    private User user;

    @OneToMany(mappedBy = "ToDoItemID")
    private List<File> files;

    public void setTodoGroup(TodoGroup todoGroup) {
        this.todoGroup = todoGroup;
    }

    public TodoGroup getTodoGroup() {
        return todoGroup;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public State getStateId() {
        return stateId;
    }

    public void setStateId(State stateId) {
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

    @Override
    public void setId(Long id) {
        setItemId(id);
    }

    public List<TodoItemComment> getTodoItemComments() {
        return todoItemComments;
    }

    public void setTodoItemComments(List<TodoItemComment> todoItemComments) {
        this.todoItemComments = todoItemComments;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles (List<File> files) {
        this.files = files;
    }

//    public enum State {
//        CREATED(1l), PROCESSING(2l), DONE(3l), CANCELLED(4l);
//        public long value;

//        private State(long value) {
//            this.value = value;
//        }
//    }
}