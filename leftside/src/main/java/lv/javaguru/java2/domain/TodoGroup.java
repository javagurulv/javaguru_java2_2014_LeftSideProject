package lv.javaguru.java2.domain;

import lv.javaguru.java2.database.TodoGroupDAO;
import lv.javaguru.java2.database.hibernate.TodoGroupDAOImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SM on 10/23/2014.
 */
@Entity
@Table(name = "todoGroups")
public class TodoGroup implements DomainObject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "GroupID", columnDefinition = "int(11)")
    private long groupId;
    @Column(name = "Name", length = 40)
    private String name;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "todoGroup")
    private List<TodoItem> todoItems;

    public void setTodoItems(List<TodoItem> todoItems){
        this.todoItems = todoItems;
    }

    public List<TodoItem> getTodoItems(){
        return todoItems;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setId(Long id) {
        setGroupId(id);
    }
}
