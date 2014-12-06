package lv.javaguru.java2.domain;

import javax.persistence.*;

/**
 * Created by Viktor on 01/07/2014.
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "UserID", columnDefinition = "int(11)")
    private long userId;
    @Column(name = "Login", length = 32)
    private String login;
    @Column(name = "Password", length = 32)
    private String password;
    @Column(name = "FirstName", length = 32)
    private String firstName;
    @Column(name = "Lastname", length = 32)
    private String lastName;
    @Column(name = "Email", length = 100)
    private String email;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
