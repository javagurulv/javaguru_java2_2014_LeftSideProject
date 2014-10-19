package lv.javaguru.java2.database.jdbc;

import static org.junit.Assert.*;

import java.util.List;

import lv.javaguru.java2.database.UserDAO;
import org.junit.Before;
import org.junit.Test;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.User;


public class UserDAOImplTest {

    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();

    private UserDAO userDAO = new UserDAOImpl();

    @Before
    public void init() throws DBException {
        databaseCleaner.cleanDatabase();
    }

    @Test
    public void testNonExistingUserDoesNotExist() throws Exception {

        User user = userDAO.getById(123L);
        assertEquals(user, null);
    }

    @Test
    public void testDelete() throws Exception {
        List<User> usersBefore = userDAO.getAll();
        User user = createUser("Test", "Test");
        userDAO.create(user);

        List<User> allUsers = userDAO.getAll();
        assertEquals(allUsers.size(), 1 + usersBefore.size());

        userDAO.delete(user.getUserId());
        allUsers = userDAO.getAll();
        assertEquals(allUsers.size(), usersBefore.size());
    }

    @Test
    public void testUpdate() throws DBException {

        User expected = createUser("QQQ", "AAA");
        userDAO.create(expected);

        expected.setFirstName("ZZZ");
        userDAO.update(expected);

        User actual = userDAO.getById(expected.getUserId());
        assertEquals(expected.getFirstName(), actual.getFirstName());
    }



    @Test
    public void testCreate() throws DBException {
        User user = createUser("F", "L");

        userDAO.create(user);

        User userFromDB = userDAO.getById(user.getUserId());
        assertNotNull(userFromDB);
        assertEquals(user.getUserId(), userFromDB.getUserId());
        assertEquals(user.getFirstName(), userFromDB.getFirstName());
        assertEquals(user.getLastName(), userFromDB.getLastName());
    }

    @Test
    public void testMultipleUserCreation() throws DBException {
        List<User> usersBefore = userDAO.getAll();
        User user1 = createUser("F1", "L1");
        User user2 = createUser("F2", "L2");
        userDAO.create(user1);
        userDAO.create(user2);
        List<User> users = userDAO.getAll();
        assertEquals(2 + usersBefore.size(), users.size());
    }



    private User createUser(String firstName, String lastName) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        return user;
    }

}