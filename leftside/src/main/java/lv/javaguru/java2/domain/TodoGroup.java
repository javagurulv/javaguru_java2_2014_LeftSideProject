package lv.javaguru.java2.domain;

import javax.persistence.*;

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
