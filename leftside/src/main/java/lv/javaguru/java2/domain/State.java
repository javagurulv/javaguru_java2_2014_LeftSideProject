package lv.javaguru.java2.domain;

import javax.persistence.*;

/**
 * Created by alekmiku on 2014.12.15..
 */
@Entity
@Table(name = "todostates")
public class State implements DomainObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "StateID", columnDefinition = "int(11)")
    private long id;
    @Column(name = "State", length = 20)
    private String state;

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
