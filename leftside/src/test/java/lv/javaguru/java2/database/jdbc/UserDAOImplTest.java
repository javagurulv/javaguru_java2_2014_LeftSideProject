package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
        User user = createUser("u", "p", "Test", "Test");
        userDAO.create(user);

        List<User> allUsers = userDAO.getAll();
        assertEquals(allUsers.size(), 1 + usersBefore.size());

        userDAO.delete(user.getUserId());
        allUsers = userDAO.getAll();
        assertEquals(allUsers.size(), usersBefore.size());
    }

    @Test
    public void testUpdate() throws DBException {

        User expected = createUser("u", "p", "QQQ", "AAA");
        userDAO.create(expected);

        expected.setFirstName("ZZZ");
        userDAO.update(expected);

        User actual = userDAO.getById(expected.getUserId());
        assertEquals(expected.getFirstName(), actual.getFirstName());
    }

    @Test
    public void testCreate() throws DBException {
        User user = createUser("u", "p", "F", "L");

        userDAO.create(user);

        User userFromDB = userDAO.getById(user.getUserId());
        assertNotNull(userFromDB);
        assertEquals(user.getUserId(), userFromDB.getUserId());
        assertEquals(user.getLogin(), userFromDB.getLogin());
        assertEquals(user.getPassword(), userFromDB.getPassword());
        assertEquals(user.getFirstName(), userFromDB.getFirstName());
        assertEquals(user.getLastName(), userFromDB.getLastName());
    }

    @Test
    public void testMultipleUserCreation() throws DBException {
        List<User> usersBefore = userDAO.getAll();
        User user1 = createUser("u1", "p1", "F1", "L1");
        User user2 = createUser("u2", "p2", "F2", "L2");
        userDAO.create(user1);
        userDAO.create(user2);
        List<User> users = userDAO.getAll();
        assertEquals(2 + usersBefore.size(), users.size());
    }

    @Test
    public void testCreateFailsOnNonUniqueLogin() throws DBException {
        List<User> usersBefore = userDAO.getAll();
        User user1 = createUser("u1", "p1", "F1", "L1");
        User user2 = createUser("u1", "p2", "F2", "L2");
        userDAO.create(user1);
        try {
            userDAO.create(user2);
        } catch (Exception e) {
            // IntegrityConstraintViolationException
        }
        List<User> users = userDAO.getAll();
        assertEquals(1 + usersBefore.size(), users.size());
    }

    private User createUser(String login, String password, String firstName, String lastName) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        return user;
    }

}